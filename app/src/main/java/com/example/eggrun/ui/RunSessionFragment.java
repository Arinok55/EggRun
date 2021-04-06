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

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;
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

    private static Bus bus = Bus.getInstance();
    private Egg mEgg;
    private boolean eggAdded = false;

    //distance ran
    private float distance = 0;
    private TextView timerText;
    private TextView distanceText;

    //total time ran
    private int seconds = 0;

    public RunSessionFragment(int position){
        mEgg = bus.getPlayer().getEggList().get(position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_run_session, container, false);
        timerText = view.findViewById(R.id.time_view);
        distanceText = view.findViewById(R.id.distance_view);
        Button endRunButton = view.findViewById(R.id.endRunButton);
        endRunButton.setOnClickListener(this);
        runTimerAndDistance();
        return view;
    }

    public void onClick(View view) {
        final int viewId = view.getId();
        if(viewId == R.id.endRunButton){
            eggAdded = addData(mEgg, distance);
        }

    }

    private void runTimerAndDistance()
    {
        // Creates a new Handler
        final Handler handler
                = new Handler();

        // Call the post() method,
        // passing in a new Runnable.
        // The post() method processes
        // code without a delay,
        // so the code in the Runnable
        // will run almost immediately.
        handler.post(new Runnable() {
            @SuppressLint("DefaultLocale")
            @Override
            public void run()
            {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                // Format the seconds into hours, minutes,
                // and seconds.
                String time
                        = String
                        .format(Locale.getDefault(),
                                "%d:%02d:%02d", hours,
                                minutes, secs);

                // Set the text view text.
                timerText.setText(time);
                //Set the distance
                distanceText.setText(String.format("%.2f", distance));

                seconds++;

                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    private boolean addData(Egg egg, double distance){
        Log.d(TAG, "Adding egg.");
        RunSession runSession = new RunSession(distance, seconds);
        egg.addRunSession(runSession);
        if (bus.getPlayer().saveData()){
            Intent intent = new Intent(getActivity(), CurrentEggActivity.class);
            requireActivity().finish();
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop()");
        if (!eggAdded) {
            addData(mEgg, distance);
        }
    }
}
