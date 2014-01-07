package com.gla.gradle.adg;

/**
 * Created by guillaumelachaud on 1/7/14.
 */
public class ADGConfigExtension {

    private String mSource;

    public ADGConfigExtension(){
    }

    public ADGConfigExtension(String source){
        mSource = source;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String mSource) {
        this.mSource = mSource;
    }
}
