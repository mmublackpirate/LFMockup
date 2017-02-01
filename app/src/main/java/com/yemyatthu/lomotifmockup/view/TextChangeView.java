package com.yemyatthu.lomotifmockup.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yemyatthu.lomotifmockup.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ye Myat Thu on 2/2/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class TextChangeView extends LinearLayout {
    @BindView(R.id.edit_text_search)
    EditText editTextSearch;
    @BindView(R.id.font_pick)
    LinearLayout fontPick;
    @BindView(R.id.color_pick)
    ImageView colorPick;

    public TextChangeView(Context context) {
        super(context);
        init(context);
    }

    public TextChangeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TextChangeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TextChangeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.layout_text_change_view, this, true);
        ButterKnife.bind(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setOnFontPickListener(OnClickListener onFontPickListener){
        fontPick.setOnClickListener(onFontPickListener);
    }

    public void setOnColorPickListener(OnClickListener onColorPickListener){
        colorPick.setOnClickListener(onColorPickListener);
    }
}
