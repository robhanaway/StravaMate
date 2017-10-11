package com.rh.stravamate.model.datalayer.primitives;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by robert.hanaway on 10/10/2017.
 */
@Entity(tableName = "activity")
public class Activity {
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    @Expose
    private String id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    @Expose
    private String name;

    @SerializedName("moving_time")
    @ColumnInfo(name = "moving_time")
    @Expose
    private String movingTime;

    @SerializedName("start_date_local")
    @ColumnInfo(name = "start_date_local")
    @Expose
    private Date startDateLocal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovingTime() {
        return movingTime;
    }

    public void setMovingTime(String movingTime) {
        this.movingTime = movingTime;
    }

    public Date getStartDateLocal() {
        return startDateLocal;
    }

    public void setStartDateLocal(Date startDateLocal) {
        this.startDateLocal = startDateLocal;
    }
}
