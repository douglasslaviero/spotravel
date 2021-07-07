package app.spotravel.api;

import app.spotravel.models.AudioFeatures;
import app.spotravel.models.Track;
import retrofit2.Call;

public class SpotifyHelper {

    public static Track getTrack(String trackId, String token) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Track> call = apiService.getTrack(trackId, "Bearer " + token);

        try {
            return call.execute().body();
        } catch (Exception e) {
            return null;
        }
    }

    public static AudioFeatures getAudioFeatures(String trackId, String token) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<AudioFeatures> call = apiService.getAudioFeature(trackId, "Bearer " + token);

        try {
            return call.execute().body();
        } catch (Exception e) {
            return null;
        }
    }
}
