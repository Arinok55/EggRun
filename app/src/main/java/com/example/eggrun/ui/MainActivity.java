package com.example.eggrun.ui;

import android.util.Log;
import androidx.fragment.app.Fragment;

import com.example.eggrun.classes.Bus;

import java.io.File;

public class MainActivity extends SingleFragmentActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected Fragment createFragment() {
        Bus bus = Bus.getInstance();
        File dir = new File(getApplicationContext().getFilesDir(), "EggRunDirectory");
        bus.setDirectory(dir);
        Log.d(TAG, "Starting MainActivityFragment");
        return new MainActivityFragment();
    }
}
