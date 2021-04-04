package com.example.eggrun.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eggrun.R;
import com.example.eggrun.classes.Player;
import com.example.eggrun.classes.pet.Pet;

import org.jetbrains.annotations.NotNull;

public class ViewPetFragment extends Fragment {
    private static final String TAG = "ViewPetFragment";
    private final Pet mPet;

    public ViewPetFragment(Pet pet) {
        mPet = pet;
    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        View view = inflater.inflate(R.layout.fragment_pets_run_session, container, false);
        ImageView imageView = view.findViewById(R.id.petImage);
        imageView.setImageResource(mPet.getImage());

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        RunSessionAdapter runSessionAdapter = new RunSessionAdapter(mPet);
        recyclerView.setAdapter(runSessionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;

    }
}
