package com.kered.demosample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.kered.demosample.com.kered.demosample.fragment.AirbnbLottieFrgment;
import com.kered.demosample.com.kered.demosample.fragment.BubbleSeekBarFrgment;
import com.kered.demosample.com.kered.demosample.fragment.LuckyDrawFrgment;
import com.kered.dklog.DKLog;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private FrameLayout mContainer;
    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Toolbar mToolbar;
    private int newValue = 500;
    private static int value = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DKLog.setEnable(true);
        Fresco.initialize(this);

        setContentView(R.layout.activity_main);
        initViews();
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_in);
    }

    private void changeFragment(int funcID, Bundle args, boolean pushStack, int containerId) {
        Class<? extends Fragment> fragmentClass = Fragment.class;
        Bundle newArgs = args;
        if (null == newArgs) {
            newArgs = new Bundle();
        }

        Intent launchFunction;
        String category;
        String action;

        switch (funcID) {
            case Config.Fragment.LottieFragId:
                fragmentClass = AirbnbLottieFrgment.class;
                break;
            case Config.Fragment.SeekBarFragId:
                fragmentClass = BubbleSeekBarFrgment.class;
                break;
            case Config.Fragment.LuckyDrawFragId:
                fragmentClass = LuckyDrawFrgment.class;
                break;
        }

        try {
            String fragmentTAG = String.valueOf(funcID);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left,
                    R.anim.slide_out_right);
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentTAG);

            // If fragment doesn't exist yet, create one
            if (fragment == null) {
                fragment = fragmentClass.newInstance();
                if (null != args) {
                    fragment.setArguments(args);
                }
                ft.add(containerId, fragment, fragmentTAG);
                if (pushStack) {
                    ft.addToBackStack(null);
                } else {
                    getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    DKLog.d(TAG, "all pop, Fragment Stack Size:" + getSupportFragmentManager().getBackStackEntryCount());
                }
            } else { // re-use the old fragment
                ft.replace(containerId, fragment, fragmentTAG);
            }

            ft.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initViews() {
        drawer = findViewById(R.id.drawer_layout);

        mContainer = findViewById(R.id.main_container);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);

        initToolBar("Airbnb");

        toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    protected void initToolBar(String title) {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            setTitle(title);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            changeFragment(Config.Fragment.LottieFragId, null, true, mContainer.getId());
        } else if (id == R.id.nav_gallery) {
            changeFragment(Config.Fragment.SeekBarFragId, null, true, mContainer.getId());
        } else if (id == R.id.nav_slideshow) {
            launchMarket();
        } else if (id == R.id.nav_manage) {
            showActivityFromBottom();
        } else if (id == R.id.nav_share) {
            changeFragment(Config.Fragment.LuckyDrawFragId, null, true, mContainer.getId());
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showActivityFromBottom(){
        Intent intent = new Intent(this, HistoryOrderListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void launchMarket() {
        try {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.hiiir.alley");
            Intent goMarket = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(goMarket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
