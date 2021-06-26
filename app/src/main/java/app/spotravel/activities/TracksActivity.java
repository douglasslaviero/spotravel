package app.spotravel.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spotify.protocol.types.Track;

import java.util.ArrayList;
import java.util.List;

import app.spotravel.R;
import app.spotravel.adapters.TracksAdapter;
import app.spotravel.api.ApiInterface;
import app.spotravel.api.ApiClient;
import app.spotravel.models.TracksResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TracksActivity extends AppCompatActivity  {
    private String token;

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
                List<Track>  tracks = response.body().getTracks();
                recyclerView.setAdapter(new TracksAdapter(tracks));
            }

            @Override
            public void onFailure(Call<TracksResponse> call, Throwable t) {
                String teste = "oi";
            }
        });
    }
}
