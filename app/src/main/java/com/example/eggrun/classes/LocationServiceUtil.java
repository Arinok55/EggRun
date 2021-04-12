package com.example.eggrun.classes;

import android.content.Context;
import android.location.Location;
import android.preference.PreferenceManager;

public class LocationServiceUtil {
    public static final String KEY_REQUESTING_LOCATION_UPDATES = "LocationUpdateEnable";

    public static String getLocationText(Location currentLocation) {
        return currentLocation == null ? "Unknown Location": new StringBuilder()
                .append(currentLocation.getLatitude())
                .append("/")
                .append(currentLocation.getLongitude())
                .toString();
    }

    public static void setRequestingLocationUpdates(Context context, boolean value) {
        PreferenceManager.
                getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(KEY_REQUESTING_LOCATION_UPDATES,value)
                .apply();
    }

    public static boolean requestingLocationUpdates(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(KEY_REQUESTING_LOCATION_UPDATES,false);
    }
}
