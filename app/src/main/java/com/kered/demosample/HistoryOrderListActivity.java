package com.kered.demosample;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * Created by derek_chang on 2017/12/29.
 */

public class HistoryOrderListActivity extends AppCompatActivity {
    private Toolbar mToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initToolBar("");
        setupWindow();
        setupWindowAnimations();
    }

    protected void initToolBar(String title) {
        //setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.hide();
            //setTitle(title);
        }
    }

    public void setupWindow(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.height = (int)(metrics.heightPixels * 407/667);
        lp.dimAmount = 102f/256f;
        lp.gravity = Gravity.BOTTOM;

        setFinishOnTouchOutside(true);
    }

    private void setupWindowAnimations() {
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
    }

    public void initViews(){
        setContentView(R.layout.activity_history_dialog);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
    }

}
