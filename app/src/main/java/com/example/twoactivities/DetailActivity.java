package com.example.twoactivities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.concurrent.Executor;

public class DetailActivity extends AppCompatActivity {

    private TextView dName;
    private TextView dAlamat;
    private TextView dAlmWebsite;
    private TextView dNoTelp;
    private int dImageResource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dName = findViewById(R.id.nama);
        dAlamat = findViewById(R.id.alamat);
        dAlmWebsite = findViewById(R.id.alm_website);
        dNoTelp = findViewById(R.id.no_telp);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        if (extra!=null) {
            dName.setText(extra.getString("province_name"));
            dName.setText(extra.getString("province_alamat"));
            dName.setText(extra.getString("province_alm_website"));
            dName.setText(extra.getString("province_no_telp"));

        }

    }

}

