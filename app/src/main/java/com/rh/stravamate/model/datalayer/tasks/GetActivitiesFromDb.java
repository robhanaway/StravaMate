package com.rh.stravamate.model.datalayer.tasks;

import android.text.TextUtils;

import com.rh.stravamate.model.config.Settings;
import com.rh.stravamate.model.datalayer.db.StravaDb;
import com.rh.stravamate.model.datalayer.network.RetroStrava;
import com.rh.stravamate.model.datalayer.primitives.Activity;
import com.rh.stravamate.model.datalayer.primitives.ActivityTypeDistinct;
import com.rh.stravamate.model.util.Logging;

import java.util.List;



/**
 * Created by robert.hanaway on 11/10/2017.
 */

public class GetActivitiesFromDb extends GetActivities {
    final String type;
    public GetActivitiesFromDb(Logging logging, StravaDb stravaDb, RetroStrava retroStrava,
                               Settings settings, String type, Callback callback) {
        super(logging, stravaDb, retroStrava, settings, callback);
        this.type = type;
    }

    @Override
    protected List<Activity> getActivities() {
        return TextUtils.isEmpty(type) ? getActivitiesFromDb() : stravaDb.getDb().getActivitiesByType(type);
    }

    @Override
    public String getTag() {
        return GetActivitiesFromDb.class.getSimpleName();
    }
}
