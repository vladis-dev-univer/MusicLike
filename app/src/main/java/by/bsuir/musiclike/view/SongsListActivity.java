package by.bsuir.musiclike.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import by.bsuir.musiclike.R;
import by.bsuir.musiclike.controller.SongsAdapter;
import by.bsuir.musiclike.entity.Song;

public class SongsListActivity extends AppCompatActivity {

    public static ArrayList<Song> songArrayList;
    RecyclerView recyclerView;
    SongsAdapter songsAdapter;
    TextView countOfSongs;
    ImageButton buttonBackHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_list);

        recyclerView = findViewById(R.id.recyclerview);
        countOfSongs = findViewById(R.id.countOfSongs);
        recyclerView.setHasFixedSize(true);

        if (!(songArrayList.size() < 1)) {
            songsAdapter = new SongsAdapter(this, songArrayList);
            recyclerView.setAdapter(songsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,
                    false));
        }
        String songCount = songsAdapter.getItemCount() + " songs";
        countOfSongs.setText(songCount);

        addListenerOnButton();

    }


    public void addListenerOnButton() {
        buttonBackHome = findViewById(R.id.buttonBackHome);

        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(".MainActivity");
                startActivity(intent);
            }
        });
    }



}