package com.example.nguyendinhduc.myapplication.project;

import android.content.Context;

import com.parse.ParseObject;

/**
 * Created by nguyendinhduc on 11/25/15.
 */
public class CreateProjectCommand implements ProjectCommand {
    //Lop concrete command dung de them project
    ProjectController projectController;
    ParseObject project;

    public CreateProjectCommand(Context context, ParseObject project) {
        projectController = ProjectController.getInstance();
        projectController.setContext(context);
        this.project = project;
    }

    /**
     * Thuc hien phuong thuc them project cua lop ProjectController
     */
    @Override
    public void excute() {
        projectController.createProject(project);
    }
}
