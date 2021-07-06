package app.spotravel.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.google.gson.Gson;

import java.util.ArrayList;

import app.spotravel.R;
import app.spotravel.chart.RadarMarkerView;
import app.spotravel.models.AudioFeatures;

import static app.spotravel.api.SpotifyHelper.getAudioFeatures;
import static app.spotravel.api.SpotifyHelper.getAverageAudioFeatures;
import static app.spotravel.api.SpotifyHelper.getTrack;

public class AudioFeaturesActivity extends AppCompatActivity {
    private Typeface tfLight;
    private Gson gson = new Gson();
    private AudioFeatures audioFeatures;
    private AudioFeatures averageAudioFeatures;
    private RadarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.audio_features_activity);

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.ic_pied_piper);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        chart = findViewById(R.id.chart);
        chart.setBackgroundColor(Color.rgb(60, 65, 82));

        chart.getDescription().setEnabled(false);

        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.LTGRAY);
        chart.setWebLineWidthInner(1f);
        chart.setWebColorInner(Color.LTGRAY);
        chart.setWebAlpha(100);

        MarkerView mv = new RadarMarkerView(this, R.layout.radar_markerview);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv);

        Intent intent = getIntent();
        String selectedTrackId = intent.getStringExtra("selectedTrackId");
        String tracksIdsString = intent.getStringExtra("tracksIds");
        String token = intent.getStringExtra("token");

        audioFeatures = getAudioFeatures(selectedTrackId, token);

        setAverageAudioFeatures(getAverageAudioFeatures(tracksIdsString, token).getAudioFeatures());

        setData(getTrack(selectedTrackId, token).getName());

        chart.animateXY(1400, 1400, Easing.EaseInOutQuad);
    }

    private void setAverageAudioFeatures(@Nullable AudioFeatures[] features) {
        if (features == null)
            return;

        int audioFeaturesSize = features.length;
        double averageDanceability = 0, averageEnergy = 0, averageLoudness = 0, averageSpeechiness = 0,
                averageAcousticness = 0, averageInstrumentalness = 0, averageLiveness = 0, averageValence = 0,
                averageTempo = 0;

        for (AudioFeatures feature : features) {
            averageAcousticness += feature.getAcousticness();
            averageDanceability += feature.getDanceability();
            averageEnergy += feature.getEnergy();
            averageLoudness += feature.getLoudness();
            averageSpeechiness += feature.getSpeechiness();
            averageInstrumentalness += feature.getInstrumentalness();
            averageLiveness += feature.getLiveness();
            averageValence += feature.getValence();
            averageTempo += feature.getTempo();
        }
        averageAudioFeatures = new AudioFeatures();
        averageAudioFeatures.setAcousticness(averageAcousticness / audioFeaturesSize);
        averageAudioFeatures.setDanceability(averageDanceability / audioFeaturesSize);
        averageAudioFeatures.setEnergy(averageEnergy / audioFeaturesSize);
        averageAudioFeatures.setLoudness(averageLoudness / audioFeaturesSize);
        averageAudioFeatures.setSpeechiness(averageSpeechiness / audioFeaturesSize);
        averageAudioFeatures.setInstrumentalness(averageInstrumentalness / audioFeaturesSize);
        averageAudioFeatures.setLiveness(averageLiveness / audioFeaturesSize);
        averageAudioFeatures.setValence(averageValence / audioFeaturesSize);
        averageAudioFeatures.setTempo(averageTempo / audioFeaturesSize);
    }

    private ArrayList<RadarEntry> getEntryFromAudioFeature(AudioFeatures feature) {
        ArrayList<RadarEntry> entries = new ArrayList<>();

        entries.add(new RadarEntry((float) feature.getDanceability()));
        entries.add(new RadarEntry((float) feature.getEnergy()));
        entries.add(new RadarEntry((float) feature.getLoudness()));
        entries.add(new RadarEntry((float) feature.getSpeechiness()));
        entries.add(new RadarEntry((float) feature.getAcousticness()));
        entries.add(new RadarEntry((float) feature.getInstrumentalness()));
        entries.add(new RadarEntry((float) feature.getLiveness()));
        entries.add(new RadarEntry((float) feature.getValence()));
        entries.add(new RadarEntry((float) feature.getTempo()));

        return entries;
    }

    private void setData(String trackName) {

        ArrayList<RadarEntry> averageEntries = getEntryFromAudioFeature(averageAudioFeatures);
        ArrayList<RadarEntry> selectedEntries = getEntryFromAudioFeature(audioFeatures);

        RadarDataSet set1 = new RadarDataSet(averageEntries, "Average Track History");
        set1.setColor(Color.rgb(103, 110, 129));
        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        RadarDataSet set2 = new RadarDataSet(selectedEntries, trackName);
        set2.setColor(Color.rgb(121, 162, 175));
        set2.setFillColor(Color.rgb(121, 162, 175));
        set2.setDrawFilled(true);
        set2.setFillAlpha(180);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);
        data.setValueTypeface(tfLight);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        chart.setData(data);
        chart.invalidate();
    }

}
