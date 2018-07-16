package com.abnd.android.musicalstructure.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.abnd.android.musicalstructure.R;
import com.abnd.android.musicalstructure.util.Constants;

public class PlayingSongActivity extends AppCompatActivity {

    private MyCountDownTimer myCountDownTimer;
    private SeekBar seekBar;
    private ImageView playView;
    private ImageView pauseView;
    private int progress;
    private TextView playingDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_song);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        if (null != intent) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String title = extras.get(Constants.KEY_TITLE).toString();
                String artist = extras.get(Constants.KEY_ARTIST).toString();
                int duration = Integer.parseInt(extras.get(Constants.KEY_DURATION).toString());

                ((TextView) findViewById(R.id.playing_title)).setText(title);
                ((TextView) findViewById(R.id.playing_artist)).setText(artist);

                ((TextView) findViewById(R.id.playing_duration)).setText(duration + "m");
                playingDuration = findViewById(R.id.playing_progress);
                playingDuration.setText("0.00");

                seekBar = findViewById(R.id.seek_bar);
                seekBar.setMax(duration * 60 * 1000);

                myCountDownTimer = new MyCountDownTimer(seekBar.getMax(), 1000);
                myCountDownTimer.start();

                playView = findViewById(R.id.play);
                pauseView = findViewById(R.id.pause);

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        if (progress > 0) {
                            myCountDownTimer.cancel();
                            myCountDownTimer = new MyCountDownTimer(seekBar.getMax() - seekBar.getProgress(), 1000);
                            myCountDownTimer.start();
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        myCountDownTimer.cancel();
                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                    }
                });

                playView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pauseView.setVisibility(View.VISIBLE);
                        playView.setVisibility(View.GONE);

                        myCountDownTimer = new MyCountDownTimer(seekBar.getMax() - seekBar.getProgress(), 1000);
                        myCountDownTimer.start();

                    }
                });

                pauseView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playView.setVisibility(View.VISIBLE);
                        pauseView.setVisibility(View.GONE);
                        myCountDownTimer.cancel();
                    }
                });
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

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the state of item position
        outState.putInt("progress", seekBar.getProgress());
    }

    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Read the state of item position
        progress = savedInstanceState.getInt("progress");

        if (progress > 0) {
            seekBar.setProgress(progress);
            myCountDownTimer = new MyCountDownTimer(seekBar.getMax() - seekBar.getProgress(), 1000);
            myCountDownTimer.start();
        }

    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            progress = seekBar.getMax() - (int) millisUntilFinished;

            playingDuration.setText(progress / (60 * 1000) + ":" + (progress % (60 * 1000)) / 1000);
            seekBar.setProgress(progress);
        }

        @Override
        public void onFinish() {
            seekBar.setProgress(seekBar.getMax());
            playingDuration.setText(seekBar.getMax() / (60 * 1000) + ":" + (seekBar.getMax() % (60 * 1000)) / 1000);
        }
    }
}
