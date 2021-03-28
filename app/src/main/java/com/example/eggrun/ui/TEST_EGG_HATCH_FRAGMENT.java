package com.example.eggrun.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;
import com.example.eggrun.classes.RunSession;
import com.example.eggrun.classes.egg.Egg;

import org.jetbrains.annotations.NotNull;

public class TEST_EGG_HATCH_FRAGMENT extends Fragment implements View.OnClickListener{
    private static final String TAG = "TEST_EGG_HATCH_FRAGMENT";
    private Egg mEgg;

    TEST_EGG_HATCH_FRAGMENT(Egg egg){
        mEgg = egg;
    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        View view = inflater.inflate(R.layout.test_egg_hatch_fragment, container, false);

        Button button1 = view.findViewById(R.id.button);
        button1.setOnClickListener(this);
        Button button2 = view.findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = view.findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Button button4 = view.findViewById(R.id.button4);
        button4.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();

        if (viewId == R.id.button){
            Log.d(TAG, "Adding 0.25 miles to " + mEgg.toString());
            RunSession runSession = new RunSession(0.25);
            mEgg.addRunSession(runSession);
        }
        else if (viewId == R.id.button2){
            Log.d(TAG, "Adding 0.5 miles to " + mEgg.toString());
            RunSession runSession = new RunSession(0.5);
            mEgg.addRunSession(runSession);
        }
        else if (viewId == R.id.button3){
            Log.d(TAG, "Adding 1 mile to " + mEgg.toString());
            RunSession runSession = new RunSession(1);
            mEgg.addRunSession(runSession);
        }
        else if (viewId == R.id.button4){
            Log.d(TAG, "Adding 2 miles to " + mEgg.toString());
            RunSession runSession = new RunSession(2);
            mEgg.addRunSession(runSession);
        }
        else{
            Log.d(TAG, "Error! unknown button press.");
        }
    }
}
