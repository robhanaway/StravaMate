package com.rh.stravamate.model.datalayer.tasks;

import com.rh.stravamate.model.config.Settings;
import com.rh.stravamate.model.datalayer.db.StravaDb;
import com.rh.stravamate.model.datalayer.network.AuthResponse;
import com.rh.stravamate.model.datalayer.network.AuthService;
import com.rh.stravamate.model.datalayer.network.RetroStrava;
import com.rh.stravamate.model.datalayer.primitives.Activity;
import com.rh.stravamate.model.datalayer.primitives.ActivityTypeDistinct;
import com.rh.stravamate.model.datalayer.primitives.Athlete;
import com.rh.stravamate.model.datalayer.primitives.exceptions.NetworkException;
import com.rh.stravamate.model.util.Constants;
import com.rh.stravamate.model.util.Logging;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by robert.hanaway on 13/10/2017.
 */

public class FullLoader extends BaseTask {

    public enum Stage {
        athlete,
        activities
    }

    public interface Callback {
        void complete(Athlete athlete, List<Activity> activityList, List<ActivityTypeDistinct> types);
        void progress(Stage stage, int count);
        void error(Exception e);

    }
    final Callback callback;
    public FullLoader(Logging logging, StravaDb stravaDb, RetroStrava retroStrava, Settings settings, Callback callback) {
        super(logging, stravaDb, retroStrava, settings);
        this.callback = callback;
    }

    @Override
    public String getTag() {
        return FullLoader.class.getSimpleName();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        Athlete athlete = loadMe();
        if (athlete != null) {
            callback.progress(Stage.athlete, 0);
        } else {
            callback.error(new Exception());
        }
        return null;
    }

    List<Activity> getActivities() {
        List<Activity> results = stravaDb.getDb().getActivities();

        if (results != null && !results.isEmpty()) {
            callback.progress(Stage.activities, results.size());
        } else {

        }
        return results;
    }

    List<Activity> getActivitiesFromBackend() {
        List<Activity> results = null;
        return results;
    }

    Athlete loadMe() {
        Athlete athlete = stravaDb.getMe();
        List<Activity> activities;
        if (athlete == null) {
            logging.d(getTag(), "Athlete is null, retrieving from backend");
            athlete = getAthleteFromBackend();

        } else {
            logging.d(getTag(), "Athlete retrieved from DB");
        }
        return athlete;
    }

    Athlete getAthleteFromBackend() {
        Athlete athlete = null;
        AuthService auth = retroStrava.getAuthService();
        Call<AuthResponse> authResponse =  auth.auth(Constants.ID, Constants.S, settings.getCode());
        try {
            Response<AuthResponse> response = authResponse.execute();
            if (response.code() == 200) {
                AuthResponse result = response.body();
                settings.setToken(result.getToken());
                athlete = result.getAthlete();
            }

        } catch (IOException e) {
            logging.e(getTag(), "getAthleteFromBackend", new NetworkException(e));
            callback.error(e);
        }
        return athlete;
    }
}
