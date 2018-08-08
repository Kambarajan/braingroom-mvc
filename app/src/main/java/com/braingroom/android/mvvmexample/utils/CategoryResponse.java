package com.braingroom.android.mvvmexample.utils;

import java.util.ArrayList;

/**
 * Created by android on 03/08/18.
 */

public class CategoryResponse {

    private ArrayList<CategoryData> braingroom = new ArrayList<>();

    public ArrayList<CategoryData> getBraingroom() {
        return braingroom;
    }

    public void setBraingroom(ArrayList<CategoryData> braingroom) {
        this.braingroom = braingroom;
    }
}
