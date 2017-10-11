package com.rh.stravamate.model.util;

import android.content.Context;

import com.rh.stravamate.R;
import com.rh.stravamate.model.config.Settings;
import com.rh.stravamate.model.datalayer.primitives.Activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by robert.hanaway on 11/10/2017.
 */

public class Converter {
    final Settings settings;
    final Context context;
    SimpleDateFormat format = new SimpleDateFormat("E d MMM HH:mm", Locale.getDefault());

    public Converter(Settings settings, Context context) {
        this.settings = settings;
        this.context = context;
        format.setTimeZone(TimeZone.getDefault());
    }

    public String fromDate(Date date) {
        return format.format(date);
    }

    public String getSpeed(Activity activity) {
        String result = "";
        int duration = settings.isKilometres() ? activity.getAverageSpeedPerKm() : activity.getAverageSpeedPerMile();
        if (duration > 0) {
            int minutes = duration % 60;
            int hours = duration/60;
            result = String.format(Locale.getDefault(),
                    "%02d:%02d%s",
                    hours, minutes,
                    context.getResources().getString(settings.isKilometres() ? R.string.km : R.string.mile));
        }
        return result;
    }
}