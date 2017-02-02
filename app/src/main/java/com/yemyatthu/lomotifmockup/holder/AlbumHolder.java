package com.yemyatthu.lomotifmockup.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.util.AlbumClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ye Myat Thu on 1/31/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class AlbumHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.album_image)
    ImageView albumImage;
    private int position;
    private int spanCount;

    public AlbumHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(int position, AlbumClickListener albumClickListener) {
        int imageResource = 0;
        if (position % 2 == 0) {
            imageResource = R.drawable.default_image;
        } else {
            imageResource = R.drawable.default_image_2;
        }
        itemView.setOnClickListener(v -> {
            if (albumClickListener != null) {
                albumClickListener.onAlbumClick();
            }
        });
    }
}
