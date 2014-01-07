package com.gla.gradle.adg;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

/**
 * Created by guillaumelachaud on 1/7/14.
 */
public class DirectoriesSetupTask extends DefaultTask {

    @TaskAction
    public void run(){
        System.out.println(getExtensions().getByName("adg"));
    }

}
