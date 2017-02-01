package com.yemyatthu.lomotifmockup.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.yemyatthu.lomotifmockup.BaseFragment;
import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.presenter.NotificationAdapter;

import butterknife.BindView;

/**
 * Created by Ye Myat Thu on 2/1/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class NotificationsFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    private LinearLayoutManager linearLayoutManager;
    private NotificationAdapter notificationAdapter;
    public static NotificationsFragment newInstance(){
        return new NotificationsFragment();
    }

    public NotificationsFragment(){

    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_notifications;
    }

    @Override
    public void initComponents() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        notificationAdapter = new NotificationAdapter();
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(notificationAdapter);
    }
}
