package com.rh.stravamate.model.config;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by robert.hanaway on 10/10/2017.
 */
@Singleton
public class Settings {
    final static String PREFS = "preferences";
    final static String CODE_KEY = "code";
    final static String CODE_TOKEN = "token";
    final Context context;

    private String code;
    private String token;
    @Inject
    public Settings(Context context) {
        this.context = context;
        load();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        save();
    }

    private void load() {
        SharedPreferences preferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        code = preferences.getString(CODE_KEY, null);
        token = preferences.getString(CODE_TOKEN, null);
    }

    void save() {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit();
        editor.putString(CODE_KEY, code);
        editor.putString(CODE_TOKEN, token);
        editor.apply();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        save();
    }
}
