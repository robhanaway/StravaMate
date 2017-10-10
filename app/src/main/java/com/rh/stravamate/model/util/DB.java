package com.rh.stravamate.model.util;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.rh.stravamate.model.primitives.Activity;
import com.rh.stravamate.model.primitives.Athlete;

import java.util.List;

/**
 * Created by robert.hanaway on 10/10/2017.
 */
@Dao
public interface DB {
    @Insert
    void insertAll(List<Athlete> athletes);

    @Insert
    void insertActivities(List<Activity> activities);

    @Query("SELECT * FROM athlete WHERE me = :me")
    List<Athlete> findAthlete(boolean me);


    @Query("DELETE FROM activity")
    void deleteAllActivites();

    @Query("SELECT * FROM activity")
    List<Activity> getActivities();
}
