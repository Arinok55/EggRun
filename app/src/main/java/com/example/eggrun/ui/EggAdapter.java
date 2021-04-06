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
import com.example.eggrun.classes.Bus;
import com.example.eggrun.classes.Player;
import com.example.eggrun.classes.egg.Egg;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class EggAdapter extends RecyclerView.Adapter<EggAdapter.ViewHolder>{
    private static final String TAG = "EggAdapter";
    private final Bus bus = Bus.getInstance();
    private Context mContext;

    public EggAdapter(Player player){
        Log.d(TAG, "creating new EggAdapter");
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        mContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.recycler_egg_layout, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder viewHolder, int position){
        ArrayList<Egg> eggList = bus.getPlayer().getEggList();
        Egg egg = eggList.get(position);
        viewHolder.imageView.setImageResource(egg.getImageId());

        if (egg.canHatch()){
            viewHolder.textView.setText("Ready");
            viewHolder.textButton.setOnClickListener(v -> {
                Log.d(TAG, "Opening HatchEggActivity.");
                Intent intent = new Intent(mContext.getApplicationContext(), HatchEggActivity.class);
                intent.putExtra("position", position);
                mContext.startActivity(intent);
                ((Activity)mContext).finish();
            });
            viewHolder.textButton.setText("Hatch");
            viewHolder.textButton.setBackgroundColor(Color.RED);
        }
        else {
            viewHolder.textView.setText(String.format("%.2f", egg.DistanceToHatch()) + " miles");
            viewHolder.textButton.setOnClickListener(v -> {
                Log.d(TAG, "Opening test_egg_hatch_fragment.");
                //run session goes here egg hatch
                Intent intent = new Intent(mContext.getApplicationContext(), RunSessionActivity.class);
                intent.putExtra("position", position);
                mContext.startActivity(intent);
                ((Activity)mContext).finish();
            });
        }
    }

    @Override
    public int getItemCount(){
        return bus.getPlayer().getEggList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;
        public Button textButton;

        public ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.egg_View);
            textView = view.findViewById(R.id.egg_text);
            textButton = view.findViewById(R.id.egg_button);
        }
    }
}
