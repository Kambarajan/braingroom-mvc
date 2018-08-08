package com.braingroom.android.mvvmexample.utils;

import java.util.ArrayList;

/**
 * Created by android on 26/07/18.
 */

public class RecommendedClassResponse {
    private ArrayList<ClassData> braingroom = new ArrayList<>();

    public ArrayList<ClassData> getBraingroom() {
        return braingroom;
    }

    public void setBraingroom(ArrayList<ClassData> braingroom) {
        this.braingroom = braingroom;
    }

}
