package com.abnd.android.musicalstructure.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abnd.android.musicalstructure.R;
import com.abnd.android.musicalstructure.fragment.ArtistFragment.OnListFragmentInteractionListener;
import com.abnd.android.musicalstructure.model.Artist;

import java.util.List;

public class ArtistRecyclerViewAdapter extends RecyclerView.Adapter<ArtistRecyclerViewAdapter.ViewHolder> {

    private final List<Artist> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ArtistRecyclerViewAdapter(List<Artist> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_artist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(mValues.get(position).getName());
        holder.mSoungCountView.setText("Total Songs " + mValues.get(position).getSongCount());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onArtistFragmentInteraction(holder.mItem);
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
        private final TextView mNameView;
        private final TextView mSoungCountView;
        private Artist mItem;

        private ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = view.findViewById(R.id.artist_name);
            mSoungCountView = view.findViewById(R.id.artist_song_count);
        }

    }
}
