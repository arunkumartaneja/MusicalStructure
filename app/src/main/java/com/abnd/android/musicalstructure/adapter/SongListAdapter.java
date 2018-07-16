package com.abnd.android.musicalstructure.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.abnd.android.musicalstructure.R;
import com.abnd.android.musicalstructure.activity.PlayingSongActivity;
import com.abnd.android.musicalstructure.model.Song;
import com.abnd.android.musicalstructure.util.Constants;

import java.util.List;

public class SongListAdapter extends ArrayAdapter<Song> {


    public SongListAdapter(Activity context, List<Song> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fragment_song, parent, false);
        }

        final Song song = getItem(position);

        TextView title = listItemView.findViewById(R.id.title);
        title.setText(song.getTitle());


        TextView artist = listItemView.findViewById(R.id.artist);
        artist.setText(song.getArtist());

        TextView duration = listItemView.findViewById(R.id.duration);
        duration.setText(song.getDuration() + "m");


        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PlayingSongActivity.class);
                intent.putExtra(Constants.KEY_TITLE, song.getTitle());
                intent.putExtra(Constants.KEY_ARTIST, song.getArtist());
                intent.putExtra(Constants.KEY_ALBUM, song.getAlbum());
                intent.putExtra(Constants.KEY_DURATION, song.getDuration());
                intent.putExtra(Constants.KEY_IMAGE, song.getImage());
                v.getContext().startActivity(intent);
            }
        });


        return listItemView;
    }
}
