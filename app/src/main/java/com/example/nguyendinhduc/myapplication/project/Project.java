package com.example.nguyendinhduc.myapplication.project;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.List;

import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_CATEGORY;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_DESCRIPTION;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_NAME;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_STATUS;
import static com.example.nguyendinhduc.myapplication.Constant.PROJECT_USER;

/**
 * Created by nguyendinhduc on 11/25/15.
 * Lop Adapter ke thua tu lop ParseObject (Adaptee)
 */

@ParseClassName("Project")
public class Project extends ParseObject {

    public Project(){

    }

    /**
     * Luu cac du lieu cua project
     *
     * @param projectName Ten cua project
     * @param projectStatus Trang thai cua project
     * @param description Mo ta project
     * @param addedCategories Mang category cua project
     * @param addedAccounts Mang cac tai khoan tham gia vao project
     */
    public void setData(String projectName, int projectStatus, String description, List<String> addedCategories,List<ParseObject> addedAccounts){
        put(PROJECT_NAME, projectName);
        put(PROJECT_STATUS, projectStatus);
        put(PROJECT_DESCRIPTION, description);
        addAllUnique(PROJECT_CATEGORY, addedCategories);
        addAllUnique(PROJECT_USER, addedAccounts);
    }
}
