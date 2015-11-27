package com.example.nguyendinhduc.myapplication.project;

/**
 * Lop Invoker trong command pattern
 * Created by nguyendinhduc on 11/25/15.
 */
public class ProjectAction {
    /**
     * Goi lenh can thuc thi ma khong can quan tam do la lenh gi
     * @param command Lenh can thuc thi
     */

    public void action(ProjectCommand command) {
        command.excute();
    }
}
