package by.bsuir.musiclike.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import by.bsuir.musiclike.R;
import by.bsuir.musiclike.models.DataReader;

import static by.bsuir.musiclike.activities.SongsListActivity.songs;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        addListenerOnButton();

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            requestStoragePermission();
        } else {
            start();
        }
    }

    public void addListenerOnButton() {
        Button buttonSongs = findViewById(R.id.buttonSongs);
        Button buttonAlbums = findViewById(R.id.buttonAlbums);

        buttonSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SongsListActivity.class);
                startActivity(intent);
            }
        });

        buttonAlbums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AlbumsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this).setTitle("Permission Needed").setMessage("Need to read songs from your storage").setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);

                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                start();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void start() {
        songs = DataReader.getAllAudio(this);

    }

}