package com.example.nguyendinhduc.myapplication.account;

import android.content.Context;
import android.widget.Toast;

import com.example.nguyendinhduc.myapplication.project.ProjectController;
import com.parse.DeleteCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

/**
 * Created by Toan on 11/25/2015.
 */
public class AccountController {
    Context context;
    private static AccountController accountController=new AccountController();
    private AccountController(){

    }
    public static AccountController getInstance(){
        return accountController;
    }
    public void setContext(Context context){
        this.context=context;
    }


    public ParseObject createAccount(ParseObject account){
        account.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    Toast.makeText(context,"Account Created",Toast.LENGTH_LONG).show();
                }
            }
        });
        return account;
    }
    public ParseObject editAccount(ParseObject account){
        account.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    Toast.makeText(context,"Account editted",Toast.LENGTH_LONG).show();
                }
            }
        });
        return account;
    }
    public ParseObject deleteAccount(ParseObject account){
        account.deleteInBackground(new DeleteCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    Toast.makeText(context,"Account Deleted",Toast.LENGTH_LONG).show();
                }
            }
        });
        return account;
    }
}
