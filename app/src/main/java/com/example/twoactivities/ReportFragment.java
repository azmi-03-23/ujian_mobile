package com.example.twoactivities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ReportFragment extends Fragment {

    public ReportFragment(){

    }
    private static final String LOG_TAG =
            ReportFragment.class.getSimpleName();;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false);
    }

    private TextView mTextView;
    private NumberListOpenHelper mDB;

    @MainThread
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mDB = new NumberListOpenHelper(this.getActivity());
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        mTextView = getView().findViewById(R.id.number_data);
        mTextView.setText(mDB.query());
    }
}