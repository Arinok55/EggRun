package com.example.eggrun.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eggrun.R;
import com.example.eggrun.classes.Player;

import org.jetbrains.annotations.NotNull;

public class CurrentEggFragment extends Fragment {
    private static final String TAG = "CurrentEggFragment";
    private final Player mPlayer;

    public CurrentEggFragment(Player player) {
        mPlayer = player;
    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        if (mPlayer.getEggList().isEmpty()){
            return inflater.inflate(R.layout.fragment_current_egg_empty, container, false);
        }
        else{
            View view = inflater.inflate(R.layout.fragment_current_number_eggs, container, false);
            RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);
            EggAdapter mEggAdapter = new EggAdapter(mPlayer);
            mRecyclerView.setAdapter(mEggAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            return view;
        }
    }
}
