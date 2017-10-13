package com.rh.stravamate.ui.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;

import com.rh.stravamate.StravaMateApplication;
import com.rh.stravamate.model.config.Settings;
import com.rh.stravamate.model.datalayer.DataLayer;
import com.rh.stravamate.model.util.Logging;
import com.rh.stravamate.model.datalayer.db.StravaDb;
import com.rh.stravamate.ui.MainActivity;

import javax.inject.Inject;

/**
 * Created by robert.hanaway on 10/10/2017.
 */

public abstract class BaseFragment extends Fragment {
    @Inject
    Logging logging;

    @Inject
    StravaDb stravaDb;

    @Inject
    DataLayer dataLayer;

    @Inject
    Settings settings;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((StravaMateApplication)getActivity().getApplication()).getAppComponent().inject(this);
    }

    abstract String getLogTag();

    protected MainActivity getMainActivity() {
        return MainActivity.class.cast(getActivity());
    }

    protected ActionBar getSupportedActionBar() {
        return getMainActivity().getSupportActionBar();
    }


}
