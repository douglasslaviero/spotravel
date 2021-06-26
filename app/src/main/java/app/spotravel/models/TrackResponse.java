package app.spotravel.models;

import com.google.gson.annotations.SerializedName;
import com.spotify.protocol.types.Track;

public class TrackResponse {
    @SerializedName("track")
    private Track track;
    @SerializedName("played_at")
    private String played_at;

    public Track getTrack()
    { return track; }

    public void setTrack(Track track)
    { this.track = track; }

    public String getPlayedAt()
    { return played_at; }

    public void setPlayedAt(String played_at)
    { this.played_at = played_at; }
}
