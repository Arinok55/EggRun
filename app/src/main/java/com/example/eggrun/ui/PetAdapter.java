package com.example.eggrun.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.eggrun.R;
import com.example.eggrun.classes.Player;
import com.example.eggrun.classes.egg.Egg;
import com.example.eggrun.classes.pet.Pet;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder>{
    private static final String TAG = "PetAdapter";
    private final Player mPlayer;
    private Context mContext;

    public PetAdapter(Player player){
        Log.d(TAG, "creating new PetAdapter");
        mPlayer = player;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        mContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.recycler_pet_layout, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder viewHolder, int position){
        ArrayList<Pet> petList = mPlayer.getPetList();
        Pet pet = petList.get(position);
        viewHolder.imageView.setImageResource(pet.getImage());
        viewHolder.textButton.setOnClickListener(v -> {
            /*
            Log.d(TAG, "Opening test_egg_hatch_fragment.");
            Intent intent = new Intent(mContext.getApplicationContext(), TEST_EGG_HATCH.class);
            intent.putExtra("player", mPlayer);
            intent.putExtra("position", position);
            mContext.startActivity(intent);
            */
        });
    }

    @Override
    public int getItemCount(){
        return mPlayer.getPetList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public Button textButton;

        public ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.pet_View);
            textButton = view.findViewById(R.id.pet_button);
        }
    }
}
