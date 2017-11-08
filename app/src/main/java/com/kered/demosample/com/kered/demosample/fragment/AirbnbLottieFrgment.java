package com.kered.demosample.com.kered.demosample.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.kered.demosample.LottieToggleAnimationView;
import com.kered.demosample.R;
import com.kered.dklog.DKLog;
import com.kered.dklog.Trace;

/**
 * Created by derek_chang on 2017/11/7.
 */


public class AirbnbLottieFrgment extends Fragment {
    public static final String TAG = "AirbnbLottieFrgment";
    protected View mMainLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mMainLayout = inflater.inflate(R.layout.activity_airbnb_lottie, container, false);
        initViews();
        return mMainLayout;
    }

    private void initViews() {
        LottieAnimationView animationView = mMainLayout.findViewById(R.id.animation_view);
        animationView.setAnimation("loading.json");
        animationView.loop(false);
        animationView.playAnimation();


        final LottieToggleAnimationView toggleButton = mMainLayout.findViewById(R.id.animationToggle);
        toggleButton.setOnCheckedChangeListener(new LottieToggleAnimationView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(boolean isChecked) {
                DKLog.d(TAG, Trace.getCurrentMethod() + isChecked);
            }
        });
    }
}
