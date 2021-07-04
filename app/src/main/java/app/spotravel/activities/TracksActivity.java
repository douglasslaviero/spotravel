package app.spotravel.activities;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

import app.spotravel.R;
import app.spotravel.adapters.OnContactListener;
import app.spotravel.adapters.TracksAdapter;
import app.spotravel.api.ApiClient;
import app.spotravel.api.ApiInterface;
import app.spotravel.models.AudioFeatures;
import app.spotravel.models.Track;
import app.spotravel.models.TracksResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class TracksActivity extends AppCompatActivity implements OnContactListener {
    private String token;
    private ArrayList<Track> tracks = new ArrayList<>();
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_pied_piper);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        token = getIntent().getExtras().getString("token");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracks_activity);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        setTracksOnView(findViewById(R.id.recyclerview));
    }

    private void setTracksOnView(RecyclerView recyclerView) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        int tracksQuantity = 10;

        Call<TracksResponse> call = apiService.getTrackHistory(tracksQuantity, "Bearer " + token);

        call.enqueue(new Callback<TracksResponse>() {
            @Override
            public void onResponse(Call<TracksResponse> call, Response<TracksResponse> response) {
                tracks = response.body().getTracks();
                recyclerView.setAdapter(new TracksAdapter(tracks, TracksActivity.this));
            }

            @Override
            public void onFailure(Call<TracksResponse> call, Throwable t) {
                String teste = "oi";
            }
        });
    }

    @Override
    public void onContactClick(int position)
    {
        Intent intent = new Intent(TracksActivity.this, AudioFeaturesActivity.class);

        intent.putExtra("trackId", tracks.get(position).getId());
        intent.putExtra("token", token);

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
                    Intent intent = new Intent(TracksActivity.this, GeniusLyrics.class);
                    intent.putExtra("trackId", tracks.get(position).getId());
                    intent.putExtra("token", token);
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
            Drawable icon = ContextCompat.getDrawable(getBaseContext(), iconResource);
            icon.setBounds(left, top, right, bottom);
            icon.draw(c);
        }
    };
}
