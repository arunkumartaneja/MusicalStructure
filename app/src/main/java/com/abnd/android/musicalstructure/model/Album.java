

package com.abnd.android.musicalstructure.model;

public class Album {
    private String title;
    private String artistName;
    private int songCount;
    private int image;

    public Album(String title, String artistName, int songCount, int image) {
        this.title = title;
        this.artistName = artistName;
        this.songCount = songCount;
        this.image = image;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getSongCount() {
        return songCount;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
