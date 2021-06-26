package app.spotravel.models;

import android.os.Build;

import androidx.annotation.RequiresApi;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Track extends  Spotify {
    @SerializedName("album")
    private Album album;
    @SerializedName("artists")
    private ArrayList<Artist> artists;

    public Album getAlbum()
    { return album; }

    public void setAlbum(Album album)
    { this.album = album; }

    public ArrayList<Artist> getArtists()
    { return artists; }

    public void setArtists(ArrayList<Artist> artists)
    { this.artists = artists; }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getArtistNames(){
        ArrayList<String> artistNames = new ArrayList<>();

        for (Artist artist: this.artists) {
            artistNames.add(artist.getName());
        }

        return String.join(", ", artistNames);
    }
}
