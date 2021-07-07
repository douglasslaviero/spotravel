package app.spotravel.fragments;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

import app.spotravel.MainActivity;
import app.spotravel.R;
import app.spotravel.activities.AudioFeaturesActivity;
import app.spotravel.activities.GeniusLyrics;
import app.spotravel.adapters.OnContactListener;
import app.spotravel.adapters.TracksAdapter;
import app.spotravel.api.ApiClient;
import app.spotravel.api.ApiInterface;
import app.spotravel.databinding.TracksActivityBinding;
import app.spotravel.models.Track;
import app.spotravel.models.TracksResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TracksFragment extends Fragment implements OnContactListener {
    private TracksActivityBinding binding;
    private String token;
    private ArrayList<Track> tracks = new ArrayList<>();
    private Gson gson = new Gson();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = TracksActivityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerview;
        LinearLayoutManager layoutManager = new LinearLayoutManager(inflater.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        setTracksOnView(binding.recyclerview);
    }

    private void setTracksOnView(RecyclerView recyclerView) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        int tracksQuantity = 10;

        String token = MainActivity.TokenValue;

        Call<TracksResponse> call = apiService.getTrackHistory(tracksQuantity, "Bearer " + token);

        call.enqueue(new Callback<TracksResponse>() {
            @Override
            public void onResponse(Call<TracksResponse> call, Response<TracksResponse> response) {
                tracks = response.body().getTracks();
                recyclerView.setAdapter(new TracksAdapter(tracks, TracksFragment.this));
            }

            @Override
            public void onFailure(Call<TracksResponse> call, Throwable t) {
                String teste = "oi";
            }
        });
    }

    @Override
    public void onContactClick(int position) {
        Intent intent = new Intent(this.getActivity(), AudioFeaturesActivity.class);

        intent.putExtra("trackId", tracks.get(position).getId());
        intent.putExtra("token", MainActivity.TokenValue);

        startActivity(intent);
    }

    int direction = 0;

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction) {
                case ItemTouchHelper.LEFT:
                case ItemTouchHelper.RIGHT:
                    Intent intent = new Intent(TracksFragment.this.getActivity(), GeniusLyrics.class);
                    intent.putExtra("trackId", tracks.get(position).getId());
                    intent.putExtra("token", MainActivity.TokenValue);
                    startActivity(intent);
                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            int iconResource = R.drawable.ic_icons8_genius;;
            int left = 0, top = 0, right = 0, bottom = 0;

            if (dX > 0) {
                c.clipRect(dX, viewHolder.itemView.getTop(), 0f, viewHolder.itemView.getBottom());
                top =viewHolder.itemView.getTop()+50;
                right = 200;
                bottom = viewHolder.itemView.getBottom()-50;

            } else {
                c.clipRect(viewHolder.itemView.getRight() + dX, viewHolder.itemView.getTop(),
                        viewHolder.itemView.getRight(), viewHolder.itemView.getBottom());
                left = viewHolder.itemView.getRight() - 200;
                top = viewHolder.itemView.getTop() + 50;
                right = viewHolder.itemView.getRight();
                bottom = viewHolder.itemView.getBottom() - 50;
            }

            c.drawColor(Color.rgb(39, 251, 107));
            Drawable icon = ContextCompat.getDrawable(getContext(), iconResource);
            icon.setBounds(left, top, right, bottom);
            icon.draw(c);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
