package com.yemyatthu.lomotifmockup.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.holder.AlbumHolder;
import com.yemyatthu.lomotifmockup.util.AlbumClickListener;

/**
 * Created by Ye Myat Thu on 1/31/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class AlbumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private AlbumClickListener albumCLickListener;

    public AlbumAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_album_item, parent, false);
        return new AlbumHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AlbumHolder)
            ((AlbumHolder) holder).bind(position, albumCLickListener);
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public void setAlbumClickListener(AlbumClickListener albumClickListener) {
        this.albumCLickListener = albumClickListener;
    }
}
