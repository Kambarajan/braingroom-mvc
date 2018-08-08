package com.braingroom.android.mvvmexample.utils;

import java.util.ArrayList;

/**
 * Created by android on 02/08/18.
 */

public class TopSellingResponse {
    private ArrayList<TopSellingClassData> braingroom = new ArrayList<>();

    public ArrayList<TopSellingClassData> getBraingroom() {
        return braingroom;
    }

    public void setBraingroom(ArrayList<TopSellingClassData> braingroom) {
        this.braingroom = braingroom;
    }
}
