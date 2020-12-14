package by.bsuir.musiclike.controller;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.musiclike.R;
import by.bsuir.musiclike.activities.SongActivity;
import by.bsuir.musiclike.models.Song;

public class AlbumDetailAdapter extends RecyclerView.Adapter<AlbumDetailAdapter.MyViewHolderAlbumDetail> {

    private Context mContext;
    public static ArrayList<Song> albumSongs;

    public AlbumDetailAdapter(Context mContext, ArrayList<Song> albumSongs) {
        this.mContext = mContext;
        AlbumDetailAdapter.albumSongs = albumSongs;
    }


    @NonNull
    @Override
    public MyViewHolderAlbumDetail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new MyViewHolderAlbumDetail(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolderAlbumDetail holder, final int position) {
        Song song = albumSongs.get(position);
        if (song != null) {
            holder.fileName.setText(song.getName());
            holder.fileArtist.setText(song.getArtist());
            Glide .with(mContext)
                    .load(ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), song.getAlbumID()).toString())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.music_note_ic)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                    )
                    .thumbnail(0.1f)
                    .transition(new DrawableTransitionOptions()
                            .crossFade()
                    )
                    .into(holder.albumArt);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, SongActivity.class);
                        intent.putExtra("sender", "albumDetails");
                        intent.putExtra("position", position);
                        mContext.startActivity(intent);
                    }
                });

        }

    }

    @Override
    public int getItemCount() {
        return albumSongs.size();
    }


    public static class MyViewHolderAlbumDetail extends RecyclerView.ViewHolder {
        TextView fileName, fileArtist;
        ImageView albumArt;

        public MyViewHolderAlbumDetail(@NonNull View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.musicFileName);
            fileArtist = itemView.findViewById(R.id.musicFileArtist);
            albumArt = itemView.findViewById(R.id.musicImage);
        }
    }

}
