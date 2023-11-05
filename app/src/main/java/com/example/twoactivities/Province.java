package com.example.twoactivities;

public class Province {
    private final String name;
    private final String alamat;
    private final String alm_website;
    private final String no_telp;
    private final int imageResource;

    public Province(String name, String alamat, String alm_website, String no_telp, int imageResource){
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

    public int getImageResource(){
        return this.imageResource;
    }
}
