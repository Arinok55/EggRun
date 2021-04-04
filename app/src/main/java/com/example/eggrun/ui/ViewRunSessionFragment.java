package com.example.eggrun.ui;

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

public class ViewRunSessionFragment extends Fragment {
    private static final String TAG = "ViewRunSessionFragment";
    RunSession mRunSession;

    public ViewRunSessionFragment(RunSession runSession) {
        mRunSession = runSession;
    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        View view = inflater.inflate(R.layout.fragment_run_session_details, container, false);

        TextView distance_view_display = view.findViewById(R.id.distance_view_display);
        distance_view_display.setText(Double.toString(mRunSession.getDistance()));

        TextView time_view_display = view.findViewById(R.id.time_view_display);
        time_view_display.setText(Float.toString(mRunSession.getTimeRan()));

        TextView average_speed_display = view.findViewById(R.id.average_speed_display);
        average_speed_display.setText(Double.toString(mRunSession.getAvgSpeed()));

        return view;
    }
}
