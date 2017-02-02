package com.yemyatthu.lomotifmockup.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.holder.MediaHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ye Myat Thu on 2/1/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class MediaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private boolean disableSelection = false;
    private List<Integer> selectedPosition = new ArrayList<>();

    public MediaAdapter() {

    }

    public MediaAdapter(boolean disableSelection) {
        this.disableSelection = disableSelection;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_media_item, parent, false);
        return new MediaHolder(view, disableSelection);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MediaHolder) {

            ((MediaHolder) holder).bind(position, selectedPosition);
        }

    }

    public int getAmountToDelete() {
        return selectedPosition.size();
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}