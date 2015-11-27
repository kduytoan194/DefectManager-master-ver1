package com.example.nguyendinhduc.myapplication.project;

import android.content.Context;
import android.widget.Toast;

import com.parse.DeleteCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

/**
 * Lop Receiver trong command pattern
 * Created by nguyendinhduc on 11/25/15.
 */
public class ProjectController {

    Context context;
    private static ProjectController projectController = new ProjectController();

    private ProjectController() {

    }

    public static ProjectController getInstance() {
        return projectController;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Phuong thuc them project
     * @param project project can them
     * @return
     */
    public ParseObject createProject(ParseObject project) {
        project.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(context, "Project Created", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return project;
    }

    /**
     * Phuong thuc sua project
     * @param project project can sua
     * @return
     */
    public ParseObject editProject(ParseObject project) {
        project.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(context, "Project Edited", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return project;
    }

    /**
     * Phuong thuc xoa project
     * @param project project can xoa
     */
    public void removeProject(ParseObject project) {
        project.deleteInBackground(new DeleteCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(context, "Project Removed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
