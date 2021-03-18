package com.example.eggrun.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;
import com.example.eggrun.classes.Player;
import com.example.eggrun.classes.egg.Egg;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CurrentEggFragment extends Fragment {
    private static final String TAG = "CurrentEggFragment";
    private Player mPlayer;

    public CurrentEggFragment(Player player) {
        mPlayer = player;
    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        ArrayList<Egg> eggList = mPlayer.getEggList();
        int layout;

        if (eggList.isEmpty()){
            layout = R.layout.fragment_current_egg_empty;
            return inflater.inflate(layout, container, false);
        }
        else{
            layout = R.layout.fragment_current_number_eggs;
            View view = inflater.inflate(layout, container, false);
            TextView textView = (TextView) view.findViewById(R.id.numOfEggs);
            textView.setText(String.valueOf(eggList.size()));
            return view;
        }
    }
}
