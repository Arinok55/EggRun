package com.example.eggrun.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eggrun.R;
import com.example.eggrun.classes.Bus;
import com.example.eggrun.classes.Player;

import org.jetbrains.annotations.NotNull;

public class CurrentEggFragment extends Fragment {
    private static final String TAG = "CurrentEggFragment";
    private final Bus bus = Bus.getInstance();

    public CurrentEggFragment() {}

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        View view;
        if (bus.getPlayer().getEggList().isEmpty()){
            view = inflater.inflate(R.layout.fragment_current_egg_empty, container, false);
            if (bus.isDarkModeActive()){
                view.setBackgroundColor(Color.BLACK);
                ImageView title = view.findViewById(R.id.egg_Run_Title);
                title.setImageResource(R.drawable.egg_run_title_dark);

                TextView text = view.findViewById(R.id.textView1);
                text.setTextColor(Color.WHITE);
            }
        }
        else{
            view = inflater.inflate(R.layout.fragment_current_number_eggs, container, false);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            EggAdapter eggAdapter = new EggAdapter(bus.getPlayer());
            recyclerView.setAdapter(eggAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            if (bus.isDarkModeActive()){
                view.setBackgroundColor(Color.BLACK);
                ImageView title = view.findViewById(R.id.egg_Run_Title);
                title.setImageResource(R.drawable.egg_run_title_dark);
            }
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"Resuming egg frag");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"Stopping egg frag");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Destroying egg frag");
    }
}
