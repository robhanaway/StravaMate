package com.rh.stravamate.model.util;

import android.content.Context;
import android.text.TextUtils;

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

    public String fromDate(Date date, String locale) {
        if (!TextUtils.isEmpty(locale)) {
            format.setTimeZone(TimeZone.getTimeZone(locale));
        }
        return format.format(date);
    }

    public String getSpeed(Activity activity) {
        String result = "";
        int duration = settings.isKilometres() ? activity.getAverageSpeedPerKm() : activity.getAverageSpeedPerMile();
        float distance = activity.getDistance() / (settings.isKilometres() ? Constants.METRES_IN_KM : Constants.METRES_IN_MILE);
        result = String.format(Locale.getDefault(), "%.2f%s", distance,
                context.getResources().getString(settings.isKilometres() ? R.string.km : R.string.mile));
        if (duration > 0) {
            int minutes = duration % 60;
            int hours = duration/60;
            result = result + String.format(Locale.getDefault(),
                    " %2d:%02d%s",
                    hours, minutes,
                    context.getResources().getString(settings.isKilometres() ? R.string.km : R.string.mile));
        }
        return result;
    }
}
