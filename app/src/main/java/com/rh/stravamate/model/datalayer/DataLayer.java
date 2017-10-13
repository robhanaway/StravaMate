package com.rh.stravamate.model.datalayer;

import android.os.AsyncTask;

import com.rh.stravamate.model.datalayer.primitives.Activity;
import com.rh.stravamate.model.datalayer.primitives.ActivityTypeDistinct;
import com.rh.stravamate.model.datalayer.tasks.GetActivities;
import com.rh.stravamate.model.datalayer.tasks.GetActivitiesFromDb;
import com.rh.stravamate.model.datalayer.tasks.GetActivityTypes;
import com.rh.stravamate.model.datalayer.tasks.RefreshActivities;
import com.rh.stravamate.model.datalayer.tasks.StatsTask;
import com.rh.stravamate.model.util.Logging;
import com.rh.stravamate.model.datalayer.db.StravaDb;
import com.rh.stravamate.model.config.Settings;
import com.rh.stravamate.model.datalayer.network.ActivityService;
import com.rh.stravamate.model.datalayer.network.RetroStrava;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by robert.hanaway on 10/10/2017.
 */

@Singleton
public class DataLayer {
    public interface ActivitiesCallback {
        void onSuccess(List<Activity> activities);
    }

    @Inject
    Logging logging;

    @Inject
    Settings settings;

    @Inject
    StravaDb stravaDb;

    @Inject
    RetroStrava retroStrava;

    @Inject
    public DataLayer() {

    }

    public void loadActivities(String type, final GetActivities.Callback callback) {
        new GetActivitiesFromDb(logging, stravaDb, retroStrava, settings,type, new GetActivities.Callback() {
            @Override
            public void onSuccess(List<Activity> activities) {
                if (!activities.isEmpty()) {
                    callback.onSuccess(activities);
                } else {
                    new RefreshActivities(logging, stravaDb, retroStrava, settings, this).execute();
                }
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        }).execute();
    }

    public void getActivityTypes(final GetActivityTypes.Callback callback) {
        new GetActivityTypes(logging, stravaDb, retroStrava, settings, callback).execute();
    }


    public void getStats(String type, StatsTask.Callback callback) {
        new StatsTask(logging, stravaDb, retroStrava, settings, type, callback).execute();
    }
}
