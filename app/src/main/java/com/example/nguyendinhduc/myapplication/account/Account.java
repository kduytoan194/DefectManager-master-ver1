package com.example.nguyendinhduc.myapplication.account;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import static com.example.nguyendinhduc.myapplication.Constant.USER_NAME;
import static com.example.nguyendinhduc.myapplication.Constant.USER_FULLNAME;
import static com.example.nguyendinhduc.myapplication.Constant.USER_ID;
import static com.example.nguyendinhduc.myapplication.Constant.USER_TABLE;
import static com.example.nguyendinhduc.myapplication.Constant.USER_EMAIL;
import static com.example.nguyendinhduc.myapplication.Constant.USER_ACCESS_LEVEL;
import static com.example.nguyendinhduc.myapplication.Constant.PASS;
/**
 * Created by Toan on 11/25/2015.
 */
@ParseClassName("Account")
public class Account extends ParseObject {
    public Account(){

    }

    public void setData(String userName,String fullName,String pass,String email,int accessLevel){
        put(USER_NAME,userName);
        put(USER_FULLNAME,fullName);
        put(PASS,pass);
        put(USER_EMAIL,email);
        put(USER_ACCESS_LEVEL,accessLevel);

    }
}
