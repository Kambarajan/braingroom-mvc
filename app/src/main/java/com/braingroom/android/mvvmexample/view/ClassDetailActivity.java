package com.braingroom.android.mvvmexample.view;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.braingroom.android.mvvmexample.R;
import com.braingroom.android.mvvmexample.RequestInterface;
import com.braingroom.android.mvvmexample.utils.CategoryData;
import com.braingroom.android.mvvmexample.utils.CategoryResponse;
import com.braingroom.android.mvvmexample.utils.ClassData;
import com.braingroom.android.mvvmexample.utils.RecommendedClassResponse;
import com.braingroom.android.mvvmexample.utils.TopSellingClassData;
import com.braingroom.android.mvvmexample.utils.TopSellingResponse;
import com.braingroom.android.mvvmexample.view.adapter.DataAdapter;
import com.braingroom.android.mvvmexample.view.adapter.ImageAdapter;
import com.braingroom.android.mvvmexample.view.adapter.TopSellingClassAdapter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClassDetailActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private RecyclerView recyclerView;
    private RecyclerView topSellingRecyclerView;
    private GridView gridView;
    private ArrayList<ClassData> data;
    private DataAdapter adapter;
    private TopSellingClassAdapter classAdapter;
    private ImageAdapter imageAdapter;
    private GoogleMap googleMap;
    private ImageView groupImageView, onlineImageView;
    private String temp1 = "https://www.braingroom.com/img/community.jpg";
    private String temp2 = "https://www.braingroom.com/img/online_classes.jpg";
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);

        try {
            initilizeMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initViews();
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2d4587")));
        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color
                .transparent)));
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_class_detail, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((ClassDetailActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    private void initViews() {
        //Group classes and online classes images display
        groupImageView = (ImageView) findViewById(R.id.itemImage);
        onlineImageView = (ImageView) findViewById(R.id.itemImage1);

        Picasso
                .with(context)
                .load(temp1)
                .fit()
                .into((ImageView) groupImageView);
        Picasso
                .with(context)
                .load(temp2)
                .fit()
                .into((ImageView) onlineImageView);

        //GridView
        gridView = (GridView) findViewById(R.id.catagory);

        //For Recomenned classes
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));
        //For Topselling classes
        topSellingRecyclerView = (RecyclerView) findViewById(R.id.top_selling_recycler_view);
        topSellingRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));

        loadJSON();
    }

    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private void loadJSON() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dev.braingroom.com/apis/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);

        Call<RecommendedClassResponse> call = request.getRecommendedClassJSON();
        Call<TopSellingResponse> jsonResponseCall = request.getJSONData();
        Call<CategoryResponse> categoryResponseCall = request.getCategoryData();

        categoryResponseCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse>
                    response) {
                ArrayList<CategoryData> categoryDataArray = response.body().getBraingroom();
                imageAdapter = new ImageAdapter(ClassDetailActivity.this, categoryDataArray);
                gridView.setAdapter(imageAdapter);
                Log.w("Category response ", new Gson().toJson(response));
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

        call.enqueue(new Callback<RecommendedClassResponse>() {
            @Override
            public void onResponse(Call<RecommendedClassResponse> call,
                                   Response<RecommendedClassResponse> response) {
                ArrayList<ClassData> array = response.body().getBraingroom();
                adapter = new DataAdapter(ClassDetailActivity.this, array);
                recyclerView.setAdapter(adapter);
                Log.w("response ", new Gson().toJson(response));
            }

            @Override
            public void onFailure(Call<RecommendedClassResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

        jsonResponseCall.enqueue(new Callback<TopSellingResponse>() {

            @Override
            public void onResponse(Call<TopSellingResponse> jsonResponseCall,
                                   Response<TopSellingResponse> response) {
                ArrayList<TopSellingClassData> classArray = response.body().getBraingroom();
                classAdapter = new TopSellingClassAdapter(ClassDetailActivity.this, classArray);
                topSellingRecyclerView.setAdapter(classAdapter);
                Log.w("TopSellingresponse ", new Gson().toJson(response));
            }

            @Override
            public void onFailure(Call<TopSellingResponse> jsonResponseCall, Throwable t) {
                Log.d("TopSellingError", t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.notification) {

        }
        if (id == R.id.message) {

        }
        return super.onOptionsItemSelected(item);
    }

}
