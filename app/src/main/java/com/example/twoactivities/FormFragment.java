package com.example.twoactivities;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class FormFragment extends Fragment implements View.OnClickListener {
    private Button mButton;

    public FormFragment(){

    }
    private static final String LOG_TAG =
            FormFragment.class.getSimpleName();
    private EditText mUsername;
    private EditText mHobby;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        mUsername = getView().findViewById(R.id.username);
        mHobby = getView().findViewById(R.id.hobby);

        mButton = getView().findViewById(R.id.button_submit);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        if(id ==  R.id.button_submit){
            Log.d(LOG_TAG, "Button Submit clicked");

            String user_name = mUsername.getText().toString();
            String hobby = mHobby.getText().toString();

            SharedPreferences sp = this.getActivity().getSharedPreferences("USERNAME", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.putString("username_key", user_name);
            editor.putString("hobby_key", hobby);
            editor.apply();

            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(user_name);
        }
    }
}