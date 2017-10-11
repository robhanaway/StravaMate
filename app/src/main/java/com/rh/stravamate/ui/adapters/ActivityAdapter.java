package com.rh.stravamate.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rh.stravamate.R;
import com.rh.stravamate.model.config.Settings;
import com.rh.stravamate.model.datalayer.primitives.Activity;
import com.rh.stravamate.model.util.Constants;
import com.rh.stravamate.model.util.Converter;
import com.rh.stravamate.ui.holders.ActivityViewHolder;

import java.util.List;

/**
 * Created by robert.hanaway on 10/10/2017.
 */

public class ActivityAdapter extends RecyclerView.Adapter<ActivityViewHolder> {
    final List<Activity> activityList;
    LayoutInflater layoutInflater;
    final Converter converter;
    public ActivityAdapter(List<Activity> activityList, Settings settings,  Context context) {
        this.activityList = activityList;
        layoutInflater = LayoutInflater.from(context);
        converter = new Converter(settings, context);
    }

    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_item, null);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActivityViewHolder holder, int position) {
        Activity activity = activityList.get(position);
        holder.date.setText(converter.fromDate(activity.getStartDateLocal(), activity.getTimeZone()));
        holder.name.setText(activity.getName());
        holder.speedAndDistance.setText(converter.getSpeed(activity));
        setType(holder, activity.getType());
    }



    void setType(ActivityViewHolder holder, String type) {
        if (Constants.TYPE_RUN.equalsIgnoreCase(type)) {
            holder.type.setImageResource(R.drawable.feed_sport_run);
        } else if (Constants.TYPE_RIDE.equalsIgnoreCase(type)) {
            holder.type.setImageResource(R.drawable.feed_sport_bike);
        } else {
            holder.type.setImageDrawable(null);
        }
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }
}
