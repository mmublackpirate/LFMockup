package com.yemyatthu.lomotifmockup.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.yemyatthu.lomotifmockup.R;

/**
 * Created by Ye Myat Thu on 2/2/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class AddPhotoVideoView extends FrameLayout {
    public AddPhotoVideoView(Context context) {
        super(context);
        init(context);
    }

    public AddPhotoVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AddPhotoVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AddPhotoVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.layout_add_photo_video_view, this, true);
    }
}
