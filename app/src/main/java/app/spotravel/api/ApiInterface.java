package app.spotravel.api;

import app.spotravel.models.AudioFeatures;
import app.spotravel.models.Track;
import app.spotravel.models.TracksResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("v1/me/player/recently-played")
    Call<TracksResponse> getTrackHistory(
            @Query("limit") int limit,
            @Header("Authorization") String token);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("v1/audio-features/{id}")
    Call<AudioFeatures> getAudioFeature(
            @Path("id") String id,
            @Header("Authorization") String token);

    @GET("v1/tracks/{id}")
    Call<Track> getTrack(
            @Path("id") String id,
            @Header("Authorization") String token);
}
