package com.rh.stravamate.model.datalayer.tasks;

import com.rh.stravamate.model.config.Settings;
import com.rh.stravamate.model.datalayer.db.StravaDb;
import com.rh.stravamate.model.datalayer.network.ActivityService;
import com.rh.stravamate.model.datalayer.network.RetroStrava;
import com.rh.stravamate.model.datalayer.primitives.Activity;
import com.rh.stravamate.model.datalayer.primitives.RetrofitActivity;
import com.rh.stravamate.model.util.Logging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by robert.hanaway on 11/10/2017.
 */

public class RefreshActivities extends GetActivities {
    int inserted;
    public RefreshActivities(Logging logging, StravaDb stravaDb, RetroStrava retroStrava, Settings settings, Callback callback) {
        super(logging, stravaDb, retroStrava, settings, callback);
    }

    @Override
    protected List<Activity> getActivities() {
        stravaDb.getDb().deleteAllActivites();
        populateDbFromServer();
        return getActivitiesFromDb();
    }

    @Override
    public String getTag() {
        return RefreshActivities.class.getSimpleName();
    }

    void populateDbFromServer() {
        ActivityService activityService = retroStrava.getActivityService();
        loadByPage(activityService, 1);
    }

    void loadByPage(final ActivityService activityService, final int page) {
        int thisPage = page;
        boolean stop = false;
        while (!stop) {

            try {
                Call<List<RetrofitActivity>> response = activityService.get("Bearer " + settings.getToken(), thisPage);
                Response<List<RetrofitActivity>> result = response.execute();
                if (result.code() == 200 && !result.body().isEmpty()) {
                    stop = false;
                    List<RetrofitActivity> activities = result.body();
                    ArrayList<Activity> dbActivities = new ArrayList<>();
                    for (RetrofitActivity retrofitActivity : activities) {
                        dbActivities.add(new Activity(retrofitActivity));
                    }
                    stravaDb.getDb().insertActivities(dbActivities);
                    inserted += activities.size();
                    callback.onProgress(inserted);
                    logging.d(getTag(), "Inserted page %d", thisPage);
                    thisPage++;
                } else {
                    stop = true;
                }
            } catch (IOException e) {
                stop = true;
                error(e);
            }
        }
    }
}
