package com.yemyatthu.lomotifmockup.event;

import android.support.v4.app.Fragment;

/**
 * Created by Ye Myat Thu on 2/1/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class MusicEvent {
    public Fragment fragment;
    public MusicEvent(Fragment fragment){
        this.fragment = fragment;
    }
}
