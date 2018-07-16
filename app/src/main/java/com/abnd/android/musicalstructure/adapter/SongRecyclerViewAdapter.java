package com.abnd.android.musicalstructure.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abnd.android.musicalstructure.R;
import com.abnd.android.musicalstructure.fragment.SongFragment.OnListFragmentInteractionListener;
import com.abnd.android.musicalstructure.model.Song;

import java.util.List;


public class SongRecyclerViewAdapter extends RecyclerView.Adapter<SongRecyclerViewAdapter.ViewHolder> {

    private final List<Song> mValues;
    private final OnListFragmentInteractionListener mListener;

    public SongRecyclerViewAdapter(List<Song> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_song, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getTitle());
        holder.mArtistView.setText(mValues.get(position).getArtist());
        holder.mDuration.setText(mValues.get(position).getDuration() + "m");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onSongFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mTitleView;
        private final TextView mArtistView;
        private final TextView mDuration;

        private Song mItem;

        private ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = view.findViewById(R.id.title);
            mArtistView = view.findViewById(R.id.artist);
            mDuration = view.findViewById(R.id.duration);
        }

    }
}
