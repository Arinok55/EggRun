package com.example.eggrun.ui;

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
import com.example.eggrun.classes.Player;
import com.example.eggrun.classes.RunSession;
import com.example.eggrun.classes.egg.Egg;
import com.example.eggrun.classes.egg.EggFactory;

import org.w3c.dom.Text;

import java.util.Locale;

public class RunSessionFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "RunSessionFragment";

    private Player mPlayer;
    private int mPosition;

    //distance ran
    private float distance;
    private TextView timerText;
    private TextView distanceText;
    private Button endRunButton;

    //total time ran
    private int seconds = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_run_session, container, false);
        timerText = view.findViewById(R.id.time_view);
        distanceText = view.findViewById(R.id.distance_view);
        endRunButton = view.findViewById(R.id.endRunButton);
        endRunButton.setOnClickListener(this);
        distance = 0;
        runTimerAndDistance();
        return view;
    }
    public RunSessionFragment(Player player, int position){
        mPlayer = player;
        mPosition = position;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void onClick(View view) {
        final int viewId = view.getId();
        Egg egg = mPlayer.getEggList().get(mPosition);
        if(viewId == R.id.endRunButton){
            addData(egg, distance);
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

    private void addData(Egg egg, double distance){
        RunSession runSession = new RunSession(distance, seconds);
        egg.addRunSession(runSession);
        if (mPlayer.saveData()){
            Intent intent = new Intent(getActivity(), CurrentEggActivity.class);
            intent.putExtra("player", mPlayer);
            getActivity().finish();
            startActivity(intent);
        }
    }
}
