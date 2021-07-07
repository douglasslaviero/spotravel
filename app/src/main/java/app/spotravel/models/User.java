package app.spotravel.models;

import com.google.gson.annotations.SerializedName;

public class User extends Spotify{
    @SerializedName("display_name")
    private String displayName;

    public String getDisplayName()
    { return displayName; }

    public void setDisplayName(String displayName)
    { this.displayName = displayName; }
}
