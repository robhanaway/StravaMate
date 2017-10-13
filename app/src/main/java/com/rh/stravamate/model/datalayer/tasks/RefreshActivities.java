package com.rh.stravamate.model.datalayer.tasks;

import com.rh.stravamate.model.config.Settings;
import com.rh.stravamate.model.datalayer.db.StravaDb;
import com.rh.stravamate.model.datalayer.network.ActivityService;
import com.rh.stravamate.model.datalayer.network.RetroStrava;
import com.rh.stravamate.model.datalayer.primitives.Activity;
import com.rh.stravamate.model.util.Logging;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by robert.hanaway on 11/10/2017.
 */

public class RefreshActivities extends GetActivities {
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
                Call<List<Activity>> response = activityService.get("Bearer " + settings.getToken(), thisPage);
                Response<List<Activity>> result = response.execute();
                if (result.code() == 200 && !result.body().isEmpty()) {
                    stop = false;
                    stravaDb.getDb().insertActivities(result.body());
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
