package com.braingroom.android.mvvmexample.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.braingroom.android.mvvmexample.R;
import com.braingroom.android.mvvmexample.utils.SplashViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeActivity extends AppCompatActivity {

    private ViewPager mPager;
    private static final Integer[] XMEN = {R.drawable.find_background, R.drawable
            .connect_background, R.drawable.catalouge_background};
    private ArrayList<Integer> imageArray = new ArrayList<>();
    private boolean isBackground = false;
    private static int currentPage = 0;
    final int SPLASH_DISPLAY_LENGTH = 3000;
    final int SPLASH_MENU_LENGTH = 9000;
    Handler handler;
    Runnable Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
    }

    private void initViews() {
        imageArray.addAll(Arrays.asList(XMEN));
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SplashViewPager(HomeActivity.this, imageArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        handler = new Handler();
        Update = new Runnable() {
            @Override
            public void run() {
                if (!isBackground) {
                    currentPage = mPager.getCurrentItem();
                    ++currentPage;
                    if (currentPage > 2)
                        currentPage = 0;
                    mPager.setCurrentItem(currentPage, true);

                }
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.postDelayed(Update, SPLASH_DISPLAY_LENGTH);
            }
        }, SPLASH_MENU_LENGTH / 3, SPLASH_MENU_LENGTH / 3);

    }

    public void goHome(View view) {
        Intent intent = new Intent(HomeActivity.this, ClassDetailActivity.class);
        startActivity(intent);
    }
}
