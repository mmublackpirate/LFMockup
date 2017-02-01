package com.yemyatthu.lomotifmockup.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.yemyatthu.lomotifmockup.R;

import butterknife.ButterKnife;

/**
 * Created by Ye Myat Thu on 2/2/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class AdjustView extends LinearLayout {

    public AdjustView(Context context) {
        super(context);
        init(context);
    }

    public AdjustView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AdjustView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AdjustView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.layout_adjust_view, this, true);
        ButterKnife.bind(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}
