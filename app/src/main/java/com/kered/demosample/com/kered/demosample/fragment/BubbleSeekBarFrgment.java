package com.kered.demosample.com.kered.demosample.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.kered.demosample.R;
import com.kered.dklog.DKLog;
import com.kered.dklog.Trace;
import com.xw.repo.BubbleSeekBar;

/**
 * Created by derek_chang on 2017/11/7.
 */

/* reference
* https://github.com/woxingxiao/BubbleSeekBar/blob/master/README_zh.md
* */
public class BubbleSeekBarFrgment extends Fragment {
    public static final String TAG = "BubbleSeekBarFrgment";
    protected View mMainLayout;
    private BubbleSeekBar mSeekBar1;
    private BubbleSeekBar mSeekBar2;
    private ImageView mGifImageView;
    private ImageView mGifImageView1;
    private ImageView mGifImageView2;
    private ImageView mGifImageView3;
    private SimpleDraweeView mSimpleDraweeView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mMainLayout = inflater.inflate(R.layout.fragment_seekbar, container, false);
        initViews();
        return mMainLayout;
    }

    private void initViews() {
        mSeekBar1 = mMainLayout.findViewById(R.id.seekBar1);
        mSeekBar2 = mMainLayout.findViewById(R.id.seekBar2);
        mGifImageView = mMainLayout.findViewById(R.id.gif_imageView);
        mGifImageView1 = mMainLayout.findViewById(R.id.gif_imageView1);
        mGifImageView2 = mMainLayout.findViewById(R.id.gif_imageView2);
        mGifImageView3 = mMainLayout.findViewById(R.id.gif_imageView3);
        mSimpleDraweeView = mMainLayout.findViewById(R.id.gif_simpleDraweeView);


        mSeekBar1.getConfigBuilder()
                .min(1.0f)
                .max(5.0f)
                .progress(5)
                .floatType()
                .sectionCount(8)
                .showSectionMark()
                .autoAdjustSectionMark()
                .alwaysShowBubble()
                .sectionTextInterval(2)
                .build();

        mSeekBar2.getConfigBuilder()
                .min(0.0f)
                .max(50)
                .progress(20)
                .sectionCount(5)
                .trackColor(ContextCompat.getColor(getContext(), R.color.color_gray))
                .secondTrackColor(ContextCompat.getColor(getContext(), R.color.color_blue))
                .thumbColor(ContextCompat.getColor(getContext(), R.color.color_blue))
                .showSectionText()
                .sectionTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary))
                .sectionTextSize(18)
                .showThumbText()
                .thumbTextColor(ContextCompat.getColor(getContext(), R.color.color_red))
                .thumbTextSize(18)
                .bubbleColor(ContextCompat.getColor(getContext(), R.color.color_green))
                .bubbleTextSize(18)
                .showSectionMark()
                .seekBySection()
                .autoAdjustSectionMark()
                .sectionTextPosition(BubbleSeekBar.TextPosition.BELOW_SECTION_MARK)
                .build();

        //GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(mGifImageView);
        Glide.with(this).load(R.drawable.animate_exchanged).asGif().into(mGifImageView);

        GlideDrawableImageViewTarget imageViewTarget1 = new GlideDrawableImageViewTarget(mGifImageView1);
        Glide.with(this).load(R.raw.animate_loading_x).into(imageViewTarget1);

        GlideDrawableImageViewTarget imageView2Target = new GlideDrawableImageViewTarget(mGifImageView2);
        Glide.with(this).load(R.raw.animate_loading_xx).into(imageView2Target);

        GlideDrawableImageViewTarget imageView3Target = new GlideDrawableImageViewTarget(mGifImageView3);
        Glide.with(this).load(R.raw.animate_loading_xxx).into(imageView3Target);

        updateCustomGidView(R.drawable.animate_exchanged_1206);

    }

    private void updateCustomGidView(int resId){
        BitmapFactory.Options dimensions = new BitmapFactory.Options();
        dimensions.inJustDecodeBounds = true;
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), resId, dimensions);
        int height = dimensions.outHeight;
        int width =  dimensions.outWidth;

        DKLog.d(TAG, Trace.getCurrentMethod() + height + " , " + width);

        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithResourceId(resId).build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(imageRequest.getSourceUri())
                .setAutoPlayAnimations(true)
                .build();
        ViewGroup.LayoutParams lp = mSimpleDraweeView.getLayoutParams();
        lp.height = height;
        lp.width = width;
        mSimpleDraweeView.setLayoutParams(lp);
        mSimpleDraweeView.setController(controller);
    }

}
