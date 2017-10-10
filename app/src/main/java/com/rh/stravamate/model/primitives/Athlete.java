package com.rh.stravamate.model.primitives;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by robert.hanaway on 10/10/2017.
 */

public class Athlete {
    @SerializedName("sex")
    @Expose
    private String sex;

    @SerializedName("resource_state")
    @Expose
    private String resourceState;

    @SerializedName("premium")
    @Expose
    private boolean premium;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("lastname")
    @Expose
    private String lastName;

    @SerializedName("firstname")
    @Expose
    private String firstName;

    @SerializedName("profile_medium")
    @Expose
    private String profileMedium;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("access_token")
    @Expose
    private String country;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("updated_at")
    @Expose
    private Date updatedAt;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("created_at")
    @Expose
    private Date createdAt;

    @SerializedName("profile")
    @Expose
    private String profile;
}
