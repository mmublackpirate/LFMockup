package com.yemyatthu.lomotifmockup.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.holder.StickerHolder;

/**
 * Created by Ye Myat Thu on 2/2/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class StickerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_sticker_item, parent, false);
        return new StickerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
