package com.alejandrocorrero.room.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "teachers")
public class Teacher {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int techerID;
    @NonNull
    private String name;
    @NonNull
    private String lastName;
}
