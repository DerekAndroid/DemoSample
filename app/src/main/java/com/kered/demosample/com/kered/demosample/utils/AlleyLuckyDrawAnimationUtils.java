package com.kered.demosample.com.kered.demosample.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.kered.demosample.FrameAnimation;
import com.kered.demosample.R;
import com.kered.dklog.DKLog;
import com.kered.dklog.Trace;

/**
 * Created by derek_chang on 2018/1/9.
 */

public class AlleyLuckyDrawAnimationUtils {
    public static final String TAG = AlleyLuckyDrawAnimationUtils.class.getSimpleName();

    private Context mContext;
    private ImageView mAnimationImageView;
    private SimpleDraweeView mBackgroundSimpleDraweeView;
    private boolean isResponse = false;

    private FrameAnimation startAnimation;
    private FrameAnimation repeatAnimation;
    private FrameAnimation endAnimation;


    public AlleyLuckyDrawAnimationUtils(Context context, ImageView animationImageView, SimpleDraweeView backgroundSimpleDraweeView) {
        mContext = context;
        mAnimationImageView = animationImageView;
        mBackgroundSimpleDraweeView = backgroundSimpleDraweeView;
    }

    public void asyncDragonDance() {
        int[] startArray = {
                R.drawable.sp_lion_1,
                R.drawable.sp_lion_2,
                R.drawable.sp_lion_3};

        ViewGroup.LayoutParams lp = mAnimationImageView.getLayoutParams();
        lp.height = (int) (1142 * 0.67);
        lp.width = (int) (1440 * 0.67);
        DKLog.d(TAG, Trace.getCurrentMethod() + lp.width + " , " + lp.height);
        mAnimationImageView.setLayoutParams(lp);

        startAnimation = new FrameAnimation(mAnimationImageView, startArray, 200, false);
        startAnimation.setAnimationListener(new FrameAnimation.AnimationListener() {
            @Override
            public void onAnimationStart() {
                DKLog.d(TAG, Trace.getCurrentMethod());
            }

            @Override
            public void onAnimationEnd() {
                DKLog.d(TAG, Trace.getCurrentMethod());
                repeat();
            }

            @Override
            public void onAnimationRepeat() {
                DKLog.d(TAG, Trace.getCurrentMethod());
            }

            @Override
            public void onProgress(int progress) {
                DKLog.d(TAG, Trace.getCurrentMethod() + progress);
            }
        });
    }

    public void onResponse() {
        isResponse = true;
    }

    private void repeat() {
        int[] repeatArray = {
                R.drawable.sp_lion_2,
                R.drawable.sp_lion_3};

        repeatAnimation = new FrameAnimation(mAnimationImageView, repeatArray, 200, true);
        repeatAnimation.setAnimationListener(new FrameAnimation.AnimationListener() {
            @Override
            public void onAnimationStart() {
                DKLog.d(TAG, Trace.getCurrentMethod());
            }

            @Override
            public void onAnimationEnd() {
                DKLog.d(TAG, Trace.getCurrentMethod());
            }

            @Override
            public void onAnimationRepeat() {
                DKLog.d(TAG, Trace.getCurrentMethod());
                /* TODO waiting for api response */
                if (isResponse) {
                    repeatAnimation.pauseAnimation();
                    end();
                }
            }

            @Override
            public void onProgress(int progress) {
                DKLog.d(TAG, Trace.getCurrentMethod() + progress);
            }
        });
    }

    private void end() {
        int[] endArray = {
                R.drawable.sp_lion_4,
                R.drawable.sp_lion_5};

        endAnimation = new FrameAnimation(mAnimationImageView, endArray, 200, false);
        endAnimation.setAnimationListener(new FrameAnimation.AnimationListener() {
            @Override
            public void onAnimationStart() {
                DKLog.d(TAG, Trace.getCurrentMethod());
            }

            @Override
            public void onAnimationEnd() {
                DKLog.d(TAG, Trace.getCurrentMethod());
            }

            @Override
            public void onAnimationRepeat() {
                DKLog.d(TAG, Trace.getCurrentMethod());
            }

            @Override
            public void onProgress(int progress) {
                DKLog.d(TAG, Trace.getCurrentMethod() + progress);
            }
        });
    }

    public void asyncStartBackground(int resId) {
        BitmapFactory.Options dimensions = new BitmapFactory.Options();
        dimensions.inJustDecodeBounds = true;
        Bitmap mBitmap = BitmapFactory.decodeResource(mContext.getResources(), resId, dimensions);
        int height = dimensions.outHeight;
        int width = dimensions.outWidth;

        DKLog.d(TAG, Trace.getCurrentMethod() + width + " , " + height);

        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithResourceId(resId).build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(imageRequest.getSourceUri())
                .setAutoPlayAnimations(true)
                .build();
        ViewGroup.LayoutParams lp = mBackgroundSimpleDraweeView.getLayoutParams();
        lp.height = height;
        lp.width = width;

        DKLog.d(TAG, Trace.getCurrentMethod() + lp.width + " , " + lp.height);

        mBackgroundSimpleDraweeView.setLayoutParams(lp);
        mBackgroundSimpleDraweeView.setController(controller);

    }

    public void pause() {
        if(startAnimation != null) {
            startAnimation.pauseAnimation();
        }

        if(repeatAnimation != null) {
            repeatAnimation.pauseAnimation();
        }

        if(endAnimation != null) {
            endAnimation.pauseAnimation();
        }
    }


}
