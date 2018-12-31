package com.udylity.sandbarfinder.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.udylity.sandbarfinder.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

    private static final String DATE_FORMAT = "K:mma";

    public static String getPreferredLocation(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(context.getString(R.string.pref_location_key), context.getString(R.string.pref_location_default));
    }

    public static String getReadableDateString(long time){
        Date date = new Date(time * 1000);
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(date);
    }

}
