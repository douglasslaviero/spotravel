package app.spotravel.models;

import com.google.gson.annotations.SerializedName;
import com.spotify.protocol.types.Track;

import java.util.ArrayList;
import java.util.List;

public class TracksResponse {
    @SerializedName("items")
    private List<TrackResponse> items;
    @SerializedName("limit")
    private int limit;

    public ArrayList<Track> getTracks()
    {
        ArrayList<Track> tracks = new ArrayList<Track>();

        for (TrackResponse item: items) {
            tracks.add(item.getTrack());
        }

        return tracks;
    }

    public List<TrackResponse> getItems()
    { return items; }

    public void setItems(List<TrackResponse> items)
    { this.items = items; }

    public int getLimit()
    { return limit; }

    public void setLimit(int limit)
    { this.limit = limit; }
}
