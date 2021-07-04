package app.spotravel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import app.spotravel.R;
import app.spotravel.api.ApiClient;
import app.spotravel.api.ApiInterface;
import app.spotravel.models.AudioFeatures;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AudioFeaturesActivity extends AppCompatActivity {
    private Gson gson = new Gson();
    private AudioFeatures audioFeatures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_features_activity);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_pied_piper);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        Intent intent = getIntent();
        String trackId = intent.getStringExtra("trackId");
        String token = intent.getStringExtra("token");

        setAudioFeatures(trackId, token);

        TextView tvDanceabilityValue = findViewById(R.id.tvDanceabilityValue);
        TextView tvEnergyValue = findViewById(R.id.tvEnergyValue);
        TextView tvLoudnessValue = findViewById(R.id.tvLoudnessValue);
        TextView tvSpeechinessValue = findViewById(R.id.tvSpeechinessValue);
        TextView tvAcousticnessValue = findViewById(R.id.tvAcousticnessValue);
        TextView tvInstrumentalnessValue = findViewById(R.id.tvInstrumentalnessValue);
        TextView tvLivenessValue = findViewById(R.id.tvLivenessValue);
        TextView tvValenceValue = findViewById(R.id.tvValenceValue);
        TextView tvTempoValue = findViewById(R.id.tvTempoValue);

        tvDanceabilityValue.setText(String.valueOf(audioFeatures.getDanceability()));
        tvEnergyValue.setText(String.valueOf(audioFeatures.getEnergy()));
        tvLoudnessValue.setText(String.valueOf(audioFeatures.getLoudness()));
        tvSpeechinessValue.setText(String.valueOf(audioFeatures.getSpeechiness()));
        tvAcousticnessValue.setText(String.valueOf(audioFeatures.getAcousticness()));
        tvInstrumentalnessValue.setText(String.valueOf(audioFeatures.getInstrumentalness()));
        tvLivenessValue.setText(String.valueOf(audioFeatures.getLiveness()));
        tvValenceValue.setText(String.valueOf(audioFeatures.getValence()));
        tvTempoValue.setText(String.valueOf(audioFeatures.getTempo()));
    }

    private void setAudioFeatures(String trackId, String token){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<AudioFeatures> call = apiService.getAudioFeature(trackId,  "Bearer " + token);

        try{
            audioFeatures = call.execute().body();
        } catch (Exception e){
            //mostrar erro
        }
    }
}
