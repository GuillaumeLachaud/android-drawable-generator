package com.gla.gradle.adg;

import org.gradle.api.*;

import java.util.HashMap;

public class ADGPlugin implements Plugin<Project> {


    @Override
    public void apply(Project project) {
        project.getTasks().getByName("clean").dependsOn("directoriesSetup");
        setupDirectoriesSetupTask(project);

    }

    private void setupDirectoriesSetupTask(Project project){

        String taskName = "directoriesSetup";
        HashMap<String, Object> args = new HashMap<String, Object>();
        args.put("type", DirectoriesSetupTask.class);
        args.put("description", "This task reads config and prepares to find the right resources directories");
        project.task(args, taskName);

    }

}