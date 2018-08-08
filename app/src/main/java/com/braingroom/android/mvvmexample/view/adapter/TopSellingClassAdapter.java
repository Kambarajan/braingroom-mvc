package com.braingroom.android.mvvmexample.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.braingroom.android.mvvmexample.R;
import com.braingroom.android.mvvmexample.utils.TopSellingClassData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by android on 31/07/18.
 */

public class TopSellingClassAdapter extends RecyclerView.Adapter<TopSellingClassAdapter
        .ViewHolder> {
    private ArrayList<TopSellingClassData> topSellingClassData;
    private Context context;

    public TopSellingClassAdapter(Context context, ArrayList<TopSellingClassData>
            topSellingClassData) {
        this.topSellingClassData = topSellingClassData;
        this.context = context;
    }

    @Override
    public TopSellingClassAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                .top_selling_classes_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopSellingClassAdapter.ViewHolder viewHolder, int position) {

        Picasso.with(context).load(topSellingClassData.get(position).getPic_name()).resize(120,
                60)
                .into(viewHolder.pic_name);
        viewHolder.classTopic.setText(topSellingClassData.get(position).getClass_topic());
        viewHolder.price.setText("₹" + "" + topSellingClassData.get(position).getPrice());
        viewHolder.classTypeData.setText(topSellingClassData.get(position).getClass_type_data());
        viewHolder.rating.setText(topSellingClassData.get(position).getRating());
        viewHolder.classDuration.setText(topSellingClassData.get(position).getClass_duration());
        viewHolder.noOfSessions.setText(topSellingClassData.get(position).getNo_of_sessions());

    }

    @Override
    public int getItemCount() {
        return topSellingClassData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView pic_name;
        private TextView price, classTopic, classDuration, noOfSessions,
                rating, classTypeData;

        public ViewHolder(View view) {
            super(view);
            pic_name = (ImageView) view.findViewById(R.id.pic_name);
            classTopic = (TextView) view.findViewById(R.id.classTopic);
            price = (TextView) view.findViewById(R.id.price);
            classTypeData = (TextView) view.findViewById(R.id.class_type_data);
            rating = (TextView) view.findViewById(R.id.rating);
            classDuration = (TextView) view.findViewById(R.id.classDuration);
            noOfSessions = (TextView) view.findViewById(R.id.no_of_sessions);

        }
    }
}
