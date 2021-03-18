package com.example.eggrun.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;
import com.example.eggrun.classes.Player;
import com.example.eggrun.classes.egg.Egg;
import com.example.eggrun.classes.egg.EggFactory;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

public class AddEggFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "AddEggFragment";
    private Player mPlayer;

    public AddEggFragment(Player player) {
        mPlayer = player;
    }

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

        return view;
    }

    public void onClick(View view){
        final int viewId = view.getId();

        if (viewId == R.id.addCommonEggButton){
            try {
                addEgg("common");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (viewId == R.id.addUncommonEggButton){
            try {
                addEgg("uncommon");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (viewId == R.id.addRareEggButton){
            try {
                addEgg("rare");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (viewId == R.id.addLegendaryEggButton){
            try {
                addEgg("legendary");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Log.d(TAG, "Error: Invalid button click");
        }
    }

    private void addEgg(String name) throws IOException {
        EggFactory eggFactory = new EggFactory();
        Egg egg = eggFactory.createEgg(name);
        mPlayer.addEgg(egg);
        Log.d(TAG,"Added " + egg.toString() + " egg to " + mPlayer.getName());

        ArrayList<Egg> eggList = mPlayer.getEggList();
        Log.d(TAG,"Number of eggs in " + mPlayer.getName() + ": " + eggList.size());
        Toast.makeText(getActivity().getApplicationContext(), "Added " + name + " Egg to the collection", Toast.LENGTH_SHORT).show();
    }
}
