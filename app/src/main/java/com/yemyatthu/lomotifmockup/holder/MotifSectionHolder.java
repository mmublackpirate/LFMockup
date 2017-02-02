package com.yemyatthu.lomotifmockup.holder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.util.SectionClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ye Myat Thu on 1/31/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class MotifSectionHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.title)
    TextView motifTitle;

    public MotifSectionHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(int position, SectionClickListener sectionClickListener) {
        switch (position % 4) {
            case 0:
                motifTitle.setText("tweak");
                motifTitle.setTextColor(Color.BLUE);
                break;
            case 1:
                motifTitle.setText("nature");
                motifTitle.setTextColor(Color.MAGENTA);
                break;
            case 2:
                motifTitle.setText("smoke");
                motifTitle.setTextColor(Color.GREEN);
                break;
            case 3:
                motifTitle.setText("dance");
                motifTitle.setTextColor(Color.RED);
                break;
        }
        motifTitle.setOnClickListener(v -> {
            if (sectionClickListener != null)
                sectionClickListener.onSectionClick(motifTitle.getText().toString(), motifTitle.getCurrentTextColor());
        });
    }
}
