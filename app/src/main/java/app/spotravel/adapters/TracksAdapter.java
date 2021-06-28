package app.spotravel.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.spotravel.models.Track;

public class TracksAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Track> tracks = new ArrayList<>();
    private OnContactListener onContactListener;

    public TracksAdapter(List<Track> tracks, OnContactListener onContactListener) {
        this.tracks = tracks;
        this.onContactListener = onContactListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(app.spotravel.R.layout.track_layout,viewGroup,false);
        return new ViewHolder(view, this.onContactListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position)
    {
        viewHolder.setData(tracks.get(position));
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }
}
