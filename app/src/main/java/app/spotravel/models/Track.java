package app.spotravel.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.StrictMode;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.SerializedName;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import app.spotravel.api.ApiClient;
import app.spotravel.api.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Track extends  Spotify {
    @SerializedName("album")
    private Album album;
    @SerializedName("artists")
    private ArrayList<Artist> artists;

    private AudioFeatures audioFeatures;

    public Album getAlbum()
    { return album; }

    public void setAlbum(Album album)
    { this.album = album; }

    public ArrayList<Artist> getArtists()
    { return artists; }

    public void setArtists(ArrayList<Artist> artists)
    { this.artists = artists; }

    public AudioFeatures getAudioFeatures()
    { return audioFeatures; }

    public void setAudioFeatures(AudioFeatures audioFeatures)
    { this.audioFeatures = audioFeatures; }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getArtistNames(){
        ArrayList<String> artistNames = new ArrayList<>();

        for (Artist artist: this.artists) {
            artistNames.add(artist.getName());
        }

        return String.join(", ", artistNames);
    }

    public Bitmap getImage(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String imageURL = this.getAlbum().getImages().get(2).getUrl();
        Bitmap image = null;

        try{
            InputStream in = new URL(imageURL).openStream();
            image = BitmapFactory.decodeStream(in);
        } catch (Exception e){
            //mostrar erro ao carregar imagem;
        }

        return image;
    }
}
