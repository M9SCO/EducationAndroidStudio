package com.example.app.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface AccountDAO {
    @Query("SELECT * FROM account WHERE id = :id;")
    Account getById(long id);


    @Query("Select * from account where login = :login and password =:password")
    Account getByAuth(String login, String password);

    @Insert
    void insert(Account account);

    @Update

    void update(Account account);

    @Delete
    void delete(Account account);
}
