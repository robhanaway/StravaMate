package com.rh.stravamate.model.util.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rh.stravamate.model.primitives.Athlete;

/**
 * Created by robert.hanaway on 10/10/2017.
 */

public class AuthResponse {
    @SerializedName("access_token")
    @Expose
    private String token;

    @SerializedName("athlete")
    @Expose
    private Athlete athlete;

    public String getToken() {
        return token;
    }

    public Athlete getAthlete() {
        return athlete;
    }
}
