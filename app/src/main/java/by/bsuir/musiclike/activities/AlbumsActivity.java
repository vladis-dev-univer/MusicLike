package by.bsuir.musiclike.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import by.bsuir.musiclike.R;
import by.bsuir.musiclike.controller.AlbumAdapter;
import by.bsuir.musiclike.models.DataReader;
import by.bsuir.musiclike.models.Song;

import static by.bsuir.musiclike.activities.SongsListActivity.songs;

public class AlbumsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        recyclerView = findViewById(R.id.recyclerview_album);
        recyclerView.setHasFixedSize(true);

        if (!(songs.size() < 1)) {
            albumAdapter = new AlbumAdapter(this, DataReader.getAlbumFiles());
            recyclerView.setAdapter(albumAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        recyclerView.setHasFixedSize(true);

    }


}