package app.spotravel.web;

import java.text.Normalizer;
import java.util.ArrayList;

import app.spotravel.models.Artist;
import app.spotravel.models.Track;

public class WebHelper {

    public static String getUrl(Track track) {
        ArrayList<Artist> artists = track.getArtists();
        String name = track.getName();

        String artistsString = "";

        for (Artist artist : artists) {
            artistsString = artistsString.concat(getTreatedString(artist.getName()) + "-");
        }

        String resultUrl = String.format("https://genius.com/%s%s-lyrics",
                artistsString, getTreatedString(name));

        return resultUrl;
    }

    private static String getTreatedString(String _string) {
        String treated = Normalizer.normalize(_string, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[^a-zA-Z0-9\\s]", "")
                .replace(' ', '-');

        while (treated.contains("--"))
            treated = treated.replace("--", "-");

        return treated;
    }
}
