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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NewAccountFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "NewAccountFragment";
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private EditText mConfirmEditText;

    private File mGameDirectory;

    public NewAccountFragment(File directory){
        mGameDirectory = directory;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        View view = inflater.inflate(R.layout.fragment_new_account, container, false);

        mUsernameEditText = view.findViewById(R.id.username_text);
        mPasswordEditText = view.findViewById(R.id.password_text);
        mConfirmEditText = view.findViewById(R.id.confirm_text);

        Button createButton = view.findViewById(R.id.create_account_button);
        createButton.setOnClickListener(this);
        Button cancelButton = view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        if (viewId == R.id.create_account_button) {
            createAccount();
        } else if(viewId == R.id.cancel_button){
            Activity activity = getActivity();
            assert activity != null;
            activity.finish();
        }
        else{
            Log.d(TAG, "Error: Invalid button click");
        }
    }

    private void createAccount(){
        Activity activity = getActivity();
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        String confirm = mConfirmEditText.getText().toString();

        if (password.equals(confirm) && !username.equals("") && !password.equals("")) {
            Player player = new Player(username, password);
            if (insertPlayer(player)) {
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
        else{
            Toast.makeText(activity.getApplicationContext(), "An unknown account creation error occurred.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean insertPlayer(Player newPlayer){
        ObjectInputStream input;
        ObjectOutputStream output;

        File[] GameFiles = mGameDirectory.listFiles();
        for (File gameFile : GameFiles) {
            if (gameFile.isFile()) {
                try {
                    input = new ObjectInputStream(new FileInputStream(gameFile));
                    Player player = (Player) input.readObject();

                    if (player.getName().equals(newPlayer.getName())) {
                        return false;
                    }
                    input.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        try{
            makePrimaryAccount(newPlayer.getName());
            output = new ObjectOutputStream(new FileOutputStream(new File(mGameDirectory, newPlayer.getName())));
            output.writeObject(newPlayer);
            Log.d(TAG, "Writing player object");
            output.close();

            Bus bus = Bus.getInstance();
            bus.setPlayer(newPlayer);

        } catch(IOException | ClassNotFoundException e){e.printStackTrace();}
        return true;
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
        }
    }
}
