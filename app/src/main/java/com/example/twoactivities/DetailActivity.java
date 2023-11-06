package com.example.twoactivities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.time.Instant;
import java.util.concurrent.Executor;

public class DetailActivity extends AppCompatActivity {

    private TextView dName;
    private TextView dAlamat;
    private TextView dAlmWebsite;
    private TextView dNoTelp;
    private ImageView dImageResource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dName = findViewById(R.id.nama);
        dAlamat = findViewById(R.id.alamat);
        dAlmWebsite = findViewById(R.id.alm_website);
        dNoTelp = findViewById(R.id.no_telp);
        dImageResource = findViewById(R.id.image);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        if (extra!=null) {
            dName.setText(extra.getString("province_name"));
            dAlamat.setText(extra.getString("province_alamat"));
            dAlmWebsite.setText(extra.getString("province_alm_website"));
            dNoTelp.setText(extra.getString("province_no_telp"));
            dImageResource.setImageResource(extra.getInt("province_image"));

            MapsFragment mapsFragment = new MapsFragment(extra.getString("province_alamat"));
            getSupportFragmentManager().beginTransaction().add(R.id.gmaps, mapsFragment).commit();
        }
        else {
            dName.setText(R.string.file_not_found);
        }

    }

}

