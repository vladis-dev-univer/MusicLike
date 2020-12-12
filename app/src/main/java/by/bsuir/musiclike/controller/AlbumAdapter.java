package by.bsuir.musiclike.controller;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcelable;
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
import by.bsuir.musiclike.activities.AlbumDetailActivity;
import by.bsuir.musiclike.activities.SongActivity;
import by.bsuir.musiclike.models.Song;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolderAlbum> {

    private Context mContext;
    private ArrayList<ArrayList<Song>> albumFiles;

    public AlbumAdapter(Context mContext, ArrayList<ArrayList<Song>> albumFiles) {
        this.mContext = mContext;
        this.albumFiles = albumFiles;
    }

    @NonNull
    @Override
    public MyViewHolderAlbum onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);
        return new MyViewHolderAlbum(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderAlbum holder, int position) {
        final Song song = albumFiles.get(position).get(0);
        if (song != null) {
            holder.albumName.setText(song.getAlbum());
            Glide.with(mContext)
                    .load(ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"),
                            song.getAlbumID()).toString())
                    .thumbnail(0.2f)
                    .centerCrop()
                    .placeholder(R.drawable.ic_album)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(holder.albumImage);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, AlbumDetailActivity.class);
                    intent.putExtra("albumName", song.getAlbum());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return albumFiles.size();
    }


    public static class MyViewHolderAlbum extends RecyclerView.ViewHolder {
        ImageView albumImage;
        TextView albumName;

        public MyViewHolderAlbum(@NonNull View itemView) {
            super(itemView);
            albumName = itemView.findViewById(R.id.album_name);
            albumImage = itemView.findViewById(R.id.album_image);
        }
    }

}
