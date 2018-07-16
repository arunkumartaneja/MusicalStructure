

package com.abnd.android.musicalstructure.model;

public class Artist {

    private String name;
    private int songCount;
    private int image;

    public Artist(String name, int songCount, int image) {
        this.songCount = songCount;
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSongCount() {
        return songCount;
    }

    public void setSongCount(int songCount) {
        this.songCount = songCount;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
