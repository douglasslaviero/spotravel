package app.spotravel.models;

import com.google.gson.annotations.SerializedName;

public class AudioFeatures {
    @SerializedName("danceability")
    private double danceability;
    @SerializedName("energy")
    private double energy;
    @SerializedName("loudness")
    private double loudness;
    @SerializedName("speechiness")
    private double speechiness;
    @SerializedName("acousticness")
    private double acousticness;
    @SerializedName("instrumentalness")
    private double instrumentalness;
    @SerializedName("liveness")
    private double liveness;
    @SerializedName("valence")
    private double valence;
    @SerializedName("tempo")
    private double tempo;

    public double getDanceability()
    { return danceability; }

    public void setDanceability(double danceability)
    { this.danceability = danceability; }

    public double getEnergy()
    { return energy; }

    public void setEnergy(double energy)
    { this.energy = energy; }

    public double getLoudness()
    { return loudness; }

    public void setLoudness(double loudness)
    { this.loudness = loudness; }

    public double getSpeechiness()
    { return speechiness; }

    public void setSpeechiness(double speechiness)
    { this.speechiness = speechiness; }

    public double getAcousticness()
    { return acousticness; }

    public void setAcousticness(double acousticness)
    { this.acousticness = acousticness; }

    public double getInstrumentalness()
    { return instrumentalness; }

    public void setInstrumentalness(double instrumentalness)
    { this.instrumentalness = instrumentalness; }

    public double getLiveness()
    { return liveness; }

    public void setLiveness(double liveness)
    { this.liveness = liveness; }

    public double getValence()
    { return valence; }

    public void setValence(double valence)
    { this.valence = valence; }

    public double getTempo()
    { return tempo; }

    public void setTempo(double tempo)
    { this.tempo = tempo; }
}
