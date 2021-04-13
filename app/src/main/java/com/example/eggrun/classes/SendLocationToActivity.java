package com.example.eggrun.classes;

import android.location.Location;

public class SendLocationToActivity {
    private Location location;

    public SendLocationToActivity(Location currentLocation) {
        this.location = currentLocation;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
