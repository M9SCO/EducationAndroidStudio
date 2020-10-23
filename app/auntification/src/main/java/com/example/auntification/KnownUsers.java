package com.example.auntification;
import android.content.Context;
import android.database.Cursor;

public class KnownUsers {


    public static boolean get_pass(String login, String password, Context context){
        DataBase db = DataBase.toInstance(context);
        Cursor answer =  db.get("SELECT * FROM accounts WHERE login = ? and password = ?",
                new String[] {login, MyCrypt.toMd5(password)});
        return answer.moveToFirst();
    }
    public static void set_pass (String login, String password, Context context){
        DataBase db = DataBase.toInstance(context);

        db.set("INSERT INTO accounts(login, password) VALUES (?, ?)",
                new String[] {login, MyCrypt.toMd5(password)});
}}
