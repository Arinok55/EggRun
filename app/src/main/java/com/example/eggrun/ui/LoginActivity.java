package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;

public class LoginActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new LoginFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.fragment_login);
    }
}