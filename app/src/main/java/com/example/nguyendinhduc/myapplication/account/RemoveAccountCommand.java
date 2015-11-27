package com.example.nguyendinhduc.myapplication.account;

import android.content.Context;

import com.parse.ParseObject;

/**
 * Created by Toan on 11/25/2015.
 */
public class RemoveAccountCommand implements AccountCommand{
    AccountController accountController;
    ParseObject account;

    public RemoveAccountCommand(Context context,ParseObject account){
        this.account=account;
        accountController=accountController.getInstance();
        accountController.setContext(context);
    }

    @Override
    public void excute() {
        accountController.deleteAccount(account);
    }
}
