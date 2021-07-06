package app.spotravel.models;

import com.google.gson.annotations.SerializedName;

public class AudioFeaturesCollection {
    public AudioFeatures[] getAudioFeatures() {
        return audioFeatures;
    }

    public void setAudioFeatures(AudioFeatures[] audioFeatures) {
        this.audioFeatures = audioFeatures;
    }

    @SerializedName("audio_features")
    private AudioFeatures[] audioFeatures;
}
