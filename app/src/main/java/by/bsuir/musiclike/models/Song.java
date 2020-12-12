package by.bsuir.musiclike.models;

import android.graphics.Bitmap;
import android.os.Parcelable;

import java.util.ArrayList;

public class Song {
    private long id;
    private String name, album, artist, path, AlbumArt;
    private long albumID;
    private int im;
    private Bitmap songImage;

    public Song() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAlbumArt() {
        return AlbumArt;
    }

    public void setAlbumArt(String albumArt) {
        AlbumArt = albumArt;
    }

    public long getAlbumID() {
        return albumID;
    }

    public void setAlbumID(long albumID) {
        this.albumID = albumID;
    }

    public int getIm() {
        return im;
    }

    public void setIm(int im) {
        this.im = im;
    }

    public Bitmap getSongImage() {
        return songImage;
    }

    public void setSongImage(Bitmap songImage) {
        this.songImage = songImage;
    }
    //    private String title;
//    private String artist;
//    private String path;
//    private String album;
//    private String duration;
//
//    public Song(String title, String artist, String path, String album, String duration) {
//        this.title = title;
//        this.artist = artist;
//        this.path = path;
//        this.album = album;
//        this.duration = duration;
//    }
//
//    public Song() {
//        this.title = null;
//        this.artist = null;
//        this.path = null;
//        this.album = null;
//        this.duration = null;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getArtist() {
//        return artist;
//    }
//
//    public void setArtist(String artist) {
//        this.artist = artist;
//    }
//
//    public String getPath() {
//        return path;
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//    }
//
//    public String getAlbum() {
//        return album;
//    }
//
//    public void setAlbum(String album) {
//        this.album = album;
//    }
//
//    public String getDuration() {
//        return duration;
//    }
//
//    public void setDuration(String duration) {
//        this.duration = duration;
//    }
}
