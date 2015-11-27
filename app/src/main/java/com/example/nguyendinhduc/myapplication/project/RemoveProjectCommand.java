package com.example.nguyendinhduc.myapplication.project;

import android.content.Context;

import com.parse.ParseObject;

/**
 * Created by nguyendinhduc on 11/25/15.
 */
public class RemoveProjectCommand implements ProjectCommand {
    //LLop concrete command dung de xoa project
    ProjectController projectController;
    ParseObject project;

    public RemoveProjectCommand(Context context, ParseObject project) {
        this.project = project;
        projectController = ProjectController.getInstance();
        projectController.setContext(context);
    }

    /**
     * Thuc hien phuong thuc xoa project cua lop PrjectController
     */
    @Override
    public void excute() {
        projectController.removeProject(project);
    }
}
