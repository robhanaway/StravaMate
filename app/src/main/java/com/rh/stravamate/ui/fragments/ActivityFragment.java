package com.rh.stravamate.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rh.stravamate.model.datalayer.primitives.Activity;
import com.rh.stravamate.R;
import com.rh.stravamate.model.datalayer.primitives.ActivityTypeDistinct;
import com.rh.stravamate.model.datalayer.tasks.GetActivities;
import com.rh.stravamate.model.datalayer.tasks.GetActivityTypes;
import com.rh.stravamate.ui.adapters.ActivityAdapter;

import java.util.List;
import java.util.Locale;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ActivityFragment extends BaseFragment {
    ArrayAdapter adapter;

    private OnListFragmentInteractionListener mListener;
    RecyclerView listView;
    private SwipeRefreshLayout swipeContainer;
    TextView progress;
    View frame;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ActivityFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ActivityFragment newInstance() {
        ActivityFragment fragment = new ActivityFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    String getLogTag() {
        return ActivityFragment.class.getSimpleName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        listView = (RecyclerView) view.findViewById(R.id.activities);
        progress = view.findViewById(R.id.progress);
        frame = view.findViewById(R.id.progress_frame);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setupSwipe(view);
        loadTypes();
        return view;
    }


    void loadActivities(String type) {
        dataLayer.loadActivities(type, new GetActivities.Callback() {
            @Override
            public void onSuccess(List<Activity> activities) {
                listView.setAdapter(new ActivityAdapter(activities, settings, getActivity()));

            }

            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onProgress(int done) {

            }
        });
    }

    void loadTypes() {
        dataLayer.getActivityTypes(new GetActivityTypes.Callback() {
            @Override
            public void onSuccess(List<ActivityTypeDistinct> types) {
                if (types.isEmpty()) {
                    dataLayer.loadActivities(null, new GetActivities.Callback() {
                        @Override
                        public void onSuccess(List<Activity> activities) {
                            loadTypes();
                        }

                        @Override
                        public void onError(Exception e) {

                        }

                        @Override
                        public void onProgress(int done) {

                        }
                    });
                } else {
                    loadTypeAdapter(types);
                }
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
                loadActivities(adapter.getItem(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
//        getMainActivity().getTypeSpinner().setOnItemSelectedListener(null);
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Activity item);
    }

    private void setupSwipe(View view) {
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    void refresh() {
        setProgressText(getString(R.string.loading));
        dataLayer.refreshActivities(new GetActivities.Callback() {
            @Override
            public void onSuccess(List<Activity> activities) {
                refreshComplete();
            }

            @Override
            public void onError(Exception e) {
                logging.e(getTag(), "refresh()", e);
                refreshComplete();

            }

            @Override
            public void onProgress(int done) {
                setProgress(done);
            }
        });
    }

    void refreshComplete() {
        swipeContainer.setRefreshing(false);
        frame.setVisibility(View.GONE);
    }

    void setProgress(final int count) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setProgressText(String.format(Locale.getDefault(), "Loaded : %d", count));
            }
        });
    }

    void setProgressText(String text) {
        frame.setVisibility(View.VISIBLE);
        progress.setText(text);
    }
}
