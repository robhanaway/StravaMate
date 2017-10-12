package com.rh.stravamate.model.datalayer.tasks;

import com.rh.stravamate.model.config.Settings;
import com.rh.stravamate.model.datalayer.db.StravaDb;
import com.rh.stravamate.model.datalayer.network.RetroStrava;
import com.rh.stravamate.model.datalayer.primitives.ActivityTypeDistinct;
import com.rh.stravamate.model.util.Logging;

import java.util.List;

/**
 * Created by robert.hanaway on 12/10/2017.
 */

public class GetActivityTypes extends BaseTask {
    public interface Callback {
        void onSuccess(List<ActivityTypeDistinct> types);
        void onError(Exception e);
    }
    final Callback callback;

    public GetActivityTypes(Logging logging, StravaDb stravaDb, RetroStrava retroStrava, Settings settings, Callback callback) {
        super(logging, stravaDb, retroStrava, settings);
        this.callback = callback;
    }

    @Override
    public String getTag() {
        return GetActivityTypes.class.getSimpleName();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return stravaDb.getDb().getActivityTypes();
    }

    protected void onPostExecute(Object result) {
        callback.onSuccess((List<ActivityTypeDistinct>) result);
    }
}
