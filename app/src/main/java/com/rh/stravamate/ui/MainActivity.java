package com.rh.stravamate.ui;



import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.text.TextUtils;


import com.rh.stravamate.R;

import com.rh.stravamate.model.util.network.AuthResponse;
import com.rh.stravamate.model.util.network.AuthService;
import com.rh.stravamate.model.util.network.RetroStrava;
import com.rh.stravamate.model.util.Constants;
import com.rh.stravamate.ui.fragments.AuthFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements AuthFragment.OnFragmentInteractionListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logging.d(getTag(), "onCreate");

        if (TextUtils.isEmpty(settings.getCode())) {
            Fragment fragment = AuthFragment.newInstance();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frame, fragment).commit();
        }
    }

    @Override
    String getTag() {
        return MainActivity.class.getSimpleName();
    }

    @Override
    public void onAuthSuccess(String code) {
        logging.d(getTag(), "Code %s", code);
//        settings.setCode(code);
        RetroStrava retroStrava = new RetroStrava();
        AuthService auth = retroStrava.getAuthService();
        Call<AuthResponse> response =  auth.auth(Constants.ID, Constants.S, code);
        response.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                logging.d(getTag(), "Token received %s", response.body().getToken());
                settings.setToken(response.body().getToken());
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                logging.d(getTag(), "auth failed");
            }
        });
    }
}
