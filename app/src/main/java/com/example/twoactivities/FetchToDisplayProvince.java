package com.example.twoactivities;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class FetchToDisplayProvince {

    private final static String LOG_TAG = FetchToDisplayProvince.class.getSimpleName();
    private static String[][] mTemp;
    private Context mContext;

    public FetchToDisplayProvince(Context context){
        this.mContext = context;
    }
    public void executeRead(String[] filename){

        String[][] temp = null;

        Log.d(LOG_TAG, "Getting the data");
        for(int i=0;i<filename.length;i++) {
            try {
                InputStream fis = mContext.getAssets().open(filename[i]);
                InputStreamReader in = new InputStreamReader(fis);

                BufferedReader bf = new BufferedReader(in);

                StringBuilder sb = new StringBuilder();
                String line;

                int c = 0;
                while ((line = bf.readLine()) != null) {
                    if (c > 0) {
                        sb.delete(0, (sb.length() - 1));
                    }
                    sb.append(line);
                    temp[c][i] = String.valueOf(sb);
                    c++;
                }
                //Bitmap bit= BitmapFactory.decodeStream(assetInStream);
                //img.setImageBitmap(bit);
            } catch (IOException e) {
                Log.d(LOG_TAG, "Data not available");
                e.printStackTrace();
            } finally {
                Log.d(LOG_TAG, "Data available");
            }
        }

        mTemp = temp;

        addImageResource();
    }

    private static TypedArray mSportsImageResources;
    private void addImageResource(){
        mSportsImageResources = mContext.getResources().obtainTypedArray(R.array.province_images);
    }

    public static LinkedList<Province> executeStore(){
        Log.d(LOG_TAG, "Printing the data");
        LinkedList<Province> mProvinceTemp = null;

        for(int i=0; i<mTemp.length; i++){
            //code goes here
            mProvinceTemp.addLast(new Province(mTemp[i][0], mTemp[i][1], mTemp[i][2], mTemp[i][3], mSportsImageResources.getResourceId(i,0)));
        }

        return mProvinceTemp;
    }

}
