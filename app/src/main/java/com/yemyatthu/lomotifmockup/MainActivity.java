package com.yemyatthu.lomotifmockup;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yemyatthu.lomotifmockup.ui.HomeActivity;
import com.yemyatthu.lomotifmockup.util.Utils;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.main_cont)
    View mainCont;
    @BindView(R.id.lomotif_icon)
    ImageView lomotifIcon;
    @BindView(R.id.lomotif_text)
    TextView lomotifText;
    @BindView(R.id.start_btn)
    TextView startBtn;
    @BindView(R.id.lomoti_cont)
    View lomotifCont;


    private void animateFromTopToBottom(View view){
        view.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            Rect myViewRect = new Rect();
            view.getGlobalVisibleRect(myViewRect);
            float y = myViewRect.top;
            view.animate().translationY(-(y+Utils.convertDpToPixel(400,this)))
                    .setDuration(100)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
//                            new Handler().postDelayed(() -> view.animate().translationY(0)
//                                    .setDuration(3000)
//                                    .setInterpolator(new BounceInterpolator())
//                                    .start(),1000);

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    })
                    .start();

        });
    }

    private void animateFromRightToLeft(View view){
        view.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            Rect myViewRect = new Rect();
            view.getGlobalVisibleRect(myViewRect);
            float x = myViewRect.right;
            view.animate().translationX(x+ Utils.convertDpToPixel(200,this))
                    .setDuration(100)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            new Handler().postDelayed(() -> view.animate().translationX(0)
                                    .setDuration(1500)
                                    .start(),4000);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    })
                    .start();

        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initComponents() {
        new Handler().postDelayed(() -> mainCont.setVisibility(View.VISIBLE),100);
        animateFromTopToBottom(lomotifCont);
        animateFromRightToLeft(startBtn);
        startBtn.setOnClickListener(v -> {
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        });
    }
}
