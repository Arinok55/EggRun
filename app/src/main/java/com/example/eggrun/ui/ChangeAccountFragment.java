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

public class ChangeAccountFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "ChangeAccountFragment";
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private final Bus bus = Bus.getInstance();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        View view = inflater.inflate(R.layout.fragment_change_account, container, false);

        mUsernameEditText = view.findViewById(R.id.username_text);
        mPasswordEditText = view.findViewById(R.id.password_text);

        Button loginButton = view.findViewById(R.id.login_account_button);
        loginButton.setOnClickListener(this);
        Button createAccountButton = view.findViewById(R.id.create_account_button);
        createAccountButton.setOnClickListener(this);
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
        }
        else if (viewId == R.id.create_account_button) {
            Intent intent = new Intent(getActivity(), NewAccountActivity.class);
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
        Security sec = new Security();
        String playerHash = sec.getPassHash(name, password);

        if (bus.hasFile(bus.getPlayerDirectory(), playerHash)){
            File playerFile = bus.getFile(bus.getPlayerDirectory(), playerHash);
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(playerFile));
            Player player = (Player) input.readObject();

            bus.setPlayer(player);
            File primaryPlayer = new File(bus.getMainDirectory(), "primaryPlayer");
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(primaryPlayer));
            output.writeObject(playerHash);
            output.close();
            return true;
        }
        return false;
    }
}
