package com.braingroom.android.mvvmexample;

import com.braingroom.android.mvvmexample.utils.CategoryData;
import com.braingroom.android.mvvmexample.utils.CategoryResponse;
import com.braingroom.android.mvvmexample.utils.RecommendedClassResponse;
import com.braingroom.android.mvvmexample.utils.TopSellingResponse;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by android on 26/07/18.
 */

public interface RequestInterface {

    @POST("viewRecommendedClass")
    Call<RecommendedClassResponse> getRecommendedClassJSON();

    @POST("viewFeaturedClass")
    Call<TopSellingResponse> getJSONData();

    @POST("getCategory")
    Call<CategoryResponse> getCategoryData();

}
