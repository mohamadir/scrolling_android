package com.example.mykotlintutorial.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.M)
public class SwipableHorizontalView extends HorizontalScrollView implements View.OnScrollChangeListener {

    private boolean enableScrolling = true;
    private int left,middle,right;
    private int direction = 0; // 0 = left, 1 = middle, 2 = right
    public boolean isEnableScrolling() {
        return enableScrolling;
    }
    private boolean isScrolling = false;

    public void setEnableScrolling(boolean enableScrolling) {
        this.enableScrolling = enableScrolling;
    }

    public void setOffsets(int left, int middle, int right){
        this.left = left;
        this.middle = middle;
        this.right = right;
    }



    public SwipableHorizontalView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnScrollChangeListener(this);
    }

    public SwipableHorizontalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnScrollChangeListener(this);
    }

    public SwipableHorizontalView(Context context) {
        super(context);
        setOnScrollChangeListener(this);
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (isEnableScrolling()) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isEnableScrolling()) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public void onScrollChange(View v, final int i, int scrollY, int i2, int oldScrollY) {
        Log.i("log-offset", i2 + "");
        if (isScrolling)  {
            return;
        }

        if (direction == 0 ) { // left

            if (i > i2) { // right

                setEnableScrolling(false);
                isScrolling = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isScrolling = false;
                        direction = 1;
                        setEnableScrolling(true);
                    }
                }, 600);
                this.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollList(i , middle);

                    }
                });

            }


        } else if (direction == 1) {// center
            if (i > i2) { // right

                setEnableScrolling(false);
                isScrolling = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isScrolling = false;
                        direction = 2;
                        setEnableScrolling(true);
                    }
                }, 600);
                this.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollList(i , 1080);

                    }
                });

            } else {  // left

                setEnableScrolling(false);
                isScrolling = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isScrolling = false;
                        direction = 0;
                        setEnableScrolling(true);
                    }
                }, 600);
                this.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollList(i , left);

                    }
                });


            }

        } else { // right
            if (i < i2) { // left

                setEnableScrolling(false);
                isScrolling = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isScrolling = false;
                        direction = 1;
                        setEnableScrolling(true);
                    }
                }, 600);
                this.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollList(i , middle);

                    }
                });
            }

        }
    }


    private void  scrollList (int from, int to) {
        ValueAnimator realSmoothScrollAnimation =
                ValueAnimator.ofInt(from, to);
        realSmoothScrollAnimation.setDuration(400);
        realSmoothScrollAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int scrollTo = (int ) animation.getAnimatedValue();
                smoothScrollTo(scrollTo, 0);
            }
        });
        realSmoothScrollAnimation.start();
    }


}
