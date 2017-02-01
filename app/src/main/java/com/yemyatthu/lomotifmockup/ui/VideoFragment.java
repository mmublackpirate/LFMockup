package com.yemyatthu.lomotifmockup.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yemyatthu.lomotifmockup.BaseFragment;
import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.presenter.VideoAdapter;

import butterknife.BindView;
import im.ene.toro.Toro;

/**
 * Created by Ye Myat Thu on 2/1/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class VideoFragment extends BaseFragment {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    public static VideoFragment newInstance(){
        return new VideoFragment();
    }

    public VideoFragment(){

    }
    @Override
    public int getContentLayout() {
        return R.layout.fragment_video;
    }

    @Override
    public void initComponents() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new VideoAdapter());
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (recyclerView != null) {
            if (menuVisible) {
                Toro.register(recyclerView);
            } else {
                Toro.unregister(recyclerView);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Toro.unregister(recyclerView);
    }
}
