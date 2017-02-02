package com.yemyatthu.lomotifmockup.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yemyatthu.lomotifmockup.BaseFragment;
import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.event.MediaSelectEvent;
import com.yemyatthu.lomotifmockup.presenter.MediaAdapter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by Ye Myat Thu on 2/1/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class MediaFragment extends BaseFragment {
    public static String DISABLE_SELECTION = MediaFragment.class.getSimpleName() + "Disable_Selection";
    @BindView(R.id.recycler)
    RecyclerView recycler;

    private GridLayoutManager gridLayoutManager;
    private MediaAdapter mediaAdapter;

    public MediaFragment() {

    }

    public static MediaFragment newInstance(boolean disableSelection) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(DISABLE_SELECTION, disableSelection);
        MediaFragment mediaFragment = new MediaFragment();
        mediaFragment.setArguments(bundle);
        return mediaFragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_media;
    }

    @Override
    public void initComponents() {
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mediaAdapter = new MediaAdapter(getArguments().getBoolean(DISABLE_SELECTION, false));
        recycler.setLayoutManager(gridLayoutManager);
        recycler.setAdapter(mediaAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().post(new MediaSelectEvent(false, mediaAdapter.getAmountToDelete()));
    }
}
