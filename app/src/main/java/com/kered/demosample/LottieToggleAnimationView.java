package com.kered.demosample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

/**
 * Created by derek_chang on 2017/11/8.
 */

public class LottieToggleAnimationView extends LottieAnimationView{
    private OnCheckedChangeListener mListener;
    public interface OnCheckedChangeListener {
        void onCheckedChanged(boolean isChecked);
    }

    public LottieToggleAnimationView(Context context) {
        super(context);
        init();
    }

    public LottieToggleAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LottieToggleAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setMinProgress(0.25f);
        setMaxProgress(0.75f);
        setProgress(0.25f);
        setSpeed(3.0f);
        setAnimation("toggle.json");
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAnimating())return;
                if(getProgress() == 0.25f) {
                    playAnimation();
                    if(mListener != null){
                        mListener.onCheckedChanged(true);
                    }
                } else {
                    reverseAnimation();
                    if(mListener != null){
                        mListener.onCheckedChanged(false);
                    }
                }
            }
        });
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mListener = listener;
    }
}
