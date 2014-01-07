package com.gla.gradle.adg;

/**
 * Created by guillaumelachaud on 1/7/14.
 */
public enum Density {

    LDPI("ldpi"),
    MDPI("mdpi"),
    HDPI("hdpi"),
    XHDPI("xhdpi"),
    XXHDPI("xxhdpi"),
    XXXHDPI("xxxhdpi");

    private String mDensity;
    private String mFolder;

    Density(String density){
        mDensity = density;
        mFolder = String.format("drawable-%s", density);
    }

    public String getDensity(){
        return mDensity;
    }

    public String getFolder(){
        return mFolder;
    }



}
