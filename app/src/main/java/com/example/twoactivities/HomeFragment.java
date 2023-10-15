package com.example.twoactivities;

import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment implements View.OnClickListener {

    public HomeFragment() {
        // Required empty public constructor
    }

    private NumberListOpenHelper mDB;

    @MainThread
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mDB = new NumberListOpenHelper(this.getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private TextView mTextView;
    private Button mButton;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;
    private Button mButton7;
    private Button mButton8;
    private Button mButton9;
    private Button mButton0;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        setViewVariables();
        setOnClickListeners();
    }

    private void setViewVariables(){
        mTextView = getView().findViewById(R.id.show_number);
        mButton = getView().findViewById(R.id.button_simpan);
        mButton1 = getView().findViewById(R.id.button1);
        mButton2 = getView().findViewById(R.id.button2);
        mButton3 = getView().findViewById(R.id.button3);
        mButton4 = getView().findViewById(R.id.button4);
        mButton5 = getView().findViewById(R.id.button5);
        mButton6 = getView().findViewById(R.id.button6);
        mButton7 = getView().findViewById(R.id.button7);
        mButton8 = getView().findViewById(R.id.button8);
        mButton9 = getView().findViewById(R.id.button9);
        mButton0 = getView().findViewById(R.id.button0);

    }

    private void setOnClickListeners(){
        mButton.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);
        mButton9.setOnClickListener(this);
        mButton0.setOnClickListener(this);
    }

    private String mTemp;
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_simpan) {
            if (mTemp != null) {
                mDB.insert(mTemp);
            } else {
                Toast.makeText(
                        getActivity().getApplicationContext(),
                        R.string.no_number,
                        Toast.LENGTH_LONG).show();
            }
            mTemp = null;

        } else if (id == R.id.button1) {
            addNumber("1");
        } else if (id == R.id.button2) {
            addNumber("2");
        } else if (id == R.id.button3) {
            addNumber("3");
        } else if (id == R.id.button4) {
            addNumber("4");
        } else if (id == R.id.button5) {
            addNumber("5");
        } else if (id == R.id.button6) {
            addNumber("6");
        } else if (id == R.id.button7) {
            addNumber("7");
        } else if (id == R.id.button8) {
            addNumber("8");
        } else if (id == R.id.button9) {
            addNumber("9");
        } else if (id == R.id.button0) {
            addNumber("0");
        }
        mTextView.setText(mTemp);
    }

    private void addNumber(String s){
        if(mTemp != null){
            mTemp = mTemp + s;
        } else {
            mTemp = s;
        }
    }
}

