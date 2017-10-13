package com.rh.stravamate.model.datalayer.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.rh.stravamate.model.datalayer.primitives.Activity;
import com.rh.stravamate.model.datalayer.primitives.ActivityTypeDistinct;
import com.rh.stravamate.model.datalayer.primitives.Athlete;

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

    @Query("SELECT * FROM activity WHERE type = :type")
    List<Activity> getActivitiesByType(String type);

    @Query("SELECT * FROM activity ORDER by average_speed DESC LIMIT 1")
    List<Activity> getFastestActivity();

    @Query("SELECT type, COUNT(type) FROM activity GROUP BY type")
    List<ActivityTypeDistinct> getActivityTypes();

    @Query("SELECT AVG(average_heartrate) FROM activity where has_heartrate <> 0 AND type = :type")
    float getAverageHeartRate(String type);

    @Query("SELECT AVG(average_speed) FROM activity where type = :type")
    float getAverageSpeed(String type);

    @Query("SELECT AVG(distance) FROM activity where type = :type")
    float getAverageDistance(String type);

    @Query("SELECT * FROM activity where has_heartrate <> 0 AND type = :type ORDER BY average_heartrate DESC LIMIT 1,1")
    Activity getMaxAverageHeartRate(String type);

    @Query("SELECT * FROM activity where has_heartrate <> 0 AND type = :type ORDER BY average_heartrate ASC LIMIT 1,1")
    Activity getMinAverageHeartRate(String type);

    @Query("SELECT * FROM activity where type = :type ORDER BY average_speed DESC LIMIT 1,1")
    Activity getMaxAverageSpeed(String type);

    @Query("SELECT * FROM activity where type = :type ORDER BY average_speed ASC LIMIT 1,1")
    Activity getMinAverageSpeed(String type);

    @Query("SELECT * FROM activity where type = :type ORDER BY distance DESC LIMIT 1,1")
    Activity getLongestDistance(String type);
}
