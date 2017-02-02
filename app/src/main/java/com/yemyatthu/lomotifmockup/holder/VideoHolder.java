package com.yemyatthu.lomotifmockup.holder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yemyatthu.lomotifmockup.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.ene.toro.exoplayer2.ExoVideoView;
import im.ene.toro.exoplayer2.ExoVideoViewHolder;

/**
 * Created by Ye Myat Thu on 1/31/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class VideoHolder extends ExoVideoViewHolder {
    @BindView(R.id.thumbnail)
    ImageView thumbnail;
    private String mediaUri;

    public VideoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(RecyclerView.Adapter adapter, @Nullable Object object) {
        if (object instanceof String) {
            mediaUri = (String) object;
            videoView.setMedia(Uri.parse(mediaUri));
        }


    }

    @Override
    protected ExoVideoView findVideoView(View itemView) {

        return (ExoVideoView) itemView.findViewById(R.id.video);
    }


    @Nullable
    @Override
    public String getMediaId() {
        return mediaUri + "@" + getAdapterPosition();
    }

    @Override
    public void onViewHolderBound() {
        super.onViewHolderBound();
        Picasso.with(itemView.getContext())
                .load(R.drawable.safesound)
                .fit()
                .centerInside()
                .into(thumbnail);
    }

    @Override
    public void onPlaybackStarted() {
        // Use animation to have a fancy UI
        thumbnail.animate().alpha(0.f).setDuration(250).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // Call from here, not outside
                VideoHolder.super.onPlaybackStarted();
            }
        }).start();
    }

    @Override
    public void onPlaybackPaused() {
        // Use animation to have a fancy UI
        thumbnail.animate().alpha(1.f).setDuration(250).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // Call from here, not outside
                VideoHolder.super.onPlaybackPaused();
            }
        }).start();
    }

    @Override
    public void onPlaybackCompleted() {
        // Use animation to have a fancy UI
        thumbnail.animate().alpha(1.f).setDuration(250).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // Call from here, not outside
                VideoHolder.super.onPlaybackCompleted();
            }
        }).start();

    }

    @Override
    public boolean onPlaybackError(Exception error) {
        // Use animation to have a fancy UI
        thumbnail.animate().alpha(1.f).setDuration(0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // Finish immediately, nothing to do.
            }
        }).start();
        return super.onPlaybackError(error);
    }
}
