package app.spotravel.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import app.spotravel.R;
import app.spotravel.api.ApiInterface;
import app.spotravel.api.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        token = getIntent().getExtras().getString("token");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
        //getTracks();
    }

    private void getTracks(){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        int tracksQuantity = 10;

        Call<Object> call = apiService.getTrackHistory(tracksQuantity, "Bearer " + token);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                int statusCode = response.code();
                Log.d("Error", response.message());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                String teste = "oi";
            }
        });
    }
}
