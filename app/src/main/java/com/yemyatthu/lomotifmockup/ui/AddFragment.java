package com.yemyatthu.lomotifmockup.ui;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.yemyatthu.lomotifmockup.BaseFragment;
import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.event.MediaSelectEvent;
import com.yemyatthu.lomotifmockup.presenter.TabPagerAdapter;
import com.yemyatthu.lomotifmockup.view.SwipeDisablePager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * Created by Ye Myat Thu on 1/31/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class AddFragment extends BaseFragment {

    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.pager)
    SwipeDisablePager pager;
    @BindColor(R.color.colorPrimaryDark)
    int primarDarkColor;
    @BindView(R.id.title)
    TextView title;
    private TabPagerAdapter tabPagerAdapter;
    private OnDismissListener onDismissListener;
    private AddPhotoVideosFragment addPhotoVideosFragment;
    private AddMusicFragment addMusicFragment;
    private int selectedAmount = 0;

    public static AddFragment newInstance() {
        return new AddFragment();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_add;
    }

    @Override
    public void initComponents() {
        List<Fragment> fragments = new ArrayList<>();
        addPhotoVideosFragment = AddPhotoVideosFragment.newInstance();
        addMusicFragment = AddMusicFragment.newInstance();
        fragments.add(addPhotoVideosFragment);
        fragments.add(addMusicFragment);
        close.setOnClickListener(v -> {
            if (pager.getCurrentItem() == 0) {
                if (selectedAmount > 0) {
                    selectedAmount = 0;
                    next.setEnabled(false);
                    title.setText("Add Photos and Videos");
                }
                addPhotoVideosFragment.resetAllFragments();
                addMusicFragment.resetAllFragments();
                if (onDismissListener != null) {
                    onDismissListener.onDismiss();
                }
            } else {
                pager.setCurrentItem(pager.getCurrentItem() - 1);
                if (pager.getCurrentItem() == 0) {
                    close.setImageResource(R.drawable.ic_close);
                    title.setText(selectedAmount == 0 ? "Add Photos and Videos" : selectedAmount + " Selected ");
                }
            }
        });
        next.setOnClickListener(v -> {
            if (pager.getCurrentItem() == 0) {
                close.setImageResource(R.drawable.ic_back);
            }
            if (pager.getChildCount() - 1 > pager.getCurrentItem()) {
                pager.setCurrentItem(pager.getCurrentItem() + 1);
                if (pager.getCurrentItem() == 1) {
                    title.setText("Find Music");
                }
            } else {
                startActivity(new Intent(getActivity(), CreateVideoActivity.class));
            }
        });
        tabPagerAdapter = new TabPagerAdapter(getChildFragmentManager(), fragments, null);
        pager.setAdapter(tabPagerAdapter);
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (addPhotoVideosFragment == null || addMusicFragment == null) {
            return;
        }
        addPhotoVideosFragment.setMenuVisibility(menuVisible);
        addMusicFragment.setMenuVisibility(menuVisible);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMediaSelectEvent(MediaSelectEvent event) {
        if (event.amountToDelete > 0) {
            if (selectedAmount > event.amountToDelete) {
                selectedAmount = selectedAmount - event.amountToDelete;
            } else {
                selectedAmount = 0;
            }
            if (selectedAmount == 0) {
                next.setEnabled(false);
                title.setText("Add Photos and Vidoes");
            } else {
                title.setText(String.format("%d Selected ", selectedAmount));
            }
            return;
        }
        if (event.isSelected) {
            selectedAmount++;
            if (!next.isEnabled()) {
                next.setEnabled(true);
            }
            title.setText(String.format("%d Selected ", selectedAmount));
        } else {
            if (selectedAmount > 0)
                selectedAmount--;
            if (selectedAmount == 0) {
                next.setEnabled(false);
                title.setText("Add Photos and Vidoes");
            } else {
                title.setText(String.format("%d Selected ", selectedAmount));
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public interface OnDismissListener {
        void onDismiss();
    }
}
