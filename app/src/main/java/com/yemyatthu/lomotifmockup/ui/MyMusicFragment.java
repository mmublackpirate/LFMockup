package com.yemyatthu.lomotifmockup.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yemyatthu.lomotifmockup.BaseFragment;
import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.presenter.MusicAdapter;

import butterknife.BindView;

/**
 * Created by Ye Myat Thu on 2/1/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class MyMusicFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private MusicAdapter myMusicAdapter;

    public static MyMusicFragment newInstance(){
        return new MyMusicFragment();
    }

    public MyMusicFragment(){}

    @Override
    public int getContentLayout() {
        return R.layout.fragment_my_music;
    }

    @Override
    public void initComponents() {
        myMusicAdapter = new MusicAdapter(getActivity(),this);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(myMusicAdapter);
    }

    public void resetFragment(){
        if(myMusicAdapter.selectedPosition != -1){
            int tempPosition = myMusicAdapter.selectedPosition;
            myMusicAdapter.selectedPosition = -1;
            myMusicAdapter.notifyItemChanged(tempPosition);
        }
        if(myMusicAdapter.mediaPlayer!=null && myMusicAdapter.mediaPlayer.isPlaying()) {
            myMusicAdapter.mediaPlayer.stop();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(myMusicAdapter.mediaPlayer!=null && myMusicAdapter.mediaPlayer.isPlaying()) {
            myMusicAdapter.mediaPlayer.stop();
        }

    }
}
