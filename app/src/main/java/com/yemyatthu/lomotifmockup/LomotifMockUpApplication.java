package com.yemyatthu.lomotifmockup;

import android.app.Application;

import im.ene.toro.Toro;

/**
 * Created by Ye Myat Thu on 2/1/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class LomotifMockUpApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Toro.init(this);
    }
}
