package com.example.nguyendinhduc.myapplication.account;

/**
 * Created by Toan on 11/25/2015.
 */
public class AccountAction {
    /**
     * Goi lenh can thuc thi ma khong can quan tam do la lenh gi
     * @param command Lenh can thuc thi
     */

    public void action(AccountCommand command) {

        command.excute();
    }
}
