package com.example.eggrun.ui;

import android.app.Activity;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ChangeAccountFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "ChangeAccountFragment";
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    private final File mGameDirectory;
    private final Bus bus = Bus.getInstance();

    public ChangeAccountFragment(File directory){
        mGameDirectory = directory;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        View view = inflater.inflate(R.layout.fragment_change_account, container, false);

        mUsernameEditText = view.findViewById(R.id.username_text);
        mPasswordEditText = view.findViewById(R.id.password_text);

        Button createButton = view.findViewById(R.id.login_account_button);
        createButton.setOnClickListener(this);
        Button cancelButton = view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        if (viewId == R.id.login_account_button) {
            try {
                loginAccount();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if(viewId == R.id.cancel_button){
            Activity activity = getActivity();
            activity.finish();
        }
        else{
            Log.d(TAG, "Error: Invalid button click");
        }
    }

    private void loginAccount() throws IOException, ClassNotFoundException {
        Activity activity = getActivity();
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        if (!username.equals("") && !password.equals("")) {
            if (loginPlayer(username, password)) {
                assert activity != null;
                Toast.makeText(activity.getApplicationContext(), "Account Changed to " + bus.getPlayer().getName(), Toast.LENGTH_SHORT).show();
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

    private boolean loginPlayer(String name, String password) throws IOException, ClassNotFoundException {
        ObjectInputStream input;
        File[] GameFiles = mGameDirectory.listFiles();
        for (File gameFile : GameFiles) {
            input = new ObjectInputStream(new FileInputStream(gameFile));
            Player player = (Player) input.readObject();
            if (player.getName().equals(name) && player.getPassword().equals(password)){
                player.setPrimary();
                Bus bus = Bus.getInstance();
                bus.setPlayer(player);
                makePrimaryAccount(name);
                input.close();
                return true;
            }
            input.close();
        }
        return false;
    }

    private void makePrimaryAccount(String name) throws IOException, ClassNotFoundException {
        ObjectInputStream input;
        File[] GameFiles = mGameDirectory.listFiles();
        for (File gameFile : GameFiles) {
            input = new ObjectInputStream(new FileInputStream(gameFile));
            Player player = (Player) input.readObject();
            if (!player.getName().equals(name)){
                player.removePrimary();
            }
            input.close();
        }
    }
}
