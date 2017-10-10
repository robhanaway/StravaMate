package com.rh.stravamate.model.util;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by robert.hanaway on 10/10/2017.
 */
@Singleton
public class Logging {

    @Inject
    public Logging() {

    }

    public void d(String tag, String format, Object... args) {
        Log.d(tag, getFormattedString(format, args));
    }

    String getFormattedString(String format, Object... args) {
        if(args != null && args.length > 0 && format != null) {
            try {
                return String.format(format, args);
            } catch (OutOfMemoryError memoryError) {
                return format;
            }
        } else {
            return format;
        }
    }
}
