package com.example.nguyendinhduc.myapplication.project;

import android.content.Context;

import com.parse.ParseObject;

/**
 * Created by nguyendinhduc on 11/25/15.
 */
public class EditProjectCommand implements ProjectCommand {
    //Lop concrete command dung de sua project
    ProjectController projectController;
    ParseObject project;

    public EditProjectCommand(Context context, ParseObject project) {
        projectController = ProjectController.getInstance();
        projectController.setContext(context);
        this.project = project;
    }

    /**
     * Thuc hien phuong thuc sua project cua lop ProjectController
     */
    @Override
    public void excute() {
        projectController.editProject(project);
    }
}
