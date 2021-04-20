package com.example.eggrun.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;
import com.example.eggrun.classes.Bus;
import com.example.eggrun.classes.egg.Egg;
import com.example.eggrun.classes.egg.EggFactory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AddEggFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "AddEggFragment";
    private Bus bus = Bus.getInstance();

    public AddEggFragment() {}

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        View view = inflater.inflate(R.layout.fragment_add_egg, container, false);
        ImageButton addCommonEggButton = view.findViewById(R.id.addCommonEggButton);
        addCommonEggButton.setOnClickListener(this);
        ImageButton addUncommonEggButton = view.findViewById(R.id.addUncommonEggButton);
        addUncommonEggButton.setOnClickListener(this);
        ImageButton addRareEggButton = view.findViewById(R.id.addRareEggButton);
        addRareEggButton.setOnClickListener(this);
        ImageButton addLegendaryEggButton = view.findViewById(R.id.addLegendaryEggButton);
        addLegendaryEggButton.setOnClickListener(this);

        if (bus.isDarkModeActive()) {
            view.getRootView().setBackgroundColor(Color.BLACK);
            ImageView title = view.findViewById(R.id.eggRunTitle);
            title.setImageResource(R.drawable.egg_run_title_dark);

            addCommonEggButton.setBackgroundColor(Color.BLACK);
            addUncommonEggButton.setBackgroundColor(Color.BLACK);
            addRareEggButton.setBackgroundColor(Color.BLACK);
            addLegendaryEggButton.setBackgroundColor(Color.BLACK);

            TextView text = view.findViewById(R.id.commonEggText);
            text.setTextColor(Color.WHITE);
            text = view.findViewById(R.id.uncommonEggText);
            text.setTextColor(Color.WHITE);
            text = view.findViewById(R.id.rareEggText);
            text.setTextColor(Color.WHITE);
            text = view.findViewById(R.id.legendaryEggText);
            text.setTextColor(Color.WHITE);
        }
        return view;
    }

    public void onClick(View view){
        final int viewId = view.getId();

        if (viewId == R.id.addCommonEggButton){
            addEgg("common");
        }
        else if (viewId == R.id.addUncommonEggButton){
            addEgg("uncommon");
        }
        else if (viewId == R.id.addRareEggButton){
            addEgg("rare");
        }
        else if (viewId == R.id.addLegendaryEggButton){
            addEgg("legendary");
        }
        else {
            Log.d(TAG, "Error: Invalid button click");
        }
    }

    private void addEgg(String name) {
        EggFactory eggFactory = new EggFactory();
        Egg egg = eggFactory.createEgg(name);
        bus.getPlayer().addEgg(egg);
        Log.d(TAG,"Added " + egg.toString() + " egg to " + bus.getPlayer().getName());

        ArrayList<Egg> eggList = bus.getPlayer().getEggList();
        Log.d(TAG,"Number of eggs in " + bus.getPlayer().getName() + ": " + eggList.size());
        Toast.makeText(requireActivity().getApplicationContext(), "Added " + name + " Egg to the collection", Toast.LENGTH_SHORT).show();
    }
}
