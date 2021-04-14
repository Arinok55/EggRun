package com.example.eggrun.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;
import com.example.eggrun.classes.Bus;
import com.example.eggrun.classes.Player;
import com.example.eggrun.classes.Security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

public class NewAccountFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "NewAccountFragment";
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private EditText mConfirmEditText;

    private Bus bus = Bus.getInstance();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        View view = inflater.inflate(R.layout.fragment_new_account, container, false);

        mUsernameEditText = view.findViewById(R.id.username_text);
        mPasswordEditText = view.findViewById(R.id.password_text);
        mConfirmEditText = view.findViewById(R.id.confirm_text);

        Button createAccountButton = view.findViewById(R.id.create_account_button);
        createAccountButton.setOnClickListener(this);
        Button loginButton = view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);
        Button cancelButton = view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        if (viewId == R.id.create_account_button) {
            try {
                createAccount();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (viewId == R.id.login_button) {
            Intent intent = new Intent(getActivity(), ChangeAccountActivity.class);
            startActivity(intent);
            Activity activity = getActivity();
            assert activity != null;
            activity.finish();
        }
        else if(viewId == R.id.cancel_button){
            Activity activity = getActivity();
            assert activity != null;
            activity.finish();
        }
        else{
            Log.d(TAG, "Error: Invalid button click");
        }
    }

    private void createAccount() {
        Activity activity = getActivity();
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        String confirm = mConfirmEditText.getText().toString();

        if (password.equals(confirm) && !username.equals("") && !password.equals("") && password.length() >= 10 && uniqueUsername(username)) {
            if (insertPlayer(username, password)) {
                assert activity != null;
                Toast.makeText(activity.getApplicationContext(), "New Account Created", Toast.LENGTH_SHORT).show();
                activity.finish();
            }
            else{
                assert activity != null;
                Toast.makeText(activity.getApplicationContext(), "Account already exists", Toast.LENGTH_SHORT).show();
            }
        }
        else if ((username.equals("")) || (password.equals("")) || (confirm.equals(""))) {
            assert activity != null;
            Toast.makeText(activity.getApplicationContext(), "Missing entry", Toast.LENGTH_SHORT).show();
        }
        else if (!password.equals(confirm)){
            assert activity != null;
            Toast.makeText(activity.getApplicationContext(), "Password and Confirm do not match", Toast.LENGTH_SHORT).show();
        }
        else if (password.length() < 10){
            assert activity != null;
            Toast.makeText(activity.getApplicationContext(), "Password is not at least 10 characters", Toast.LENGTH_SHORT).show();
        }
        else if(!uniqueUsername(username)){
            assert activity != null;
            Toast.makeText(activity.getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(activity.getApplicationContext(), "An unknown account creation error occurred.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean insertPlayer(String name, String password) {
        Security sec = new Security();
        String playerHash = sec.getPassHash(name, password);
        Player newPlayer = new Player(name, playerHash);
        bus.setPlayer(newPlayer);

        try {
            File primaryPlayer = new File(bus.getMainDirectory(), "primaryPlayer");
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(primaryPlayer));
            output.writeObject(playerHash);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newPlayer.saveData();
    }

    private boolean uniqueUsername(String name){
        if (bus.hasFile(bus.getMainDirectory(), "usernameSet")){
            try{
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(bus.getFile(bus.getMainDirectory(), "usernameSet")));
                Set<String> set = (Set<String>) input.readObject();
                input.close();
                return !set.contains(name);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                File setFile = new File(bus.getMainDirectory(), "usernameSet");
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(setFile));
                Set<String> set = new HashSet<String>();
                set.add(name);
                output.writeObject(set);
                output.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
