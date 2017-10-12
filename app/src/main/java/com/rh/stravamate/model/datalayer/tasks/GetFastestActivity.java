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

public class GetFastestActivity extends GetActivities {
    public GetFastestActivity(Logging logging, StravaDb stravaDb, RetroStrava retroStrava, Settings settings, Callback callback) {
        super(logging, stravaDb, retroStrava, settings, callback);
    }

    @Override
    public String getTag() {
        return GetFastestActivity.class.getSimpleName();
    }

    @Override
    protected List<Activity> getActivities() {
        return stravaDb.getDb().getFastestActivity();
    }
}
