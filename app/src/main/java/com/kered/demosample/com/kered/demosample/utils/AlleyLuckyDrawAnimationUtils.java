package com.kered.demosample.com.kered.demosample.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    private TextView mResultTextView;
    private ImageView mAnimationImageView;
    private SimpleDraweeView mBackgroundSimpleDraweeView;
    private boolean isResponse = false;

    private FrameAnimation mStartAnimation;
    private FrameAnimation mRepeatAnimation;
    private FrameAnimation mEndAnimation;

    private String mDrawResult;

    public AlleyLuckyDrawAnimationUtils(Context context,
                                        TextView resultTextView,
                                        ImageView animationImageView,
                                        SimpleDraweeView backgroundSimpleDraweeView) {
        mContext = context;
        mResultTextView = resultTextView;
        mAnimationImageView = animationImageView;
        mBackgroundSimpleDraweeView = backgroundSimpleDraweeView;
    }

    public void asyncDragonDance() {
        int[] startArray = {
                R.drawable.sp_lion_1,
                R.drawable.sp_lion_2,
                R.drawable.sp_lion_3};

        int[] durationArray = {
                2000,
                500,
                500
        };
//        ViewGroup.LayoutParams lp = mAnimationImageView.getLayoutParams();
//        lp.height = 200;//(int) (1142 * 0.67);
//        lp.width = 200;//(int) (1440 * 0.67);
//        DKLog.d(TAG, Trace.getCurrentMethod() + lp.width + " , " + lp.height);
//        mAnimationImageView.setLayoutParams(lp);

        mStartAnimation = new FrameAnimation(mAnimationImageView, startArray, durationArray, false);
        mStartAnimation.setAnimationListener(new FrameAnimation.AnimationListener() {
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

    public void updateDrawResponse(String result) {
        isResponse = true;
        mDrawResult = result;
        mResultTextView.setText(result);
    }

    private void repeat() {
        int[] repeatArray = {
                R.drawable.sp_lion_2,
                R.drawable.sp_lion_3};

        mRepeatAnimation = new FrameAnimation(mAnimationImageView, repeatArray, 500, true);
        mRepeatAnimation.setAnimationListener(new FrameAnimation.AnimationListener() {
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
                    mRepeatAnimation.pauseAnimation();
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

        mEndAnimation = new FrameAnimation(mAnimationImageView, endArray, 500, false);
        mEndAnimation.setAnimationListener(new FrameAnimation.AnimationListener() {
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
        if(mStartAnimation != null) {
            mStartAnimation.pauseAnimation();
        }

        if(mRepeatAnimation != null) {
            mRepeatAnimation.pauseAnimation();
        }

        if(mEndAnimation != null) {
            mEndAnimation.pauseAnimation();
        }
    }


}
