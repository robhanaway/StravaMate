package com.rh.stravamate.model.datalayer.primitives;

import com.google.gson.annotations.SerializedName;

/**
 * Created by robert.hanaway on 18/10/2017.
 */

public class RetrofitActivity extends Activity {
    @SerializedName("athlete")
    private
    Athlete athlete;

    @SerializedName("gear")
    private
    Gear gear;

    @SerializedName("map")
    private
    Map map;

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public Gear getGear() {
        return gear;
    }

    public void setGear(Gear gear) {
        this.gear = gear;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
