package com.yemyatthu.lomotifmockup.presenter;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.holder.MusicHolder;

/**
 * Created by Ye Myat Thu on 2/1/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class MusicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public int selectedPosition = -1;
    public MediaPlayer mediaPlayer;
    private Fragment fragment;
    public MusicAdapter(Context context, Fragment fragment){
        mediaPlayer = MediaPlayer.create(context,R.raw.music);
        this.fragment = fragment;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_music_item,parent,false);
        return new MusicHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MusicHolder)
            ((MusicHolder) holder).bind(position,this,fragment);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if(mediaPlayer!=null){
            mediaPlayer.stop();
        }
    }
}
