package com.rh.stravamate;

import android.app.Application;

import com.rh.stravamate.model.util.AppComponent;
import com.rh.stravamate.model.util.DaggerAppComponent;

/**
 * Created by robert.hanaway on 10/10/2017.
 */

public class StravaMateApplication extends Application {
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }

    protected AppComponent initDagger(Application application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
