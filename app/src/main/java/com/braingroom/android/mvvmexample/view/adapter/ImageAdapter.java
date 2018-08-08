package com.braingroom.android.mvvmexample.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.braingroom.android.mvvmexample.R;
import com.braingroom.android.mvvmexample.utils.CategoryData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by android on 03/08/18.
 */

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CategoryData> categoryData;

    public ImageAdapter(Context context, ArrayList<CategoryData> categoryData) {
        this.context = context;
        this.categoryData = categoryData;
    }

    @Override
    public int getCount() {
        return categoryData.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = new View(context);
            grid = inflater.inflate(R.layout.grid_single, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
            textView.setText(categoryData.get(position).getCategory_name());
            Picasso
                    .with(context)
                    .load(categoryData.get(position).getCategory_image())
                    .fit()
                    .into((ImageView) imageView);

        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
