package com.rh.stravamate.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rh.stravamate.R;

/**
 * Created by robert.hanaway on 10/10/2017.
 */

public class ActivityViewHolder extends RecyclerView.ViewHolder {
    public TextView date;
    public ActivityViewHolder(View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.date);
    }
}
