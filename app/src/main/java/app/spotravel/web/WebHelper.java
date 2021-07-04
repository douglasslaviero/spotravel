package app.spotravel.web;

import java.util.ArrayList;

import app.spotravel.models.Artist;
import app.spotravel.models.Track;

public class WebHelper {

    public static String getUrl(Track track)
    {
        ArrayList<Artist> artists = track.getArtists();
        String name = track.getName();

        String artistsString = "";

        for(Artist artist :artists)
        {
            artistsString = artistsString.concat(artist.getName().replace(' ', '-').concat("-"));
        }

        return String.format("https://genius.com/%s%s-lyrics",artistsString ,
                name.replaceAll("[^a-zA-Z0-9]", "").replace(' ', '-'));
    }
}
