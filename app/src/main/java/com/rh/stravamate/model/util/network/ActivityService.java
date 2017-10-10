package com.rh.stravamate.model.util.network;


import com.rh.stravamate.model.primitives.Activity;
import com.rh.stravamate.model.util.StravaDb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by robert.hanaway on 10/10/2017.
 */

public interface ActivityService {
    @GET("/api/v3/athlete/activities")
    Call<List<Activity>> get(@Header("Authorization") String auth, @Query("page") int page);
}
