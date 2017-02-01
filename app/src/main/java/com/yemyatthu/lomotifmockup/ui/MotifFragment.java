package com.yemyatthu.lomotifmockup.ui;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yemyatthu.lomotifmockup.BaseFragment;
import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.event.MotifEvent;
import com.yemyatthu.lomotifmockup.presenter.MotifAdapter;
import com.yemyatthu.lomotifmockup.util.MotifFragmentViewController;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * Created by Ye Myat Thu on 1/31/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class MotifFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.edit_text_search)
    EditText editTextSearch;
    @BindView(R.id.main_cont)
    LinearLayout mainCont;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.fragment_cont)
    FrameLayout fragmentCont;
    @BindView(R.id.side_cont)
    LinearLayout sideCont;
    private GridLayoutManager gridLayoutManager;
    private MotifAdapter motifAdapter;
    private MotifFragmentViewController motifFragmentViewController;
    public static MotifFragment newInstance(){
        return new MotifFragment();
    }
    public MotifFragment(){

    }
    @Override
    public int getContentLayout() {
        return R.layout.fragment_motif;
    }

    @Override
    public void initComponents() {
        if(getActivity() instanceof HomeActivity){
            motifFragmentViewController = ((HomeActivity) getActivity()).motifFragmentViewController;
        }
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 4 == 0) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });
        motifAdapter = new MotifAdapter();
        motifAdapter.setSectionClickListener((sectionTitle, color) -> {
            if(mainCont.getVisibility()==View.VISIBLE)
                mainCont.setVisibility(View.GONE);
            motifFragmentViewController.setSecondView();
            sideCont.setVisibility(View.VISIBLE);
            Fragment mediaFragment = MediaFragment.newInstance(false);
            title.setText(sectionTitle);
            title.setBackgroundColor(color);
            title.setOnClickListener(v -> {
                getChildFragmentManager().beginTransaction().remove(mediaFragment).commit();
                sideCont.setVisibility(View.GONE);
                mainCont.setVisibility(View.VISIBLE);
                motifFragmentViewController.setFirstView();
            });
            getChildFragmentManager().beginTransaction().replace(R.id.fragment_cont,mediaFragment).commit();
        });
        recycler.setLayoutManager(gridLayoutManager);
        recycler.setAdapter(motifAdapter);
    }
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if(mainCont==null){
            return;
        }
        if(menuVisible){
            if(mainCont.getVisibility() == View.VISIBLE){
                motifFragmentViewController.setFirstView();
            }else{
                motifFragmentViewController.setSecondView();
            }
        }else{
            motifFragmentViewController.setFirstView();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMotifEvent(MotifEvent event){
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

    public void resetFragment(){
        title.callOnClick();
        motifAdapter.selectedPosition.clear();
        motifAdapter.notifyDataSetChanged();
    }
}
