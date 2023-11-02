package com.example.twoactivities;

import android.content.Context;
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

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);

        mTextView = findViewById(R.id.show_info_gempa);
    }

    public void invoke(View view) throws MalformedURLException {
        Executor exe = new Invoker();

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null) {
            exe.execute(showInfoGempa());
            mTextView.setText(R.string.loading);
        } else {
            mTextView.setText(R.string.bad_network_connection);
        }

    }

    private Runnable showInfoGempa() throws MalformedURLException {
        String s = NetworkUtils.getInfoGempa();
        String mDetail = null;

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject infoGempa = jsonObject.getJSONObject("Infogempa");
            JSONObject mGempa = infoGempa.getJSONObject("gempa");
            mDetail = mGempa.getString("Tanggal");
            mDetail = mDetail + "\n" + mGempa.getString("Jam");
            mDetail = mDetail + "\n" + mGempa.getString("Coordinates");
            mDetail = mDetail + "\n" + mGempa.getString("Magnitude");
            mDetail = mDetail + "\n" + mGempa.getString("Kedalaman");
            mDetail = mDetail + "\n" + mGempa.getString("Wilayah");
            mDetail = mDetail + "\n" + mGempa.getString("Potensi");
        } catch (Exception e) {
            mTextView.setText(R.string.no_results);
        }

        mTextView.setText(mDetail);
        return null;
    }

}

