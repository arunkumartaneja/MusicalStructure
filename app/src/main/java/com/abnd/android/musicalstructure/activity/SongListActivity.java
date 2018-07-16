package com.abnd.android.musicalstructure.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.abnd.android.musicalstructure.R;
import com.abnd.android.musicalstructure.adapter.SongListAdapter;
import com.abnd.android.musicalstructure.provider.MusicContent;
import com.abnd.android.musicalstructure.util.Constants;


public class SongListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        if (null != intent) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String title = extras.get(Constants.KEY_TITLE).toString();
                int songCount = Integer.parseInt(extras.get(Constants.KEY_SONG_COUNT).toString());
                int image = Integer.parseInt(extras.get(Constants.KEY_IMAGE).toString());

                ((TextView) findViewById(R.id.title)).setText(title);
                ((TextView) findViewById(R.id.song_count)).setText("Total Songs - " + songCount);

                findViewById(R.id.song_list_layout).setBackgroundResource(image);

                SongListAdapter songListAdapter = new SongListAdapter(this, MusicContent.SONGS.subList(0, songCount));
                ListView listView = findViewById(R.id.list);
                listView.setAdapter(songListAdapter);
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
