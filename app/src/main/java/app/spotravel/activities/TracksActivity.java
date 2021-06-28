package app.spotravel.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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
        token = getIntent().getExtras().getString("token");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracks_activity);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
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
}
