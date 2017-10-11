package com.rh.stravamate.model.datalayer.tasks;

import com.rh.stravamate.model.config.Settings;
import com.rh.stravamate.model.datalayer.db.StravaDb;
import com.rh.stravamate.model.datalayer.network.RetroStrava;
import com.rh.stravamate.model.datalayer.primitives.Activity;
import com.rh.stravamate.model.util.Logging;

import java.util.List;

/**
 * Created by robert.hanaway on 11/10/2017.
 */

public abstract class GetActivities extends BaseTask<Void, Void, List<Activity>> {


    public interface Callback {
        void onSuccess(List<Activity> activities);
        void onError(Exception e);
    }

    final Callback callback;

    public GetActivities(Logging logging, StravaDb stravaDb, RetroStrava retroStrava, Settings settings, Callback callback) {
        super(logging, stravaDb, retroStrava, settings);
        this.callback = callback;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return getActivities();
    }

    protected void onPostExecute(Object result) {
        callback.onSuccess((List<Activity>) result);
    }

    protected abstract List<Activity> getActivities();

    protected List<Activity> getActivitiesFromDb() {
        return stravaDb.getDb().getActivities();
    }

    protected void error(Exception e) {
        callback.onError(e);
    }
}
