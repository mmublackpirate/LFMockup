package com.yemyatthu.lomotifmockup.presenter;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.holder.VideoHolder;

import im.ene.toro.ToroAdapter;

/**
 * Created by Ye Myat Thu on 1/31/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class VideoAdapter extends ToroAdapter<ToroAdapter.ViewHolder> {
    @Override
    public ToroAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_video_item, parent, false);
        return new VideoHolder(view);
    }


    @Override
    public int getItemCount() {
        return 10;
    }

    @Nullable
    @Override
    protected Object getItem(int position) {
        return "asset:///video.mp4";
    }
}
