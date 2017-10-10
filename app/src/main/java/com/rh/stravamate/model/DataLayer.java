package com.rh.stravamate.model;

import android.os.AsyncTask;

import com.rh.stravamate.model.primitives.Activity;
import com.rh.stravamate.model.util.Logging;
import com.rh.stravamate.model.util.StravaDb;
import com.rh.stravamate.model.util.config.Settings;
import com.rh.stravamate.model.util.network.ActivityService;
import com.rh.stravamate.model.util.network.RetroStrava;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
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

    public void refreshActivities(final ActivitiesCallback callback) {
        new AsyncTask<Void, Void, List<Activity>>() {
            @Override
            protected List<Activity> doInBackground(Void... voids) {
                stravaDb.getDb().deleteAllActivites();
                ActivityService activityService = retroStrava.getActivityService();
                loadByPage(activityService, 1);
                return stravaDb.getDb().getActivities();
            }
            protected void onPostExecute(List<Activity> result) {
                callback.onSuccess(result);
            }
        }.execute();
    }



    void loadByPage(final ActivityService activityService, final int page) {
        int thisPage = page;
        boolean stop = false;
        while (!stop) {

            try {
                Call<List<Activity>> response = activityService.get("Bearer " + settings.getToken(), thisPage);
                Response<List<Activity>> result = response.execute();
                if (result.code() == 200 && !result.body().isEmpty()) {
                    stop = false;
                    stravaDb.getDb().insertActivities(result.body());
                    thisPage++;
                } else {
                    stop = true;
                }
            } catch (IOException e) {
                stop = true;
            }
        }

    }
}
