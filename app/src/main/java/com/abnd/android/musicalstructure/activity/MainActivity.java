package com.abnd.android.musicalstructure.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abnd.android.musicalstructure.R;
import com.abnd.android.musicalstructure.fragment.AlbumFragment;
import com.abnd.android.musicalstructure.fragment.ArtistFragment;
import com.abnd.android.musicalstructure.fragment.SongFragment;
import com.abnd.android.musicalstructure.model.Album;
import com.abnd.android.musicalstructure.model.Artist;
import com.abnd.android.musicalstructure.model.Song;
import com.abnd.android.musicalstructure.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SongFragment.OnListFragmentInteractionListener,
        AlbumFragment.OnListFragmentInteractionListener, ArtistFragment.OnListFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SongFragment(), "Songs");
        adapter.addFragment(new AlbumFragment(), "Albums");
        adapter.addFragment(new ArtistFragment(), "Artist");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onSongFragmentInteraction(Song song) {
        Intent intent = new Intent(this, PlayingSongActivity.class);
        intent.putExtra(Constants.KEY_TITLE, song.getTitle());
        intent.putExtra(Constants.KEY_ARTIST, song.getArtist());
        intent.putExtra(Constants.KEY_ALBUM, song.getAlbum());
        intent.putExtra(Constants.KEY_DURATION, song.getDuration());
        intent.putExtra(Constants.KEY_IMAGE, song.getImage());
        startActivity(intent);
    }

    @Override
    public void onAlbumFragmentInteraction(Album item) {
        Intent intent = new Intent(this, SongListActivity.class);
        intent.putExtra(Constants.KEY_TITLE, item.getTitle());
        intent.putExtra(Constants.KEY_ARTIST, item.getArtistName());
        intent.putExtra(Constants.KEY_SONG_COUNT, item.getSongCount());
        intent.putExtra(Constants.KEY_IMAGE, item.getImage());
        startActivity(intent);
    }

    @Override
    public void onArtistFragmentInteraction(Artist item) {
        Intent intent = new Intent(this, SongListActivity.class);
        intent.putExtra(Constants.KEY_TITLE, item.getName());
        intent.putExtra(Constants.KEY_SONG_COUNT, item.getSongCount());
        intent.putExtra(Constants.KEY_IMAGE, item.getImage());
        startActivity(intent);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        private ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        private void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
