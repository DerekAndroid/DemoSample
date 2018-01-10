package com.kered.demosample.com.kered.demosample.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kered.demosample.R;
import com.kered.demosample.com.kered.demosample.utils.AlleyLuckyDrawAnimationUtils;

/**
 * Created by derek_chang on 2017/11/7.
 */


public class LuckyDrawFrgment extends Fragment {
    public static final String TAG = "LuckyDrawFrgment";
    protected View mMainLayout;

    private ImageView mDragonDanceImageView;
    private SimpleDraweeView mDrawBackgroundSimpleDraweeView;
    private TextView mResultTextView;
    private ImageView mCloseImageView;
    private View mDrawDialog;

    private AlleyLuckyDrawAnimationUtils animationUtils;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mMainLayout = inflater.inflate(R.layout.fragment_lucky_draw, container, false);
        initViews();
        return mMainLayout;
    }

    private void initViews() {
        mDrawBackgroundSimpleDraweeView = mMainLayout.findViewById(R.id.dragon_dance_background);
        mDragonDanceImageView = mMainLayout.findViewById(R.id.dragon_dance_imageView);
        mResultTextView = mMainLayout.findViewById(R.id.result_textView);
        mDrawDialog = mMainLayout.findViewById(R.id.lucky_draw_dialog);
        mCloseImageView = mMainLayout.findViewById(R.id.close);

        mDrawDialog.setVisibility(View.GONE);

        mDragonDanceImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationUtils.updateDrawResponse("600");
            }
        });

        mCloseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawDialog.getVisibility() == View.VISIBLE){
                    mDrawDialog.setVisibility(View.GONE);
                    Animation fadeOut = new AlphaAnimation(1, 0);
                    fadeOut.setInterpolator(new AccelerateInterpolator()); //add this
                    fadeOut.setDuration(200);
                    AnimationSet animation = new AnimationSet(false); //change to false
                    animation.addAnimation(fadeOut);
                    mDrawDialog.startAnimation(animation);
                }
            }
        });

        Button button = mMainLayout.findViewById(R.id.lucky_draw_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawDialog.getVisibility() != View.VISIBLE){
                    mDrawDialog.setVisibility(View.VISIBLE);
                    Animation fadeIn = new AlphaAnimation(0, 1);
                    fadeIn.setInterpolator(new AccelerateInterpolator()); //add this
                    fadeIn.setDuration(200);
                    AnimationSet animation = new AnimationSet(false); //change to false
                    animation.addAnimation(fadeIn);
                    mDrawDialog.startAnimation(animation);
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        /* TODO 跳出後回來的接續 */

        animationUtils = new AlleyLuckyDrawAnimationUtils(getActivity(),
                mResultTextView,
                mDragonDanceImageView,
                mDrawBackgroundSimpleDraweeView);
        animationUtils.asyncStartBackground(R.drawable.sp_bg);
        animationUtils.asyncDragonDance();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(animationUtils != null) {
            animationUtils.pause();
        }

    }
}
