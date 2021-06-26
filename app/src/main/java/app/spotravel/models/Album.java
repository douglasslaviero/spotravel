package app.spotravel.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Album extends Spotify {
    @SerializedName("images")
    private ArrayList<Image> images;

    public ArrayList<Image> getImages()
    { return images; }

    public void setImages(ArrayList<Image> images)
    { this.images = images; }
}
