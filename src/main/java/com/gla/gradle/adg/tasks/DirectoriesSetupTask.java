package com.gla.gradle.adg.tasks;

import com.gla.gradle.adg.ADGConfigExtension;
import com.gla.gradle.adg.Density;
import com.gla.gradle.adg.InvalidConfigurationException;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.util.Arrays;

/**
 * Created by guillaumelachaud on 1/7/14.
 *
 * This task will look for the default resources directory. Default is xhdpi.
 * If provided, it will look for the preferred resources directory.
 * It will also create missing subdirectories
 *
 */
public class DirectoriesSetupTask extends DefaultTask {

    private final static Density DEFAULT_DENSITY = Density.XHDPI;
    private final static Density DEFAULT_MIN_DENSITY = Density.MDPI;
    private final static Density[] VALID_DENSITIES = {Density.LDPI, Density.MDPI, Density.HDPI, Density.XHDPI, Density.XXHDPI, Density.XXXHDPI};
    private Density mRefDensity = null;
    private Density mMinDensity = null;

    public static final String REF_DENSITY_PROPERTY = "mRefDensity";
    public static final String MIN_DENSITY_PROPERTY = "mMinDdensity";

    @TaskAction
    public void run(){

        try{
            mRefDensity = getDensityFromConfig(((ADGConfigExtension) getProject().getExtensions().getByName("adg")).getRefDensity());
        } catch (NullPointerException e){
            //We don't have any configuration. Let's use the default resource directory
            mRefDensity = DEFAULT_DENSITY;
        }

        try{
            mMinDensity = getDensityFromConfig(((ADGConfigExtension) getProject().getExtensions().getByName("adg")).getMinDensity());
        } catch (NullPointerException e){
            //We don't have any configuration. Let's use the default resource directory
            mMinDensity = DEFAULT_MIN_DENSITY;
        }

        if(mMinDensity.compareTo(mRefDensity) > 0){
            throw new RuntimeException("[ADG] : Invalid configuration are not allowed : min Density is greater than or equal to reference density");
        }

        System.out.println("Source density to be used : " + mRefDensity);
        System.out.println("Min density to be generated : " + mMinDensity);

        getProject().getExtensions().getExtraProperties().set(REF_DENSITY_PROPERTY, mRefDensity);
        getProject().getExtensions().getExtraProperties().set(MIN_DENSITY_PROPERTY, mMinDensity);

        createMissingFolders();

    }

    private Density getDensityFromConfig(String density){

        try{

            if(density == null){
                throw new NullPointerException();
            }

            Density dst = Density.valueOf(density.toUpperCase());

            if(!Arrays.asList(VALID_DENSITIES).contains(dst)){
                throw new InvalidConfigurationException();
            }
            return dst;
        } catch (IllegalArgumentException e){
            System.out.println(e);
            throw new RuntimeException("[ADG] : Invalid configuration are not allowed");
        }

    }

    private void createMissingFolders(){

        String resDirectory = getProject().getProjectDir().getAbsolutePath() + "/src/main/res/%s";

        for(int i = mMinDensity.ordinal() ; i < mRefDensity.ordinal() ; i++){
            File resDir = new File(String.format(resDirectory, Density.values()[i].getFolder()));
            if(resDir == null || !resDir.exists()){
                resDir.mkdir();
            }
        }

    }

}
