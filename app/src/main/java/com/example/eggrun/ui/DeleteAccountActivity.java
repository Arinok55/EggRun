package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import java.io.File;

public class DeleteAccountActivity extends SingleFragmentActivity {
    private static final String TAG = "DeleteAccountActivity";

    @Override
    protected Fragment createFragment(){
        return new DeleteAccountFragment();
    }
}