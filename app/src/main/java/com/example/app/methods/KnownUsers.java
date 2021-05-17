package com.example.app.methods;

import android.content.Context;

import com.example.app.models.Account;
import com.example.app.models.AccountDAO;
import com.example.app.models.MyAbstractDataBase;

public class KnownUsers {

    public static boolean get_pass(String login, String password, Context context) {
        MyAbstractDataBase db = DataBase.getInstance(context).getDatabase();
        AccountDAO accountDAO = db.accountDAO();
        Account account = accountDAO.getByAuth(login, Crypt.toMd5(password));
        return (account != null);
    }

    public static void set_pass(String login, String password, Context context) {
        MyAbstractDataBase db = DataBase.getInstance(context).getDatabase();
        AccountDAO accountDAO = db.accountDAO();
        Account account = new Account();
        account.login = login;
        account.password = Crypt.toMd5(password);
        accountDAO.insert(account);
    }


}
