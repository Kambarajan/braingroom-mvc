package com.braingroom.android.mvvmexample.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.braingroom.android.mvvmexample.R;

import java.util.ArrayList;

/**
 * Created by android on 06/08/18.
 */

public class SplashViewPager extends PagerAdapter {
    private ArrayList<Integer> images;
    private ArrayList<String> text;
    private LayoutInflater inflater;
    private Context context;

    public SplashViewPager(Context context, ArrayList<Integer> images) {
        this.images = images;
        this.context = context;
        this.text = text;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.splash_slide, view, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image);
        myImage.setImageResource(images.get(position));
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }
}
