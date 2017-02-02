package com.yemyatthu.lomotifmockup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Ye Myat Thu on 12/6/2016.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public abstract class BaseFragment extends Fragment {
    public View rootView;
    public Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getContentLayout(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initComponents();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public abstract int getContentLayout();

    public abstract void initComponents();
}
