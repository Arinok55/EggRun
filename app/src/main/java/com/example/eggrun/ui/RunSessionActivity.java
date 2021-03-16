package com.example.eggrun.ui;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import com.example.eggrun.R;
import com.example.eggrun.classes.egg.EggFactory;

public class RunSessionActivity extends SingleFragmentActivity {
    private static final String TAG = "RunSessionActivity";
    private RunSessionFragment runSessionFragment;

    @Override
    protected Fragment createFragment() {
        if (runSessionFragment == null){
            Intent intent = getIntent();
            runSessionFragment = new RunSessionFragment(intent.getStringExtra("egg_type"));
        }
        return runSessionFragment;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_session);
    }
}

