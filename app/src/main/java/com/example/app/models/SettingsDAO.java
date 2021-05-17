package com.example.app.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface SettingsDAO {
    @Query(value = "SELECT * FROM settings WHERE id = :key limit 1;")
    Settings getByKey(String key);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Settings settings);

    @Update
    void update(Settings settings);

    @Delete
    void delete(Settings settings);
}
