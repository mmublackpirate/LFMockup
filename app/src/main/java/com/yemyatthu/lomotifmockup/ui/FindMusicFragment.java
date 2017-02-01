package com.yemyatthu.lomotifmockup.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yemyatthu.lomotifmockup.BaseFragment;
import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.presenter.MusicAdapter;

import butterknife.BindView;

/**
 * Created by Ye Myat Thu on 2/1/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class FindMusicFragment extends BaseFragment {
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.edit_text_search)
    EditText editTextSearch;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.main_cont)
    LinearLayout mainCont;

    private MusicAdapter findMusicAdapter;

    public static FindMusicFragment newInstance(){
        return new FindMusicFragment();
    }

    public FindMusicFragment(){}

    @Override
    public int getContentLayout() {
        return R.layout.fragment_find_music;
    }

    @Override
    public void initComponents() {
        findMusicAdapter = new MusicAdapter(getActivity(),this);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(findMusicAdapter);
    }

    public void resetFragment(){
        if(findMusicAdapter.selectedPosition != -1){
            int tempPosition = findMusicAdapter.selectedPosition;
            findMusicAdapter.selectedPosition = -1;
            findMusicAdapter.notifyItemChanged(tempPosition);
        }
        if(findMusicAdapter.mediaPlayer!=null && findMusicAdapter.mediaPlayer.isPlaying()) {
            findMusicAdapter.mediaPlayer.stop();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(findMusicAdapter.mediaPlayer!=null && findMusicAdapter.mediaPlayer.isPlaying()) {
            findMusicAdapter.mediaPlayer.stop();
        }
    }
}
