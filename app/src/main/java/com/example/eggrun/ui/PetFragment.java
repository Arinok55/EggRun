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
import com.example.eggrun.classes.Bus;
import com.example.eggrun.classes.Player;

import org.jetbrains.annotations.NotNull;

public class PetFragment extends Fragment {
    private static final String TAG = "PetFragment";
    private static Bus bus = Bus.getInstance();

    public PetFragment(){}

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        if (bus.getPlayer().getPetList().isEmpty()){
            return inflater.inflate(R.layout.fragment_pets_empty, container, false);
        }
        else{
            View view = inflater.inflate(R.layout.fragment_number_pets, container, false);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            PetAdapter petAdapter = new PetAdapter();
            recyclerView.setAdapter(petAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            return view;
        }
    }
}