package com.example.app.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Settings {
    @PrimaryKey
    @NonNull
    public String id;
    public String value;
}
