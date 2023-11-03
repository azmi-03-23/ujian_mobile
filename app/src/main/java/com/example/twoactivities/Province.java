package com.example.twoactivities;

import android.graphics.drawable.Drawable;

public class Province {
    private String name;
    private String alamat;
    private String alm_website;
    private String no_telp;
    private Drawable imageResource;

    public Province(String name, String alamat, String alm_website, String no_telp, Drawable imageResource){
        this.name = name;
        this.alamat = alamat;
        this.alm_website = alm_website;
        this.no_telp = no_telp;
        this.imageResource = imageResource;
    }

    public String getName(){
        return this.name;
    }
    public String getAlamat(){
        return this.alamat;
    }
    public String getAlmWebsite(){
        return this.alm_website;
    }
    public String getNoTelp(){
        return this.no_telp;
    }

    public Drawable getImageResource(){
        return this.imageResource;
    }
}
