package by.bsuir.musiclike.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import by.bsuir.musiclike.R;
import by.bsuir.musiclike.controller.SongsAdapter;
import by.bsuir.musiclike.models.Song;

public class SongsListActivity extends AppCompatActivity {

    static ArrayList<Song> songs;
    RecyclerView recyclerView;
    SongsAdapter songsAdapter;
    TextView countOfSongs;
    ImageButton buttonBackHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_list);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        recyclerView = findViewById(R.id.recyclerview);
        countOfSongs = findViewById(R.id.countOfSongs);
        recyclerView.setHasFixedSize(true);

        if (!(songs.size() < 1)) {
            songsAdapter = new SongsAdapter(this, songs);
            recyclerView.setAdapter(songsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        }
        recyclerView.setHasFixedSize(true);
        String songCount = songsAdapter.getItemCount() + " songs";
        countOfSongs.setText(songCount);

        addListenerOnButton();

    }


    public void addListenerOnButton() {
        buttonBackHome = findViewById(R.id.buttonBackHome);

        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SongsListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



}