package com.rh.stravamate.model.util;

import com.rh.stravamate.AppModule;
import com.rh.stravamate.ui.BaseActivity;
import com.rh.stravamate.ui.fragments.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by robert.hanaway on 10/10/2017.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(BaseActivity baseActivity);
    void inject(BaseFragment baseActivity);
}
