package com.example.eggrun.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;

public class EggSelectionFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_egg_selection, container, false);

        Button commonButton = v.findViewById(R.id.commonButton);
        commonButton.setOnClickListener(this);

        Button uncommonButton = v.findViewById(R.id.uncommonButton);
        uncommonButton.setOnClickListener(this);

        Button rareButton = v.findViewById(R.id.rareButton);
        rareButton.setOnClickListener(this);

        Button legendaryButton = v.findViewById(R.id.legendaryButton);
        legendaryButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void onClick(View v) {
        final int viewId = v.getId();

        if(viewId == R.id.commonButton){
            //start Run Session with common egg
            Intent intent = new Intent(EggSelectionFragment.this.getActivity(), RunSessionActivity.class);
            intent.putExtra("egg_type", "COMMON");
            startActivity(intent);
        }else if(viewId == R.id.uncommonButton){
            //start Run Session with common egg
            Intent intent = new Intent(EggSelectionFragment.this.getActivity(), RunSessionActivity.class);
            intent.putExtra("egg_type", "UNCOMMON");
            startActivity(intent);
        }else if(viewId == R.id.rareButton){
            //start Run Session with common egg
            Intent intent = new Intent(EggSelectionFragment.this.getActivity(), RunSessionActivity.class);
            intent.putExtra("egg_type", "RARE");
            startActivity(intent);
        }else if(viewId == R.id.legendaryButton){
            //start Run Session with common egg
            Intent intent = new Intent(EggSelectionFragment.this.getActivity(), RunSessionActivity.class);
            intent.putExtra("egg_type", "LEGENDARY");
            startActivity(intent);
        }

    }
}
