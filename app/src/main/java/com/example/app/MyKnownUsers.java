package com.example.app;

import android.content.Context;
import android.database.Cursor;

public class MyKnownUsers {


    public static boolean get_pass(String login, String password, Context context) {
        MyDataBase db = MyDataBase.toInstance(context);
        Cursor answer = db.get("SELECT * FROM accounts WHERE login = ? and password = ?",
                new String[]{login, MyCrypt.toMd5(password)});
        return answer.moveToFirst();
    }

    public static void set_pass(String login, String password, Context context) {
        MyDataBase db = MyDataBase.toInstance(context);

        db.set("INSERT INTO accounts(login, password) VALUES (?, ?)",
                new String[]{login, MyCrypt.toMd5(password)});
    }
}
