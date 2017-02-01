package com.yemyatthu.lomotifmockup.util;

/**
 * Created by Ye Myat Thu on 2/1/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class MotifFragmentViewController {
    private boolean firstView = true;

    public boolean isFirstView() { return firstView;}
    public void setFirstView() { firstView = true; }
    public void setSecondView() { firstView = false; }
}
