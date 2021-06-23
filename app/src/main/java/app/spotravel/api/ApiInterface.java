package app.spotravel.api;

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
    Call<Object> getTrackHistory(
            @Query("limit") int limit,
            @Header("Authorization") String token);
}
