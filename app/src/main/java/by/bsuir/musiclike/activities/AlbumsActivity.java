package by.bsuir.musiclike.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import by.bsuir.musiclike.R;
import by.bsuir.musiclike.controller.AlbumAdapter;
import by.bsuir.musiclike.models.DataReader;

import static by.bsuir.musiclike.activities.SongsListActivity.songs;

public class AlbumsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AlbumAdapter albumAdapter;
    ImageButton back;

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
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        back = findViewById(R.id.buttonBackHomeFromAlbum);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlbumsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}