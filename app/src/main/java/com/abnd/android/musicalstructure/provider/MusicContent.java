package com.abnd.android.musicalstructure.provider;

import com.abnd.android.musicalstructure.R;
import com.abnd.android.musicalstructure.model.Album;
import com.abnd.android.musicalstructure.model.Artist;
import com.abnd.android.musicalstructure.model.Song;

import java.util.ArrayList;
import java.util.List;

public class MusicContent {

    public static final List<Song> SONGS = new ArrayList<>();
    public static final List<Album> ALBUMS = new ArrayList<>();
    public static final List<Artist> ARTISTS = new ArrayList<>();

    private static final int COUNT = 25;

    static {
        // Add some sample songs to show on UI.
        for (int i = 1; i <= COUNT; i++) {
            SONGS.add(new Song("Song " + i, "Song " + i + " artist", "Song_" + i + "album", i, R.drawable.song));
            ALBUMS.add(new Album("Album " + i, "Album " + i + " artist", i, R.drawable.album));
            ARTISTS.add(new Artist("Artist " + i, i, R.drawable.artist));
        }
    }

}
