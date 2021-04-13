package com.example.eggrun.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.eggrun.R;
import com.example.eggrun.classes.BackgroundLocationService;
import com.example.eggrun.classes.Bus;
import com.example.eggrun.classes.Player;
import com.example.eggrun.classes.RunSession;
import com.example.eggrun.classes.egg.Egg;
import com.example.eggrun.classes.egg.EggFactory;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Objects;

public class RunSessionFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "RunSessionFragment";

    BackgroundLocationService backgroundLocationService;
    private static Bus bus = Bus.getInstance();
    private Egg mEgg;
    private boolean eggAdded = false;

    //distance ran
    private float distance;
    private TextView timerText;
    private TextView distanceText;

    //total time ran
    private int seconds;

    public RunSessionFragment(int position){
        mEgg = bus.getPlayer().getEggList().get(position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_run_session, container, false);
        distance = 0f;
        seconds = 0;
        timerText = view.findViewById(R.id.time_view);
        distanceText = view.findViewById(R.id.distance_view);

        changeDistanceText();
        changeTimerText();
        Button endRunButton = view.findViewById(R.id.endRunButton);
        endRunButton.setOnClickListener(this);
        return view;
    }

    public void onClick(View view) {
        final int viewId = view.getId();
        if(viewId == R.id.endRunButton){
            eggAdded = addData(mEgg, distance);
            backgroundLocationService.removeLocationUpdates();

        }

    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void changeTimerText() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        String time
                = String
                .format(Locale.getDefault(),
                        "%d:%02d:%02d", hours,
                        minutes, secs);
        timerText.setText(time);
    }

    public void changeDistanceText() {
        distanceText.setText(String.format("%.2f", distance));
    }

    private boolean addData(Egg egg, double distance){
        Log.d(TAG, "Adding egg.");
        RunSession runSession = new RunSession(distance, seconds);
        egg.addRunSession(runSession);
        if (bus.getPlayer().saveData()){
            Intent intent = new Intent(this.getContext(), CurrentEggActivity.class);
            requireActivity().finish();
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop()");
        if (!eggAdded) {
            addData(mEgg, distance);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"Resuming run frag");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Destroying run frag");
    }

    public void setBackgroundLocationService(BackgroundLocationService backgroundLocationService) {
        this.backgroundLocationService = backgroundLocationService;
    }
}
