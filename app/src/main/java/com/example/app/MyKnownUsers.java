package com.example.app;

import android.content.Context;
import android.os.AsyncTask;

import com.example.app.models.Account;
import com.example.app.models.AccountDAO;
import com.example.app.models.DataBase;

public class MyKnownUsers {

    public static boolean get_pass(String login, String password, Context context) {
        DataBase db = MyDataBase.getInstance(context).getDatabase();
        AccountDAO accountDAO = db.accountDAO();
        Account account = accountDAO.getByAuth(login, MyCrypt.toMd5(password));
        return (account != null);
    }

    public static void set_pass(String login, String password, Context context) {
        DataBase db = MyDataBase.getInstance(context).getDatabase();
        AccountDAO accountDAO = db.accountDAO();
        Account account = new Account();
        account.login = login;
        account.password = MyCrypt.toMd5(password);
        accountDAO.insert(account);
    }


}
