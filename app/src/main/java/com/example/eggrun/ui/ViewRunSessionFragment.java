package com.example.eggrun.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;
import com.example.eggrun.classes.RunSession;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class ViewRunSessionFragment extends Fragment {
    private static final String TAG = "ViewRunSessionFragment";
    RunSession mRunSession;

    public ViewRunSessionFragment(RunSession runSession) {
        mRunSession = runSession;
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        View view = inflater.inflate(R.layout.fragment_run_session_details, container, false);

        TextView distance_view_display = view.findViewById(R.id.distance_view_display);
        double distance = mRunSession.getDistance();
        distance_view_display.setText(String.format("%.2f", distance) + " miles");

        int seconds = (int) mRunSession.getTimeRan();
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        TextView time_view_display = view.findViewById(R.id.time_view_display);
        String time = String.format(Locale.getDefault(),"%d:%02d:%02d", hours, minutes, secs);
        time_view_display.setText(time);

        TextView average_speed_display = view.findViewById(R.id.average_speed_display);
        double mph = mRunSession.getAvgSpeed() * 60 * 60;
        average_speed_display.setText(String.format("%.2f", mph) + " mph");

        return view;
    }
}
