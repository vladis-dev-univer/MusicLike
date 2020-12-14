package by.bsuir.musiclike.models;

import java.util.Comparator;

public class Song {
    private long id;
    private String name, album, artist, path, size, duration, date_add;
    private long albumID;

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

    public long getAlbumID() {
        return albumID;
    }

    public void setAlbumID(long albumID) {
        this.albumID = albumID;
    }

    public String getDate_add() {
        return date_add;
    }

    public void setDate_add(String date_add) {
        this.date_add = date_add;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public static Comparator<Song> songNameComparator = new Comparator<Song>() {
        @Override
        public int compare(Song o1, Song o2) {
            return o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase());
        }
    };

    public static Comparator<Song> songArtistComparator = new Comparator<Song>() {
        @Override
        public int compare(Song o1, Song o2) {
            return o1.getArtist().toUpperCase().compareTo(o2.getArtist().toUpperCase());
        }
    };

    public static Comparator<Song> songSizeComparator = new Comparator<Song>() {
        @Override
        public int compare(Song o1, Song o2) {
            return o1.getSize().toUpperCase().compareTo(o2.getSize().toUpperCase());
        }
    };

    public static Comparator<Song> songDurationComparator = new Comparator<Song>() {
        @Override
        public int compare(Song o1, Song o2) {
            return o1.getDuration().toUpperCase().compareTo(o2.getDuration().toUpperCase());
        }
    };

    public static Comparator<Song> songDateAddComparator = new Comparator<Song>() {
        @Override
        public int compare(Song o1, Song o2) {
            return o1.getDate_add().toUpperCase().compareTo(o2.getDate_add().toUpperCase());
        }
    };

}
