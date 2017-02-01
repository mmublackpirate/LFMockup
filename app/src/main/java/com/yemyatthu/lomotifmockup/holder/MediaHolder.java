package com.yemyatthu.lomotifmockup.holder;

import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.event.MediaSelectEvent;
import com.yemyatthu.lomotifmockup.util.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ye Myat Thu on 1/31/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class MediaHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.media_cont)
    FrameLayout mediaCont;
    @BindView(R.id.media_image)
    ImageView mediaImage;
    @BindDrawable(R.drawable.background_media_selected)
    Drawable mediaSelected;
    private boolean disableSelection = false;
    public MediaHolder(View itemView,boolean disableSelection) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.disableSelection = disableSelection;
    }

    public void bind(int position,List<Integer> selectedPosition){
        if(!disableSelection) {
            if (selectedPosition.contains(position)) {
                ViewCompat.setElevation(mediaCont, Utils.convertDpToPixel(8, mediaCont.getContext()));
                mediaCont.setForeground(mediaSelected);
            } else {
                ViewCompat.setElevation(mediaCont, 0);
                mediaCont.setForeground(null);
            }
            mediaCont.setOnClickListener(v -> {
                if (!selectedPosition.contains(position)) {
                    ViewCompat.setElevation(mediaCont, Utils.convertDpToPixel(8, mediaCont.getContext()));
                    mediaCont.setForeground(mediaSelected);
                    EventBus.getDefault().post(new MediaSelectEvent(true));
                    selectedPosition.add(position);
                } else {
                    ViewCompat.setElevation(mediaCont, 0);
                    mediaCont.setForeground(null);
                    EventBus.getDefault().post(new MediaSelectEvent(false));
                    selectedPosition.remove(selectedPosition.indexOf(position));
                }
            });

        }
        int imageResource = 0;
        if (position % 2 == 0) {
            imageResource = R.drawable.default_image;
        } else {
            imageResource = R.drawable.default_image_2;
        }
        Picasso.with(mediaImage.getContext())
                .load(imageResource)
                .into(mediaImage);
    }

}
