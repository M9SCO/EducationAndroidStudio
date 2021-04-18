package com.example.app;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.room.Room;

import com.example.app.models.DataBase;

public class MyDataBase {

    private static MyDataBase instance;

    private static DataBase database;

    public static void onCreate(Context context) {
        database = Room.databaseBuilder(context, DataBase.class, "app").allowMainThreadQueries()
                .build();
    }

    public static MyDataBase getInstance(Context context) {
        if (instance==null){
            onCreate(context);
            instance = new MyDataBase();
        }
        return instance;
    }



    public DataBase getDatabase() {
        return database;
    }
}
