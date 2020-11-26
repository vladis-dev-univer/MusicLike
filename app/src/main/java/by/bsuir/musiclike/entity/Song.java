package by.bsuir.musiclike.entity;

public class Song {
    private String title;
    private String artist;
    private String path;
    private String album;
    private String duration;

    public Song(String title, String artist, String path, String album, String duration) {
        this.title = title;
        this.artist = artist;
        this.path = path;
        this.album = album;
        this.duration = duration;
    }

    public Song() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
