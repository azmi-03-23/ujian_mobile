package com.example.twoactivities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {
    public static String getInfoProvinsi() throws MalformedURLException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        //download
        final String mURL;
        mURL = "https://backend.kemendagri.go.id/files/Daftar%20Alamat%20Kantor%20Prov-Kab-Kota.pdf";
        //copyURLToFile(mURL, "android.resource://com.example.twoactivities/assets/abc.pdf", 30000, 60000);
        String infoGempaJSONString;

        URL requestURL = new URL(mURL);

        try {
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            infoGempaJSONString = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return infoGempaJSONString;
    }

}
