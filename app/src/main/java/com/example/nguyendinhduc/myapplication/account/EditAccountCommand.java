package com.example.nguyendinhduc.myapplication.account;

import android.content.Context;

import com.example.nguyendinhduc.myapplication.project.ProjectController;
import com.parse.ParseObject;

/**
 * Created by Toan on 11/25/2015.
 */
public class EditAccountCommand implements AccountCommand {
    AccountController accountController;
    ParseObject object;
    public EditAccountCommand(Context context,ParseObject object){
        accountController=AccountController.getInstance();
        accountController.setContext(context);
        this.object=object;
    }

    @Override
    public void excute() {
        accountController.editAccount(object);
    }
}
