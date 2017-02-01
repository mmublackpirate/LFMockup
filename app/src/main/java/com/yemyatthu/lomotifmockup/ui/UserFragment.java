package com.yemyatthu.lomotifmockup.ui;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yemyatthu.lomotifmockup.BaseFragment;
import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.presenter.TabPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ye Myat Thu on 2/1/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class UserFragment extends BaseFragment {
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.info_cont)
    LinearLayout infoCont;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.setting)
    ImageView setting;
    @BindView(R.id.follow)
    ImageView follow;

    private TabPagerAdapter tabPagerAdapter;
    public static UserFragment newInstance(){
        return new UserFragment();
    }

    public UserFragment(){

    }
    @Override
    public int getContentLayout() {
        return R.layout.fragment_user;
    }

    @Override
    public void initComponents() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(MediaFragment.newInstance(true));
        fragmentList.add(MediaFragment.newInstance(true));
        List<String> titles = new ArrayList<>();
        titles.add("In Progress");
        titles.add("Lomotifs");
        tabPagerAdapter = new TabPagerAdapter(getChildFragmentManager(), fragmentList, titles);
        pager.setAdapter(tabPagerAdapter);
        tab.setupWithViewPager(pager);
        setting.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(),SettingsActivity.class));
        });
        follow.setOnClickListener(v -> startActivity(new Intent(getActivity(),FriendsActivity.class)));
    }
}
