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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ProvinceListAdapter mAdapter;
    private LinkedList<Province> provinceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);

        mRecyclerView = findViewById(R.id.recyclerview);

        mAdapter = new ProvinceListAdapter(this, provinceList);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        registerCallClickBack();
    }

    public void invoke(View view) throws MalformedURLException{
        Executor exe = new Invoker();
        exe.execute(initializeData());
    }

    private Runnable initializeData() throws MalformedURLException{
        String temp = null;
        try {
            temp = NetworkUtils.getInfoProvinsi();
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView mText = findViewById(R.id.test);
        mText.setText(temp);

        return null;
    }

    private void registerCallClickBack() {
        Context mContext = this;
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("province_name", provinceList.get(position).getName());
                intent.putExtra("province_alamat", provinceList.get(position).getAlamat());
                intent.putExtra("province_alm_website", provinceList.get(position).getAlmWebsite());
                intent.putExtra("province_no_telp", provinceList.get(position).getNoTelp());
                //intent.putExtra("province_image", provinceList.get(position).getImageResource());
                startActivity(intent);
            }
        }));
    }

}

