package com.rh.stravamate.model.datalayer.primitives;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rh.stravamate.model.util.Constants;

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



    @SerializedName("start_date_local")
    @ColumnInfo(name = "start_date_local")
    @Expose
    private Date startDateLocal;

    @SerializedName("description")
    @ColumnInfo(name = "description")
    @Expose
    private String description;

    @SerializedName("distance")
    @ColumnInfo(name = "distance")
    @Expose
    private float distance;

    @SerializedName("moving_time")
    @ColumnInfo(name = "moving_time")
    @Expose
    private int movingTime;

    @SerializedName("elapsed_time")
    @ColumnInfo(name = "elapsed_time")
    @Expose
    private int elapsedTime;

    @SerializedName("total_elevation_gain")
    @ColumnInfo(name = "total_elevation_gain")
    @Expose
    private float totalElevationGain;

    @SerializedName("elev_high")
    @ColumnInfo(name = "elev_high")
    @Expose
    private float elevationHigh;

    @SerializedName("elev_low")
    @ColumnInfo(name = "elev_low")
    @Expose
    private float elevationLow;

    @SerializedName("type")
    @ColumnInfo(name = "type")
    @Expose
    private String type;

    @SerializedName("average_speed")
    @ColumnInfo(name = "average_speed")
    @Expose
    private float averageSpeed;

    @SerializedName("max_speed")
    @ColumnInfo(name = "max_speed")
    @Expose
    private float maxSpeed;

    @SerializedName("timezone")
    @ColumnInfo(name = "timezone")
    @Expose
    private String timeZone;

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

    public int getMovingTime() {
        return movingTime;
    }

    public void setMovingTime(int movingTime) {
        this.movingTime = movingTime;
    }

    public Date getStartDateLocal() {
        return startDateLocal;
    }

    public void setStartDateLocal(Date startDateLocal) {
        this.startDateLocal = startDateLocal;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public float getTotalElevationGain() {
        return totalElevationGain;
    }

    public void setTotalElevationGain(float totalElevationGain) {
        this.totalElevationGain = totalElevationGain;
    }

    public float getElevationHigh() {
        return elevationHigh;
    }

    public void setElevationHigh(float elevationHigh) {
        this.elevationHigh = elevationHigh;
    }

    public float getElevationLow() {
        return elevationLow;
    }

    public void setElevationLow(float elevationLow) {
        this.elevationLow = elevationLow;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(float averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getAverageSpeedPerKm() {
        return getAverageSpeedPerUnit(Constants.METRES_IN_KM);
    }

    public int getAverageSpeedPerMile() {
        return getAverageSpeedPerUnit(Constants.METRES_IN_MILE);
    }

    public int getAverageSpeedPerUnit(float unit) {
        int result = 0;
        if (averageSpeed != 0) {
            result = (int)(unit/averageSpeed);
        }
        return result;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
