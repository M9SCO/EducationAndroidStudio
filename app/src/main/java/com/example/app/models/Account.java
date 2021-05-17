package com.example.app.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Account {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String login;
    public String password;


}
