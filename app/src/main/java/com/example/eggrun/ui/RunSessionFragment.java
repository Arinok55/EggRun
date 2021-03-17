package com.example.eggrun.ui;

import android.media.effect.EffectFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;
import com.example.eggrun.classes.RunSession;
import com.example.eggrun.classes.egg.EggFactory;

public class RunSessionFragment extends Fragment {
    private RunSession runSession;
    private EggFactory eggFactory;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_run_session, container, false);
        return v;
    }
    public RunSessionFragment(String eggType){
        eggFactory = new EggFactory();
        runSession = new RunSession(eggFactory.createEgg(eggType));
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
