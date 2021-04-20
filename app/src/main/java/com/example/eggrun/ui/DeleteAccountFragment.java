package com.example.eggrun.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import java.util.Set;

public class DeleteAccountFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "DeleteAccountFragment";
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    private final Bus bus = Bus.getInstance();

    public DeleteAccountFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        View view = inflater.inflate(R.layout.fragment_delete_account, container, false);

        mUsernameEditText = view.findViewById(R.id.username_text);
        mPasswordEditText = view.findViewById(R.id.password_text);

        Button createButton = view.findViewById(R.id.delete_account_button);
        createButton.setOnClickListener(this);
        Button cancelButton = view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(this);

        if (bus.isDarkModeActive()){
            view.setBackgroundColor(Color.BLACK);
            mUsernameEditText.setTextColor(Color.WHITE);
            mPasswordEditText.setTextColor(Color.WHITE);
            TextView text = view.findViewById(R.id.delete_account_text);
            text.setTextColor(Color.WHITE);
            text = view.findViewById(R.id.username);
            text.setTextColor(Color.WHITE);
            text = view.findViewById(R.id.password);
            text.setTextColor(Color.WHITE);
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        if (viewId == R.id.delete_account_button) {
            deleteAccount();
        } else if(viewId == R.id.cancel_button){
            Activity activity = getActivity();
            assert activity != null;
            activity.finish();
        }
        else{
            Log.d(TAG, "Error: Invalid button click");
        }
    }

    private void deleteAccount() {
        Activity activity = getActivity();
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        if (!username.equals("") && !password.equals("")) {
            if (deletePlayer(username, password)) {
                assert activity != null;
                Toast.makeText(activity.getApplicationContext(), "Account Deleted", Toast.LENGTH_SHORT).show();
                activity.finish();
            }
            else{
                assert activity != null;
                Toast.makeText(activity.getApplicationContext(), "Username or Password do not match records", Toast.LENGTH_SHORT).show();
            }
        }
        else if ((username.equals("")) || (password.equals(""))) {
            assert activity != null;
            Toast.makeText(activity.getApplicationContext(), "Missing entry", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(activity.getApplicationContext(), "An unknown account creation error occurred.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean deletePlayer(String name, String password) {
        Security sec = new Security();
        String hash = sec.getPassHash(name, password);

        if (bus.hasFile(bus.getPlayerDirectory(), hash)){
            File deleteThis = bus.getFile(bus.getPlayerDirectory(), hash);
            deleteThis.delete();

            try {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(bus.getFile(bus.getMainDirectory(), "usernameSet")));
                Set<String> nameSet = (Set<String>) input.readObject();
                nameSet.remove(name);
                input.close();

                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(bus.getFile(bus.getMainDirectory(), "usernameSet")));
                output.writeObject(nameSet);
                output.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (bus.getPlayer().getName().equals(name)){
                bus.removePlayer();
            }
            return true;
        }
        return false;
    }
}
