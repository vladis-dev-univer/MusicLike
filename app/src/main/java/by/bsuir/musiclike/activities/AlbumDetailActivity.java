package by.bsuir.musiclike.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentUris;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.musiclike.R;
import by.bsuir.musiclike.controller.AlbumDetailAdapter;
import by.bsuir.musiclike.models.Song;

import static by.bsuir.musiclike.activities.SongsListActivity.songs;

public class AlbumDetailActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView albumImage;
    TextView albumNameText, countOfSonsText;
    String albumName;
    AlbumDetailAdapter albumDetailAdapter;
    List<Song> albumSongs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        recyclerView = findViewById(R.id.recyclerview_detail_album);
        albumImage = findViewById(R.id.albumPhoto);
        albumNameText = findViewById(R.id.album_name_text);
        countOfSonsText = findViewById(R.id.count_of_songs_album);
        albumName = getIntent().getStringExtra("albumName");
        int j = 0;
        for (int i = 0; i < songs.size(); i++) {
            if (albumName.endsWith(songs.get(i).getAlbum())) {
                albumSongs.add(j, songs.get(i));
                j++;
            }
        }
        Glide.with(this)
                .load(ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"),
                        albumSongs.get(0).getAlbumID()).toString())
                .thumbnail(0.2f)
                .centerCrop()
                .placeholder(R.drawable.ic_album)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(albumImage);
        String count = albumSongs.size() + " songs";
        countOfSonsText.setText(count);
        albumNameText.setText(albumName);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!(albumSongs.size() < 1)) {
            albumDetailAdapter = new AlbumDetailAdapter(this, albumSongs);
            recyclerView.setAdapter(albumDetailAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        }
    }
}