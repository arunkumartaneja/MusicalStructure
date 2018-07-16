package com.abnd.android.musicalstructure.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abnd.android.musicalstructure.R;
import com.abnd.android.musicalstructure.fragment.AlbumFragment.OnListFragmentInteractionListener;
import com.abnd.android.musicalstructure.model.Album;

import java.util.List;

public class AlbumRecyclerViewAdapter extends RecyclerView.Adapter<AlbumRecyclerViewAdapter.ViewHolder> {

    private final List<Album> mValues;
    private final OnListFragmentInteractionListener mListener;

    public AlbumRecyclerViewAdapter(List<Album> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getTitle());
        holder.mArtistView.setText(mValues.get(position).getArtistName());
        holder.mSongCountView.setText("Total Songs " + mValues.get(position).getSongCount());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onAlbumFragmentInteraction(holder.mItem);
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
        private final TextView mSongCountView;
        private Album mItem;

        private ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = view.findViewById(R.id.album_title);
            mArtistView = view.findViewById(R.id.album_artist);
            mSongCountView = view.findViewById(R.id.album_song_count);
        }

    }
}
