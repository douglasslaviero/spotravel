package app.spotravel.models;

import com.google.gson.annotations.SerializedName;
import com.spotify.protocol.types.Image;

import java.util.ArrayList;

public class Album extends com.spotify.protocol.types.Album {
    public Album(String name, String uri) {
        super(name, uri);
    }

    @SerializedName("images")
    private ArrayList<Image> images;

    public ArrayList<Image> getImages()
    { return images; }

    public void setImages(ArrayList<Image> images)
    { this.images = images; }
}
