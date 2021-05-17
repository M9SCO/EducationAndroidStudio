package com.example.app.methods;

import android.content.Context;

import androidx.room.Room;

import com.example.app.models.MyAbstractDataBase;

public class DataBase {

    private static DataBase instance;

    private static MyAbstractDataBase database;

    public static void onCreate(Context context) {
        database = Room.databaseBuilder(context, MyAbstractDataBase.class, "app").allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();
    }

    public static DataBase getInstance(Context context) {
        if (instance==null){
            onCreate(context);
            instance = new DataBase();
        }
        return instance;
    }



    public MyAbstractDataBase getDatabase() {
        return database;
    }
}
