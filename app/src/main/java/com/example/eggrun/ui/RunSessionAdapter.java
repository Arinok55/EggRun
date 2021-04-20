package com.example.eggrun.ui;

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
import com.example.eggrun.classes.Bus;
import com.example.eggrun.classes.Player;
import com.example.eggrun.classes.RunSession;
import com.example.eggrun.classes.pet.Pet;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RunSessionAdapter extends RecyclerView.Adapter<RunSessionAdapter.ViewHolder>{
    private static final String TAG = "RunSessionAdapter";
    private Bus bus = Bus.getInstance();
    private Context mContext;
    private Pet mPet;

    public RunSessionAdapter(Pet pet){
        Log.d(TAG, "creating new RunSessionAdapter");
        mPet = pet;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        mContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.recycler_run_session_layout, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder viewHolder, int position){
        ArrayList<RunSession> runSessionList = (ArrayList<RunSession>) mPet.getRunSessionList();
        RunSession runSession = runSessionList.get(position);
        viewHolder.textView.setText(runSession.getDate().toString());
        if (bus.isDarkModeActive()){
            viewHolder.textView.setTextColor(Color.WHITE);
        }
        viewHolder.textButton.setOnClickListener(v -> {
            Log.d(TAG, "Opening ViewRunSessionActivity.");
            Intent intent = new Intent(mContext.getApplicationContext(), ViewRunSessionActivity.class);
            intent.putExtra("runSession", runSession);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount(){
        return mPet.getRunSessionList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public Button textButton;

        public ViewHolder(View view){
            super(view);
            textView = view.findViewById(R.id.date);
            textButton = view.findViewById(R.id.view_button);
        }
    }
}
