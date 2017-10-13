package com.rh.stravamate.model.datalayer.tasks;

import com.rh.stravamate.model.config.Settings;
import com.rh.stravamate.model.datalayer.db.StravaDb;
import com.rh.stravamate.model.datalayer.network.RetroStrava;
import com.rh.stravamate.model.datalayer.primitives.Activity;
import com.rh.stravamate.model.datalayer.primitives.Stats;
import com.rh.stravamate.model.util.Logging;

/**
 * Created by robert.hanaway on 13/10/2017.
 */

public class StatsTask extends BaseTask {
    public interface Callback {
        void onSuccess(Stats stats);
    }
    final Callback callback;
    final String type;
    public StatsTask(Logging logging, StravaDb stravaDb, RetroStrava retroStrava,
                     Settings settings, String type, Callback callback) {
        super(logging, stravaDb, retroStrava, settings);
        this.callback = callback;
        this.type = type;
    }

    @Override
    public String getTag() {
        return StatsTask.class.getSimpleName();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        Stats stats = new Stats();
        stats.setAverageHeartRate(stravaDb.getDb().getAverageHeartRate(type));
        stats.setAverageSpeed(stravaDb.getDb().getAverageSpeed(type));
        stats.setAverageDistance(stravaDb.getDb().getAverageDistance(type));
        stats.setMaxHeart(stravaDb.getDb().getMaxAverageHeartRate(type));
        stats.setMaxSpeed(stravaDb.getDb().getMaxAverageSpeed(type));
        stats.setMinHeart(stravaDb.getDb().getMinAverageHeartRate(type));
        stats.setMinSpeed(stravaDb.getDb().getMinAverageSpeed(type));
        stats.setLongestDistance(stravaDb.getDb().getLongestDistance(type));
        return stats;
    }

    protected void onPostExecute(Object result) {
        callback.onSuccess((Stats) result);
    }
}
