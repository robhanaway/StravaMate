package com.rh.stravamate.model.datalayer.primitives;

import android.arch.persistence.room.ColumnInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by robert.hanaway on 12/10/2017.
 */

public class ActivityTypeDistinct {
    @SerializedName("type")
    @ColumnInfo(name = "type")
    @Expose
    private String type;

    @SerializedName("COUNT(type)")
    @ColumnInfo(name = "COUNT(type)")
    @Expose
    private int count;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
