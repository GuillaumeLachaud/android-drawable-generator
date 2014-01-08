package com.gla.gradle.adg;

/**
 * Created by guillaumelachaud on 1/7/14.
 */
public class ADGConfigExtension {

    private String mRefDensity;
    private String mMinDensity;
    private boolean mForceOverwrite = false;

    public ADGConfigExtension(){
    }

    public ADGConfigExtension(String refDensity, String minDensity){
        mRefDensity = refDensity;
        mMinDensity = minDensity;
    }

    public String getRefDensity() {
        return mRefDensity;
    }

    public void setRefDensity(String refDensity) {
        this.mRefDensity = refDensity;
    }

    public String getMinDensity() {
        return mMinDensity;
    }

    public void setMinDensity(String mMinDensity) {
        this.mMinDensity = mMinDensity;
    }

    public boolean isForceOverwrite() {
        return mForceOverwrite;
    }

    public void setForceOverwrite(boolean mForceOverwrite) {
        this.mForceOverwrite = mForceOverwrite;
    }
}
