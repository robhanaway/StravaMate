package com.rh.stravamate.model.datalayer.primitives;

import com.google.gson.annotations.SerializedName;

/**
 * Created by robert.hanaway on 18/10/2017.
 */

public class RetrofitActivity extends Activity {
    @SerializedName("athlete")
    Athlete athlete;

    @SerializedName("gear")
    Gear gear;
}
