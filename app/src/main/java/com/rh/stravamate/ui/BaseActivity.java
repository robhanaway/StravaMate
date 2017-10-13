package com.rh.stravamate.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.rh.stravamate.StravaMateApplication;
import com.rh.stravamate.model.util.Logging;
import com.rh.stravamate.model.datalayer.db.StravaDb;
import com.rh.stravamate.model.config.Settings;
import com.rh.stravamate.model.datalayer.network.RetroStrava;

import javax.inject.Inject;


/**
 * Created by robert.hanaway on 10/10/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Inject
    Logging logging;

    @Inject
    Settings settings;

    @Inject
    StravaDb stravaDb;

    @Inject
    RetroStrava retroStrava;

    Snackbar snackbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((StravaMateApplication)getApplication()).getAppComponent().inject(this);
    }

    abstract String getTag();

    void showSnack(String text) {
        if (snackbar != null) {
            snackbar.setText(text);
        } else {
            snackbar = Snackbar.make(null, text, Snackbar.LENGTH_INDEFINITE);
        }
        snackbar.show();
    }

    void showSnack(int text) {
        showSnack(getString(text));
    }

    void hideSnack() {
        if (snackbar != null) {
            snackbar.dismiss();
        }
        snackbar = null;
    }
}
