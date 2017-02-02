package com.yemyatthu.lomotifmockup.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yemyatthu.lomotifmockup.R;

import static butterknife.ButterKnife.findById;

/**
 * Created by Ye Myat Thu on 1/31/2017.
 */


public class CircleGuideView extends LinearLayout {


    private ProgressBar firstProgress;
    private ProgressBar secondProgress;
    private TextView pickVideo;
    private TextView pickMusic;
    private TextView makeVideo;

    public CircleGuideView(Context context) {
        super(context);
        init(context);
    }

    public CircleGuideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleGuideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleGuideView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_guide_circle, this, true);
        firstProgress = findById(this, R.id.first_progress);
        secondProgress = findById(this, R.id.second_progress);
        pickVideo = findById(this, R.id.pick_video);
        pickMusic = findById(this, R.id.pick_music);
        makeVideo = findById(this, R.id.make_video);

        firstProgress.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        secondProgress.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        firstProgress.setMax(100000);
        secondProgress.setMax(100000);

        ObjectAnimator pickVideoAnimator = ObjectAnimator.ofFloat(pickVideo, "alpha", 0f, 1f)
                .setDuration(800);
        ObjectAnimator firstProgressAnimator = ObjectAnimator.ofInt(firstProgress, "progress", 0, 100000)
                .setDuration(500);
        ObjectAnimator pickMusicAnimator = ObjectAnimator.ofFloat(pickMusic, "alpha", 0f, 1f)
                .setDuration(800);
        ObjectAnimator secondProgressAnimator = ObjectAnimator.ofInt(secondProgress, "progress", 0, 100000)
                .setDuration(500);
        ObjectAnimator makeVideoAnimator = ObjectAnimator.ofFloat(makeVideo, "alpha", 0f, 1f)
                .setDuration(800);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setStartDelay(2000);
        animatorSet.playSequentially(pickVideoAnimator, firstProgressAnimator, pickMusicAnimator, secondProgressAnimator, makeVideoAnimator);
        animatorSet.start();

    }

}
