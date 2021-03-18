package com.example.eggrun.ui;

import android.util.Log;
import androidx.fragment.app.Fragment;
import java.io.File;

public class MainActivity extends SingleFragmentActivity {
    private static final String TAG = "MainActivity";
    private MainActivityFragment mainActivityFragment;
    private File mAppDirectory;
    private String mAppDirectoryFilename = "EggRunDirectory";

    @Override
    protected Fragment createFragment() {
        if (mainActivityFragment == null) {
            setDirectory();
            Log.d(TAG, "Starting MainActivityFragment");
            mainActivityFragment = new MainActivityFragment(mAppDirectory);
        }
        return mainActivityFragment;
    }

    private void setDirectory() {
        mAppDirectory = new File(getApplicationContext().getFilesDir(), mAppDirectoryFilename);
        if (!mAppDirectory.isDirectory()) {
            Log.d(TAG, "Creating " + mAppDirectoryFilename);
            if (mAppDirectory.mkdir()) {
                Log.d(TAG, "Success");
            } else {
                Log.d(TAG, "Failed");
            }
        }
    }
}
