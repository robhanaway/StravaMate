package com.rh.stravamate.model.datalayer.primitives;

import com.google.gson.annotations.SerializedName;

/**
 * Created by robert.hanaway on 18/10/2017.
 */

public class Map {
    @SerializedName("id")
    private String id;
    @SerializedName("summary_polyline")
    private String summaryPolyLine;
    @SerializedName("resource_state")
    private int resourceState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummaryPolyLine() {
        return summaryPolyLine;
    }

    public void setSummaryPolyLine(String summaryPolyLine) {
        this.summaryPolyLine = summaryPolyLine;
    }

    public int getResourceState() {
        return resourceState;
    }

    public void setResourceState(int resourceState) {
        this.resourceState = resourceState;
    }
}
