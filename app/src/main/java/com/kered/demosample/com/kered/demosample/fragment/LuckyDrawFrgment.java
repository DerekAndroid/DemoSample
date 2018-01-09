package com.kered.demosample.com.kered.demosample.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
    private SimpleDraweeView mSimpleDraweeView;
    private AlleyLuckyDrawAnimationUtils animationUtils;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mMainLayout = inflater.inflate(R.layout.fragment_lucky_draw, container, false);
        initViews();
        return mMainLayout;
    }

    private void initViews() {
        mSimpleDraweeView = mMainLayout.findViewById(R.id.gif_simpleDraweeView);
        mDragonDanceImageView = mMainLayout.findViewById(R.id.dragon_dance_imageView);




        mDragonDanceImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationUtils.onResponse();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        /* TODO 跳出後回來的接續 */

        animationUtils = new AlleyLuckyDrawAnimationUtils(getActivity(), mDragonDanceImageView, mSimpleDraweeView);
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
