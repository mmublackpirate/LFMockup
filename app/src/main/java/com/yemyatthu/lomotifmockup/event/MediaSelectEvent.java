package com.yemyatthu.lomotifmockup.event;

/**
 * Created by Ye Myat Thu on 2/1/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class MediaSelectEvent {
    public boolean isSelected;
    public int amountToDelete = 0;

    public MediaSelectEvent(boolean isSelected, int amountToDelete) {
        this.isSelected = isSelected;
        this.amountToDelete = amountToDelete;
    }

    public MediaSelectEvent(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
