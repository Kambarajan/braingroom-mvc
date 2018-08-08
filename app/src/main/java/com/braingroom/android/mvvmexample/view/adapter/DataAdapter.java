package com.braingroom.android.mvvmexample.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.braingroom.android.mvvmexample.R;
import com.braingroom.android.mvvmexample.utils.ClassData;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

/**
 * Created by android on 26/07/18.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<ClassData> android;
    private Context context;


    public DataAdapter(Context context, ArrayList<ClassData> android) {
        this.android = android;
        this.context = context;
    }


    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                        .recommended_classes_card, viewGroup,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_version.setText("₹" + "" + android.get(i).getPrice());
        viewHolder.tv_api_level.setText(android.get(i).getClass_topic());
        viewHolder.class_duration.setText(android.get(i).getClass_duration());
        viewHolder.no_of_sessions.setText(android.get(i).getNo_of_sessions() + "" + "Sessions");
        viewHolder.rating.setText(android.get(i).getRating());
        viewHolder.class_type_data.setText(android.get(i).getClass_type_data());
        Picasso.with(context).load(android.get(i).getPic_name()).resize(120, 60).into
                (viewHolder.pic_name);
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_version, tv_api_level, class_duration, no_of_sessions,
                rating, class_type_data;
        private ImageView pic_name;

        public ViewHolder(View view) {
            super(view);

            tv_version = (TextView) view.findViewById(R.id.tv_version);
            tv_api_level = (TextView) view.findViewById(R.id.tv_api_level);
            class_duration = (TextView) view.findViewById(R.id.classDuration);
            no_of_sessions = (TextView) view.findViewById(R.id.no_of_sessions);
            rating = (TextView) view.findViewById(R.id.rating);
            pic_name = (ImageView) view.findViewById(R.id.pic_name);
            class_type_data = (TextView) view.findViewById(R.id.class_type_data);
        }
    }
}
