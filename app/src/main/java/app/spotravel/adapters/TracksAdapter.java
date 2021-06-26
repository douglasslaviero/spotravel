package app.spotravel.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.spotravel.R;
import app.spotravel.models.Track;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ViewHolder> {
    private List<Track> tracks = new ArrayList<>();

    public TracksAdapter(List<Track> tracks) {
        this.tracks = tracks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(app.spotravel.R.layout.track_layout,viewGroup,false);
        return new ViewHolder(view);
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

    class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        private TextView tvTrackName;
        private TextView tvArtistName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTrackName = itemView.findViewById(R.id.track_name);
            tvArtistName = itemView.findViewById(R.id.artist_names);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        private void setData(Track track) {
            tvTrackName.setText(track.name);
            tvArtistName.setText(track.getArtistNames());
        }

        public void onClick(View view) {}
    }
}
