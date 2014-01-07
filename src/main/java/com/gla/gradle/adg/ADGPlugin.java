package com.gla.gradle.adg;

import com.gla.gradle.adg.tasks.DirectoriesSetupTask;
import com.gla.gradle.adg.tasks.RequirementsCheckTask;
import org.gradle.api.*;

import java.util.HashMap;

public class ADGPlugin implements Plugin<Project> {


    @Override
    public void apply(Project project) {
        project.getExtensions().create("adg", ADGConfigExtension.class);
        setupRequirementsChecksTask(project);
        setupDirectoriesSetupTask(project);
        project.getTasks().getByName("preBuild").dependsOn("directoriesSetup");
        project.getTasks().getByName("directoriesSetup").dependsOn("requirementsCheck");
    }

    private void setupRequirementsChecksTask(Project project) {

        String taskName = "requirementsCheck";
        HashMap<String, Object> args = new HashMap<String, Object>();
        args.put("type", RequirementsCheckTask.class);
        args.put("description", "This task checks that all the requirements and dependencies are fulfilled");
        project.task(args, taskName);
    }

    private void setupDirectoriesSetupTask(Project project){

        String taskName = "directoriesSetup";
        HashMap<String, Object> args = new HashMap<String, Object>();
        args.put("type", DirectoriesSetupTask.class);
        args.put("description", "This task reads config and prepares to find the right resources directories");
        project.task(args, taskName);
    }

}