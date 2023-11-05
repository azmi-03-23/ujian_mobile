package com.example.twoactivities;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private TextView mText;
    private RecyclerView mRecyclerView;
    private ProvinceListAdapter mAdapter;
    private LinkedList<Province> mProvinceList;
    private final String[] filename = {"province_name.txt","province_address.txt","province_web_address.txt","province_no_telp.txt"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = findViewById(R.id.test);
    }

    public void invoke(View view) throws IOException {
        Context context = this;
        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(LOG_TAG, "Getting the data");
                    FetchToDisplayProvince pdf = new FetchToDisplayProvince(context);
                    pdf.executeRead(filename);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        AssetManager asmn = getAssets();

        if (asmn.list("") != null) {
            Log.d(LOG_TAG, "Assets Available");
            mText.setText(R.string.loading);
            newThread.start();
            mProvinceList = FetchToDisplayProvince.executeStore();
            //recyclerview
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
            mAdapter = new ProvinceListAdapter(this, mProvinceList);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            registerCallClickBack();
        } else {
            mText.setText(R.string.file_not_found);
        }

    }

    private void registerCallClickBack() {
        Context mContext = this;
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("province_name", mProvinceList.get(position).getName());
                intent.putExtra("province_alamat", mProvinceList.get(position).getAlamat());
                intent.putExtra("province_alm_website", mProvinceList.get(position).getAlmWebsite());
                intent.putExtra("province_no_telp", mProvinceList.get(position).getNoTelp());
                //intent.putExtra("province_image", provinceList.get(position).getImageResource());
                startActivity(intent);
            }
        }));
    }

}

