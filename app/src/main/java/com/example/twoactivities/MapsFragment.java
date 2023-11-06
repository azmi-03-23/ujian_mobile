package com.example.twoactivities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MapsFragment extends Fragment implements View.OnClickListener {

    private String pcAddress;
    public MapsFragment(String s) {
        this.pcAddress = s;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }
    private Button mButton;

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        mButton = getView().findViewById(R.id.button_open);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.button_open)
        {
            Uri gmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(pcAddress));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(this.getActivity().getPackageManager()) != null) {
                startActivity(mapIntent);
            }
        }
    }
}