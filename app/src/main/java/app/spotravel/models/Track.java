package app.spotravel.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.SerializedName;
import com.spotify.protocol.types.Artist;
import com.spotify.protocol.types.ImageUri;

import java.util.ArrayList;
import java.util.List;

public class Track extends com.spotify.protocol.types.Track {
    public Track(
            Artist artist,
            List<Artist> artists,
            Album album,
            long duration,
            String name,
            String uri,
            ImageUri imageUri,
            boolean isEpisode,
            boolean isPodcast) {
        super(
                artist,
                artists,
                album,
                duration,
                name,
                uri,
                imageUri,
                isEpisode,
                isPodcast);
    }

    @SerializedName("album")
    private Album album;

    public Album getAlbum()
    { return album; }

    public void setAlbum(Album album)
    { this.album = album; }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getArtistNames(){
        ArrayList<String> artistNames = new ArrayList<>();

        for (Artist artist: this.artists) {
            artistNames.add(artist.name);
        }

        return String.join(", ", artistNames);
    }
}
