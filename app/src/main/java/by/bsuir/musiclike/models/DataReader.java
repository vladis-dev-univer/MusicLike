package by.bsuir.musiclike.models;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DataReader {

    private static HashMap<String, List<Song>> albums = new HashMap<>();
    public static ArrayList<ArrayList<Song>> albumFiles = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<Song> getAllAudio(final Context context) {
        final ArrayList<Song> songs = new ArrayList<>();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.Audio.AudioColumns.DATA,
                MediaStore.Audio.AudioColumns.TITLE,
                MediaStore.Audio.AudioColumns.ALBUM,
                MediaStore.Audio.ArtistColumns.ARTIST,
                MediaStore.Audio.AudioColumns.ALBUM_ID,
                MediaStore.Audio.AudioColumns._ID};
        Cursor c = context.getContentResolver().query(uri, projection, null, null, null);

        if (c != null) {

            while (c.moveToNext()) {
                Song audioModel = new Song();
                String path = c.getString(0);   // Retrieve path.
                String name = c.getString(1);   // Retrieve name.
                String album = c.getString(2);  // Retrieve album name.
                String artist = c.getString(3); // Retrieve artist name.
                long albumID = c.getLong(4);
                long thisId = c.getLong(5);
                audioModel.setId(thisId);
                audioModel.setName(name);
                audioModel.setAlbum(album);
                audioModel.setArtist(artist);
                audioModel.setPath(path);
                audioModel.setAlbumID(albumID);
                songs.add(audioModel);
                if (albums.get(album) == null) {
                    albums.put(album, new ArrayList<Song>());
                }
                Objects.requireNonNull(albums.get(album)).add(audioModel);
            }
            c.close();
        }
        return songs;
    }

    private static void shift() {
        Iterator it = albums.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            albumFiles.add((ArrayList<Song>) pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    public static ArrayList<ArrayList<Song>> getAlbumFiles() {
        shift();
        return albumFiles;
    }
}
