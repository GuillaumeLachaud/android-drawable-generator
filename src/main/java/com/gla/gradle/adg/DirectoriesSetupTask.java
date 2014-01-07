package com.gla.gradle.adg;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

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

    private final static String DRAWABLE_RES_PREFIX = "drawable-%s";
    private final static String DEFAULT_DENSITY = "xhdpi";
    private final static String[] VALID_DENSITIES = {"ldpi", "mdpi", "hdpi", "xhdpi", "xxhdpi", "xxxhdpi"};

    @TaskAction
    public void run(){

        String preferred = null;
        String sourcePath = null;

        try{
            preferred = ((ADGConfigExtension) getProject().getExtensions().getByName("adg")).getSource();
            if(preferred == null){
                throw new NullPointerException();
            }
            if(!Arrays.asList(VALID_DENSITIES).contains(preferred)){
                throw new InvalidConfigurationException();
            }
            sourcePath = String.format(DRAWABLE_RES_PREFIX, preferred);
        } catch (NullPointerException e){
            //We don't have any configuration. Let's use the default resource directory
            sourcePath = String.format(DRAWABLE_RES_PREFIX, DEFAULT_DENSITY);
        } catch (InvalidConfigurationException e){
            throw new RuntimeException("[ADG] : Invalid configuration are not allowed");
        }



        System.out.println("Source density to be used : " + sourcePath);

        ConvertCmd cmd = new ConvertCmd();
        IMOperation op = new IMOperation();
        op.version();

        try{
            cmd.run(op);
        } catch (Exception e){
        }

    }

}
