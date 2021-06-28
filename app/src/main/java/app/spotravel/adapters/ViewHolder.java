package app.spotravel.adapters;

import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import app.spotravel.R;
import app.spotravel.models.Track;

class ViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {

    private TextView tvTrackName;
    private TextView tvArtistName;
    private ImageView ivTrackImage;
    private OnContactListener onContactListener;

    public ViewHolder(@NonNull View itemView, OnContactListener onContactListener) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.onContactListener = onContactListener;
        tvTrackName = itemView.findViewById(R.id.track_name);
        tvArtistName = itemView.findViewById(R.id.artist_names);
        ivTrackImage = itemView.findViewById(R.id.track_image );
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setData(Track track) {
        tvTrackName.setText(track.getName());
        tvArtistName.setText(track.getArtistNames());
        ivTrackImage.setImageBitmap(track.getImage());
    }

    public void onClick(View view)
    {
        onContactListener.onContactClick(getAdapterPosition());
    }
}
