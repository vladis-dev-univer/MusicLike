package by.bsuir.musiclike.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.Collections;

import by.bsuir.musiclike.R;
import by.bsuir.musiclike.controller.SongsAdapter;
import by.bsuir.musiclike.models.Song;

public class SongsListActivity extends AppCompatActivity {

    static ArrayList<Song> songs;
    RecyclerView recyclerView;
    SongsAdapter songsAdapter;
    TextView countOfSongs;
    ImageButton buttonBackHome, buttonMenu;


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
        buttonMenu = findViewById(R.id.imageButtonMenuAltPlayNow);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });
        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SongsListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showMenu(View v) {
        PopupMenu menu = new PopupMenu(this, v);

        menu.inflate(R.menu.sort_item_menu);

        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.by_name:
                        Collections.sort(songs, Song.songNameComparator);
                        recreate();
                        return true;
                    case R.id.by_date:
                        Collections.sort(songs, Song.songDateAddComparator);
                        recreate();
                        return true;
                    case R.id.by_size:
                        Collections.sort(songs, Song.songSizeComparator);
                        recreate();
                        return true;
                    case R.id.by_duration:
                        Collections.sort(songs, Song.songDurationComparator);
                        recreate();
                        return true;
                    case R.id.by_artist:
                        Collections.sort(songs, Song.songArtistComparator);
                        recreate();
                        return true;
                    default:
                        return false;
                }
            }
        });

        menu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                Toast.makeText(getApplicationContext(), "Ready",
                        Toast.LENGTH_SHORT).show();
            }
        });

        menu.show();
    }



}