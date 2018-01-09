package com.kered.demosample;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;

/**
 * Created by martin_wu on 2016/12/21.
 */

public abstract class AlleyAnimationDrawable extends AnimationDrawable {

    Handler mAnimationHandler;
    Handler mShowMoneyHandler;

    public AlleyAnimationDrawable(AnimationDrawable aniDrawable) {
        for (int i = 0; i < aniDrawable.getNumberOfFrames(); i++) {
            this.addFrame(aniDrawable.getFrame(i), aniDrawable.getDuration(i));
        }
    }

    @Override
    public void start() {
        super.start();
        mAnimationHandler = new Handler();
        mAnimationHandler.postDelayed(new Runnable() {

            public void run() {
                onAnimationFinish();
            }
        }, getTotalDuration());

        mShowMoneyHandler = new Handler();
        mShowMoneyHandler.postDelayed(new Runnable() {

            public void run() {
                onGiftMoneyShowing();
            }
        }, getShowTextDuration());

    }
    public int getTotalDuration() {

        int Duration = 0;

        for (int i = 0; i < this.getNumberOfFrames(); i++) {
            Duration += this.getDuration(i);
        }

        return Duration;
    }
    public int getShowTextDuration() {

        int Duration = 0;
        int ShowFrame = this.getNumberOfFrames()-3;
        for (int i = 0; i < ShowFrame; i++) {
            Duration += this.getDuration(i);
        }

        return Duration;
    }

    protected abstract void onAnimationFinish();
    protected abstract void onGiftMoneyShowing();
}