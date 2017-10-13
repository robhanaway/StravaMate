package com.rh.stravamate.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.rh.stravamate.R;
import com.rh.stravamate.model.datalayer.primitives.Activity;
import com.rh.stravamate.model.datalayer.primitives.ActivityTypeDistinct;
import com.rh.stravamate.model.datalayer.primitives.Stats;
import com.rh.stravamate.model.datalayer.tasks.GetActivities;
import com.rh.stravamate.model.datalayer.tasks.GetActivityTypes;
import com.rh.stravamate.model.datalayer.tasks.StatsTask;
import com.rh.stravamate.ui.adapters.ActivityAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends BaseFragment {
    RecyclerView listView;
    ArrayAdapter adapter;
    public StatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance() {
        StatsFragment fragment = new StatsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    void loadStats(String type) {
        dataLayer.getStats("Run", new StatsTask.Callback() {
            @Override
            public void onSuccess(Stats stats) {
                createAdapter(stats);
            }
        });
    }


    void createAdapter(Stats stats) {
        List<Activity> activities = new ArrayList<>();
        stats.getLongestDistance().setTitle("Longest");
        activities.add(stats.getLongestDistance());
        stats.getMaxSpeed().setTitle("Fastest");
        activities.add(stats.getMaxSpeed());
        listView.setAdapter(new ActivityAdapter(activities, settings, getActivity()));
    }

    @Override
    String getLogTag() {
        return StatsFragment.class.getSimpleName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        listView = (RecyclerView) view.findViewById(R.id.activities);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadTypes();
        return view;
    }

    void loadTypes() {
        dataLayer.getActivityTypes(new GetActivityTypes.Callback() {
            @Override
            public void onSuccess(List<ActivityTypeDistinct> types) {
                loadTypeAdapter(types);
            }

            @Override
            public void onError(Exception e) {
            }
        });
    }

    void loadTypeAdapter(List<ActivityTypeDistinct> types) {
        adapter = new ArrayAdapter<String>(getMainActivity(), android.R.layout.simple_list_item_1);
        for (ActivityTypeDistinct activityTypeDistinct : types) {
            adapter.add(activityTypeDistinct.getType());
        }
        getMainActivity().getTypeSpinner().setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getMainActivity().getTypeSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadStats(adapter.getItem(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
