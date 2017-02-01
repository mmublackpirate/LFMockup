package com.yemyatthu.lomotifmockup.holder;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.event.MusicEvent;
import com.yemyatthu.lomotifmockup.presenter.MusicAdapter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ye Myat Thu on 2/1/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class MusicHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.music_card)
    View musicCard;
    @BindDrawable(R.drawable.background_rounded_accent_50)
    Drawable accent50;
    @BindDrawable(R.drawable.background_rounded_white)
    Drawable white;
    public MusicHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(int position, MusicAdapter adapter, Fragment fragment){
        if(adapter.selectedPosition== position){
            musicCard.setBackground(accent50);
        }else{
            musicCard.setBackground(white);
        }
       itemView.setOnClickListener(v -> {
           if(adapter.selectedPosition == position){
               adapter.selectedPosition = -1;
               adapter.notifyItemChanged(position);
               adapter.mediaPlayer.stop();
           }else {
               adapter.notifyItemChanged(adapter.selectedPosition);
               if(adapter.mediaPlayer.isPlaying())
                   adapter.mediaPlayer.stop();
               adapter.mediaPlayer = MediaPlayer.create(itemView.getContext(),R.raw.music);
               adapter.mediaPlayer.start();
               adapter.selectedPosition = position;
               adapter.notifyItemChanged(adapter.selectedPosition);
               EventBus.getDefault().post(new MusicEvent(fragment));
           }
       });
    }
}
