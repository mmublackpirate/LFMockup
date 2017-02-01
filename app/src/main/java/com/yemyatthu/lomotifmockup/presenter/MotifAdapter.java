package com.yemyatthu.lomotifmockup.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yemyatthu.lomotifmockup.R;
import com.yemyatthu.lomotifmockup.holder.MediaHolder;
import com.yemyatthu.lomotifmockup.holder.MotifSectionHolder;
import com.yemyatthu.lomotifmockup.util.SectionClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ye Myat Thu on 1/31/2017.
 * Copyright © 2016 Sportsquid, Inc. All rights reserved
 */


public class MotifAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int SECTION_HEADER = 100;
    public static final int DEFAULT = 101;
    private SectionClickListener sectionClickListener;
    public List<Integer> selectedPosition = new ArrayList<>();
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==SECTION_HEADER){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_motif_section_item,parent,false);
            return new MotifSectionHolder(view);
        }else if(viewType == DEFAULT) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_media_item, parent, false);
            return new MediaHolder(view,false);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if(position%4==0){
            return SECTION_HEADER;
        }else{
            return DEFAULT;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MediaHolder)
            ((MediaHolder) holder).bind(position,selectedPosition);
        else if(holder instanceof MotifSectionHolder) {
            ((MotifSectionHolder) holder).bind(position/4,sectionClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public void setSectionClickListener(SectionClickListener sectionClickListener){
        this.sectionClickListener = sectionClickListener;
    }
}
