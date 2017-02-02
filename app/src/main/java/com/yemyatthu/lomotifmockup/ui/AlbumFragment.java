package com.yemyatthu.lomotifmockup.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yemyatthu.lomotifmockup.BaseFragment;
import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.event.AlbumEvent;
import com.yemyatthu.lomotifmockup.presenter.AlbumAdapter;
import com.yemyatthu.lomotifmockup.util.AlbumFragmentViewController;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * Created by Ye Myat Thu on 1/31/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class AlbumFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.fragment_cont)
    FrameLayout fragmentCont;
    @BindView(R.id.side_cont)
    LinearLayout sideCont;

    private LinearLayoutManager linearLayoutManager;
    private AlbumAdapter albumAdapter;
    private AlbumFragmentViewController albumFragmentViewController;

    public AlbumFragment() {

    }

    public static AlbumFragment newInstance() {
        return new AlbumFragment();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_album;
    }

    @Override
    public void initComponents() {
        if (getActivity() instanceof HomeActivity) {
            albumFragmentViewController = ((HomeActivity) getActivity()).albumFragmentViewController;
        }
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(linearLayoutManager);
        albumAdapter = new AlbumAdapter();
        albumAdapter.setAlbumClickListener(() -> {
            if (recycler.getVisibility() == View.VISIBLE)
                recycler.setVisibility(View.GONE);
            if (albumFragmentViewController != null) {
                albumFragmentViewController.setSecondView();
            }
            sideCont.setVisibility(View.VISIBLE);
            MediaFragment mediaFragment = MediaFragment.newInstance(false);
            getChildFragmentManager().beginTransaction().replace(R.id.fragment_cont, mediaFragment).commit();
            title.setOnClickListener(v -> {
                getChildFragmentManager().beginTransaction().remove(mediaFragment).commit();
                sideCont.setVisibility(View.GONE);
                recycler.setVisibility(View.VISIBLE);
                if (albumFragmentViewController != null) {
                    albumFragmentViewController.setFirstView();
                }
            });
        });
        recycler.setAdapter(albumAdapter);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (recycler == null) {
            return;
        }
        if (menuVisible) {
            if (recycler.getVisibility() == View.VISIBLE) {
                albumFragmentViewController.setFirstView();
            } else {
                albumFragmentViewController.setSecondView();
            }
        } else {
            albumFragmentViewController.setFirstView();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAlbumEvent(AlbumEvent event) {
        title.callOnClick();
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    public void resetFragment() {
        title.callOnClick();
    }
}
