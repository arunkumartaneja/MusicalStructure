package com.abnd.android.musicalstructure.model;

public class Song {

    private String title;
    private String artist;
    private String album;
    private int duration;
    private int image;

    public Song(String title, String artist, String album, int duration, int image) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.image = image;
    }

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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", duration=" + duration +
                ", image='" + image + '\'' +
                '}';
    }
}
