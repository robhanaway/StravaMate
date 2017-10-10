package com.rh.stravamate.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rh.stravamate.StravaMateApplication;
import com.rh.stravamate.model.util.AppComponent;
import com.rh.stravamate.model.util.Logging;

import javax.inject.Inject;


/**
 * Created by robert.hanaway on 10/10/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Inject
    Logging logging;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((StravaMateApplication)getApplication()).getAppComponent().inject(this);
    }

    abstract String getTag();
}
