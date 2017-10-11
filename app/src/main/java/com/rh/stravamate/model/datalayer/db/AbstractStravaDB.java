package com.rh.stravamate.model.datalayer.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.rh.stravamate.model.datalayer.primitives.Activity;
import com.rh.stravamate.model.datalayer.primitives.Athlete;

/**
 * Created by robert.hanaway on 10/10/2017.
 */
@Database(entities = {Athlete.class, Activity.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class AbstractStravaDB extends RoomDatabase{
    public abstract DB db();
}
