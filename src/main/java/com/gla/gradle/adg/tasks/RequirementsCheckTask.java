package com.gla.gradle.adg.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

/**
 * Created by guillaumelachaud on 1/7/14.
 */
public class RequirementsCheckTask extends DefaultTask {

    @TaskAction
    public void run(){

        ConvertCmd cmd = new ConvertCmd();
        IMOperation op = new IMOperation();
        op.version();

        try{
            cmd.run(op);
        } catch (Exception e){
            throw new RuntimeException("[ADG] ImageMagick is missing. Please install ImageMagick and try again");
        }

    }
}
