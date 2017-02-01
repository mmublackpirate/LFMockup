package com.yemyatthu.lomotifmockup.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yemyatthu.lomotifmockup.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ye Myat Thu on 2/2/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class FilterHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.title)
    TextView title;
    public FilterHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(int position){
        switch (position%4){
            case 0:
                title.setText("Moon");
                break;
            case 1:
                title.setText("Neon");
                break;
            case 2:
                title.setText("Grey Scale");
                break;
            case 3:
                title.setText("Hug");
                break;
        }
    }


}
