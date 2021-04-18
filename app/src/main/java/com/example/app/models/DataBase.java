package com.example.app.models;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1,
        entities = {Account.class})
public abstract class DataBase extends RoomDatabase {
    public abstract AccountDAO accountDAO();
}