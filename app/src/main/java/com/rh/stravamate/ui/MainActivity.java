package com.rh.stravamate.ui;



import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;


import com.rh.stravamate.R;


import com.rh.stravamate.model.datalayer.primitives.Activity;
import com.rh.stravamate.model.datalayer.primitives.Athlete;
import com.rh.stravamate.model.datalayer.network.AuthResponse;
import com.rh.stravamate.model.datalayer.network.AuthService;
import com.rh.stravamate.model.util.Constants;
import com.rh.stravamate.ui.fragments.ActivityFragment;
import com.rh.stravamate.ui.fragments.AuthFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements
        AuthFragment.OnFragmentInteractionListener,
        ActivityFragment.OnListFragmentInteractionListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logging.d(getTag(), "onCreate");

        loadMe();
    }

    @Override
    String getTag() {
        return MainActivity.class.getSimpleName();
    }

    @Override
    public void onAuthSuccess(String code) {
        logging.d(getTag(), "Code %s", code);
        AuthService auth = retroStrava.getAuthService();
        Call<AuthResponse> response =  auth.auth(Constants.ID, Constants.S, code);
        response.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                AuthResponse authResponse = response.body();
                logging.d(getTag(), "Token received %s", authResponse.getToken());
                settings.setToken(response.body().getToken());
                stravaDb.storeMe(authResponse.getAthlete());
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                logging.d(getTag(), "auth failed");
            }
        });
    }

    void loadMe() {
        AsyncTask<Void, Void, Athlete> task = new AsyncTask<Void, Void, Athlete>() {
            @Override
            protected Athlete doInBackground(Void... voids) {
                final Athlete me = stravaDb.getMe();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (me == null) {
                            loadAuthFragment();
                        } else {
                            loadListFragment();
                        }
                    }
                });
                return me;
            }
        };
        task.execute();
    }

    void loadAuthFragment() {
        Fragment fragment = AuthFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame, fragment).commit();
    }




    void loadListFragment() {
        Fragment fragment = ActivityFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame, fragment).commit();
    }

    @Override
    public void onListFragmentInteraction(Activity item) {

    }
}
