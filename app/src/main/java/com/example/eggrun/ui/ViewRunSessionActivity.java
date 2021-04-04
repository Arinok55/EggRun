package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import com.example.eggrun.classes.Player;
import com.example.eggrun.classes.RunSession;

public class ViewRunSessionActivity extends SingleFragmentActivity{
    private static final String TAG = "ViewRunSessionActivity";

    @Override
    protected Fragment createFragment(){
        return new ViewRunSessionFragment((RunSession) getIntent().getSerializableExtra("runSession"));
    }
}
