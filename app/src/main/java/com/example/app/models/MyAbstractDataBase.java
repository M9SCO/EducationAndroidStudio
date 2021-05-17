package com.example.app.models;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 4,
        entities = {Account.class, Settings.class})
public abstract class MyAbstractDataBase extends RoomDatabase {
    public abstract AccountDAO accountDAO();
    public abstract SettingsDAO settingsDAO();
}