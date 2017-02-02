package com.yemyatthu.lomotifmockup.util;

public class AlbumFragmentViewController {
    private boolean firstView = true;

    public boolean isFirstView() {
        return firstView;
    }

    public void setFirstView() {
        firstView = true;
    }

    public void setSecondView() {
        firstView = false;
    }
} 