package com.yemyatthu.lomotifmockup.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Ye Myat Thu on 1/22/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class SwipeDisablePager extends ViewPager {
    public SwipeDisablePager(Context context) {
        super(context);
    }

    public SwipeDisablePager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return false;
    }
}
