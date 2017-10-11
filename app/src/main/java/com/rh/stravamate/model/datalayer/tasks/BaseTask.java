package com.rh.stravamate.model.datalayer.tasks;

import android.os.AsyncTask;

import com.rh.stravamate.model.config.Settings;
import com.rh.stravamate.model.datalayer.db.StravaDb;
import com.rh.stravamate.model.datalayer.network.RetroStrava;
import com.rh.stravamate.model.util.Logging;

/**
 * Created by robert.hanaway on 11/10/2017.
 */

public abstract class BaseTask<Params, Progress, Result>  extends AsyncTask {
    final Logging logging;
    final StravaDb stravaDb;
    final RetroStrava retroStrava;
    final Settings settings;

    public BaseTask(Logging logging, StravaDb stravaDb, RetroStrava retroStrava, Settings settings) {
        this.logging = logging;
        this.stravaDb = stravaDb;
        this.retroStrava = retroStrava;
        this.settings = settings;
    }

    public abstract String getTag();

}
