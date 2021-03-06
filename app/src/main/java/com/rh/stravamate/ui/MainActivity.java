package com.rh.stravamate.ui;



import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;


import com.rh.stravamate.R;


import com.rh.stravamate.model.datalayer.primitives.Activity;
import com.rh.stravamate.model.datalayer.primitives.Athlete;
import com.rh.stravamate.model.datalayer.network.AuthResponse;
import com.rh.stravamate.model.datalayer.network.AuthService;
import com.rh.stravamate.model.util.Constants;
import com.rh.stravamate.ui.fragments.ActivityFragment;
import com.rh.stravamate.ui.fragments.AuthFragment;
import com.rh.stravamate.ui.fragments.StatsFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements
        AuthFragment.OnFragmentInteractionListener,
        ActivityFragment.OnListFragmentInteractionListener{

    public Spinner getTypeSpinner() {
        return spinner;
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createActionBar();
        logging.d(getTag(), "onCreate");
        loadMe();
    }

    void createActionBar() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.mipmap.ic_launcher,
                R.string.app_name,  /* "open drawer" description */
                R.string.app_name  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(R.string.app_name);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.app_name);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.actionbar_drawer);

        getSupportActionBar().setHomeButtonEnabled(true);

        ListView listView = findViewById(R.id.left_drawer);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.main_menu)));

        listView.setOnItemClickListener(new DrawerItemClickListener());
    }

    void selectItem(int position) {
        switch (position) {
            case 0:
                showActivities();
                break;
            case 1:
                showStats();
                break;
        }
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    void showActivities() {
        Fragment fragment = ActivityFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).commit();
    }

    void showStats() {
        Fragment fragment = StatsFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).commit();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toggleDrawer();
                break;

        }
        return true;
    }


    void toggleDrawer() {
        if (!mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        } else {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
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
                removeAuthAndLoadList();

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

    void removeAuthAndLoadList() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Fragment fragment = ActivityFragment.newInstance();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).commit();
            }
        });
    }

    void loadListFragment() {
        Fragment fragment = ActivityFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame, fragment).commit();
    }

    @Override
    public void onListFragmentInteraction(Activity item) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.spinner);
        spinner = (Spinner) MenuItemCompat.getActionView(item);
        return true;
    }
}
