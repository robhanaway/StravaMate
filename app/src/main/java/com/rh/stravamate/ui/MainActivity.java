package com.rh.stravamate.ui;

import android.os.Bundle;

import com.rh.stravamate.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logging.d(getTag(), "onCreate");
    }

    @Override
    String getTag() {
        return MainActivity.class.getSimpleName();
    }
}
