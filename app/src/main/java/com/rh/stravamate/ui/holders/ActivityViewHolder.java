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
    public TextView title;
    public TextView date;
    public TextView name;
    public ImageView type;
    public TextView speedAndDistance;
    public View kudosCommentContainer;
    public View kudosView;
    public View commentView;
    public TextView kudosCount;
    public TextView commentCount;
    public View heartContainer;
    public TextView avgHeart;
    public TextView maxHeart;
    public ActivityViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.topic);
        date = itemView.findViewById(R.id.date);
        name = itemView.findViewById(R.id.name);
        type = itemView.findViewById(R.id.type);
        speedAndDistance = itemView.findViewById(R.id.speed_distance);
        kudosCommentContainer = itemView.findViewById(R.id.kudos_comment_container);
        kudosView = itemView.findViewById(R.id.kudos_container);
        commentView = itemView.findViewById(R.id.comment_container);
        kudosCount = itemView.findViewById(R.id.kudos_count);
        commentCount = itemView.findViewById(R.id.comment_count);
        heartContainer = itemView.findViewById(R.id.heart_container);
        avgHeart = itemView.findViewById(R.id.heart_rate);
        maxHeart = itemView.findViewById(R.id.max_rate);
    }
}
