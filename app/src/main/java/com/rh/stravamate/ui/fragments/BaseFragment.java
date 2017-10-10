package com.rh.stravamate.ui.fragments;


import android.app.Fragment;
import android.os.Bundle;

import com.rh.stravamate.StravaMateApplication;
import com.rh.stravamate.model.util.Logging;

import javax.inject.Inject;

/**
 * Created by robert.hanaway on 10/10/2017.
 */

public abstract class BaseFragment extends Fragment {
    @Inject
    Logging logging;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((StravaMateApplication)getActivity().getApplication()).getAppComponent().inject(this);
    }

    abstract String getLogTag();
}
