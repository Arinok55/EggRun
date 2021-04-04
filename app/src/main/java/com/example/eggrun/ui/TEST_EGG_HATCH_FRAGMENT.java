package com.example.eggrun.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;
import com.example.eggrun.classes.Player;
import com.example.eggrun.classes.RunSession;
import com.example.eggrun.classes.egg.Egg;

import org.jetbrains.annotations.NotNull;

public class TEST_EGG_HATCH_FRAGMENT extends Fragment implements View.OnClickListener{
    private static final String TAG = "TEST_EGG_HATCH_FRAGMENT";
    private Player mPlayer;
    private int mPosition;

    TEST_EGG_HATCH_FRAGMENT(Player player, int position){
        mPlayer = player;
        mPosition = position;
    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        View view = inflater.inflate(R.layout.test_egg_hatch_fragment, container, false);

        Button button1 = view.findViewById(R.id.button);
        button1.setOnClickListener(this);
        Button button2 = view.findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = view.findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Button button4 = view.findViewById(R.id.button4);
        button4.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        Egg egg = mPlayer.getEggList().get(mPosition);

        if (viewId == R.id.button){
            addData(egg, 0.25);
        }
        else if (viewId == R.id.button2){
            addData(egg, 0.5);
        }
        else if (viewId == R.id.button3){
            addData(egg, 1);
        }
        else if (viewId == R.id.button4){
            addData(egg, 2);
        }
        else{
            Log.d(TAG, "Error! unknown button press.");
        }
    }

    private void addData(Egg egg, double distance){
        Log.d(TAG, "Adding " + distance + " miles to " + egg.toString());
        RunSession runSession = new RunSession(distance, 1);
        egg.addRunSession(runSession);
        if (mPlayer.saveData()){
            Log.d(TAG, "Success!");
            Intent intent = new Intent(getActivity(), CurrentEggActivity.class);
            intent.putExtra("player", mPlayer);
            getActivity().finish();
            startActivity(intent);
        }
    }
}
