package com.example.eggrun.ui;

import android.util.Log;
import androidx.fragment.app.Fragment;

import com.example.eggrun.classes.Bus;

import java.io.File;

public class MainActivity extends SingleFragmentActivity {
    private static final String TAG = "MainActivity";
    private MainActivityFragment mainActivityFragment;

    @Override
    protected Fragment createFragment() {
        if (mainActivityFragment == null) {
            Bus bus = Bus.getInstance();
            String mAppDirectoryFilename = "EggRunDirectory";
            File dir = new File(getApplicationContext().getFilesDir(), "EggRunDirectory");
            if (!dir.isDirectory()){
                dir.mkdir();
            }
            bus.setDirectory(dir);
            Log.d(TAG, "Starting MainActivityFragment");
            mainActivityFragment = new MainActivityFragment();
        }
        return mainActivityFragment;
    }
}
