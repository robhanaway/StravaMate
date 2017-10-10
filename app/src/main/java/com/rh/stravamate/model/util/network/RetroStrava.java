package com.rh.stravamate.model.util.network;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by robert.hanaway on 10/10/2017.
 */
@Singleton
public class RetroStrava {
    static String BASE_URL = "https://www.strava.com/";

    @Inject
    public RetroStrava() {

    }
    private Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public AuthService getAuthService() {
        return getRetrofitInstance().create(AuthService.class);
    }

    public ActivityService getActivityService() {
        return getRetrofitInstance().create(ActivityService.class);
    }
}
