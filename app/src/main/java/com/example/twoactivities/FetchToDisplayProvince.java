package com.example.twoactivities;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class FetchToDisplayProvince {

    private final static String LOG_TAG = FetchToDisplayProvince.class.getSimpleName();
    private static ArrayList<ArrayList<String>> mTemp = new ArrayList<ArrayList<String>>();
    private Context mContext;

    public FetchToDisplayProvince(Context context){
        this.mContext = context;
    }
    public static int getmTempSize(){
        if(mTemp!=null){
            return mTemp.size();
        }
        return 0;
    }

    public void readProvinceAssets(String[] filename){

        ArrayList<String> tempNama = new ArrayList<String>();
        ArrayList<String> tempAlamat = new ArrayList<String>();
        ArrayList<String> tempAlmWebsite = new ArrayList<String>();
        ArrayList<String> tempNoTelp = new ArrayList<String>();

        Log.d(LOG_TAG, "Getting the data");
        for(int i=0;i<4;i++) {
            Log.d(LOG_TAG, "Column " + Integer.toString(i));
            try {
                InputStream fis = mContext.getAssets().open(filename[i]);
                InputStreamReader in = new InputStreamReader(fis);

                BufferedReader bf = new BufferedReader(in);

                StringBuilder sb = new StringBuilder();
                String line;

                int c = 0;
                while ((line = bf.readLine()) != null) {
                    Log.d(LOG_TAG, "Row " + Integer.toString(c) + " column " + Integer.toString(i));
                    if (c > 0) {
                        Log.d(LOG_TAG, "Got to row " + Integer.toString(c) + " column " + Integer.toString(i));
                        sb.delete(0, (sb.length()));
                    }
                    sb.append(line);
                    switch(i) {
                        case 0:
                            tempNama.add(sb.toString());
                            break;
                        case 1:
                            tempAlamat.add(sb.toString());
                            break;
                        case 2:
                            tempAlmWebsite.add(sb.toString());
                            break;
                        case 3:
                            tempNoTelp.add(sb.toString());
                            break;
                    }
                    Log.d(LOG_TAG, "Value of row " + Integer.toString(c) + " column " + Integer.toString(i) + " = " + sb.toString());
                    c++;
                }
            } catch (IOException e) {
                Log.d(LOG_TAG, "Data not available");
                e.printStackTrace();
            } finally {
                Log.d(LOG_TAG, "Data available");
            }
        }

        mTemp.add(tempNama);
        mTemp.add(tempAlamat);
        mTemp.add(tempAlmWebsite);
        mTemp.add(tempNoTelp);

        addImageResource();

    }
    private void addImageResource(){
    }

    public static LinkedList<Province> createProvinceClass(Context context){
        Log.d(LOG_TAG, "Adding image resource");
        TypedArray mProvincesImageResources = context.getResources().obtainTypedArray(R.array.province_images);
        Log.d(LOG_TAG, "Printing the data");
        LinkedList<Province> mProvinceTemp = new LinkedList<>();

        for(int i=0; i<34; i++){
                mProvinceTemp.addLast(new Province(mTemp.get(0).get(i), mTemp.get(1).get(i), mTemp.get(2).get(i), mTemp.get(3).get(i), mProvincesImageResources.getResourceId(i, 0)));
        }
        mProvincesImageResources.recycle();
        return mProvinceTemp;
    }

}
