package com.rh.stravamate.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rh.stravamate.R;
import com.rh.stravamate.model.datalayer.primitives.Activity;
import com.rh.stravamate.ui.holders.ActivityViewHolder;

import java.util.List;

/**
 * Created by robert.hanaway on 10/10/2017.
 */

public class ActivityAdapter extends RecyclerView.Adapter<ActivityViewHolder> {
    final List<Activity> activityList;
    LayoutInflater layoutInflater;
    public ActivityAdapter(List<Activity> activityList, Context context) {
        this.activityList = activityList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_item, null);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActivityViewHolder holder, int position) {
        Activity activity = activityList.get(position);
        holder.date.setText(activity.getStartDateLocal().toString());
        holder.name.setText(activity.getName());

    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }
}
