package com.gla.gradle.adg;

/**
 * Created by guillaumelachaud on 1/7/14.
 */
public enum Density {

    LDPI("ldpi", 120),
    MDPI("mdpi", 160),
    HDPI("hdpi", 240),
    XHDPI("xhdpi", 320),
    XXHDPI("xxhdpi", 480),
    XXXHDPI("xxxhdpi", 640);

    private String mDensity;
    private String mFolder;
    private int mValue;

    Density(String density, int value){
        mDensity = density;
        mFolder = String.format("drawable-%s/", density);
        mValue = value;
    }

    public String getDensity(){
        return mDensity;
    }

    public String getFolder(){
        return mFolder;
    }

    public int getValue(){
        return mValue;
    }



}
