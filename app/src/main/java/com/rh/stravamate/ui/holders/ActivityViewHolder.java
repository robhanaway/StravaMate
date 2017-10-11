package com.rh.stravamate.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rh.stravamate.R;

/**
 * Created by robert.hanaway on 10/10/2017.
 */

public class ActivityViewHolder extends RecyclerView.ViewHolder {
    public TextView date;
    public TextView name;
    public ImageView type;
    public TextView avgSpeed;
    public ActivityViewHolder(View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.date);
        name = itemView.findViewById(R.id.name);
        type = itemView.findViewById(R.id.type);
        avgSpeed = itemView.findViewById(R.id.avg);
    }
}
