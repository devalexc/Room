package com.alejandrocorrero.room.data.local;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

public class BD {

    private static BD ourInstance;
    private AppDatabase db;

    public static synchronized BD getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new BD(context.getApplicationContext());
        }
        return ourInstance;
    }

    private BD(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "tfg.db")
                .allowMainThreadQueries()

                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {

                    }
                }).build();
    }



    public AppDatabase getDb() {
        return db;
    }

}
