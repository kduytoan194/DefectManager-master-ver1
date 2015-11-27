package com.example.nguyendinhduc.myapplication.account;

import android.content.Context;
import com.parse.ParseObject;

/**
 * Created by Toan on 11/25/2015.
 */
public class CreatAccountCommand implements  AccountCommand{
    AccountController accountController;
    ParseObject accountPO;
    public  CreatAccountCommand(Context context,ParseObject accountPO){
        accountController=AccountController.getInstance();
        accountController.setContext(context);
        this.accountPO=accountPO;

    }

    @Override
    public void excute() {
     accountController.createAccount(accountPO);
    }
}
