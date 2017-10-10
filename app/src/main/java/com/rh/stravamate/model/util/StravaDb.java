package com.rh.stravamate.model.util;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.rh.stravamate.model.primitives.Activity;
import com.rh.stravamate.model.primitives.Athlete;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by robert.hanaway on 10/10/2017.
 */
@Singleton
public class StravaDb {
    DB db;
    @Inject
    public StravaDb(Context context) {
        db = Room.databaseBuilder(context, AbstractStravaDB.class, "strava.db").build().db();
    }

    public DB getDb() {
        return db;
    }

    public Athlete getMe() {
        Athlete result = null;
        List<Athlete> athletes = db.findAthlete(true);
        if (!athletes.isEmpty()) {
            result = athletes.get(0);
        }
        return result;
    }

    public void insertActivities(List<Activity> activities) {
        new AsyncTask<List<Activity>, Void, Void>() {
            @Override
            protected Void doInBackground(List<Activity>[] lists) {
                db.insertActivities(lists[0]);
                return null;
            }
        }.execute(activities);
    }

    public void storeMe(final Athlete athlete) {
        athlete.setMe(true);
        new AsyncTask<Athlete, Void, Void>() {
            @Override
            protected Void doInBackground(Athlete... athletes) {
                List<Athlete> me = db.findAthlete(true);
                if (me.isEmpty()) {
                    ArrayList<Athlete> athleteList = new ArrayList<>();
                    athleteList.add(athletes[0]);
                    db.insertAll(athleteList);
                }
                return null;
            }
        }.execute(athlete);
    }


}
