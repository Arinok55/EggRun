package com.example.eggrun.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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
import com.example.eggrun.classes.RunSession;
import com.example.eggrun.classes.egg.Egg;

import java.util.ArrayList;

public class EggAdapter extends RecyclerView.Adapter<EggAdapter.ViewHolder>{
    private static final String TAG = "EggAdapter";
    private ArrayList<Egg> eggList;
    private Context mContext;

    public EggAdapter(Player player, Context context){
        Log.d(TAG, "creating new EggAdapter");
        eggList = player.getEggList();
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.egg_layout, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){
        Egg egg = eggList.get(position);
        viewHolder.textView.setText(egg.DistanceToHatch() + " miles");
        viewHolder.imageView.setImageResource(egg.getImageId());
        viewHolder.textButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Opening test_egg_hatch_fragment.");
                //Intent intent = new Intent(mContext.getActivity(), TEST_EGG_HATCH.class);
                //intent.putExtra("egg", egg);
                //mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return eggList.size();
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
