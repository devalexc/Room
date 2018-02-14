package com.alejandrocorrero.room.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.alejandrocorrero.room.data.model.Company;

import java.util.List;

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

    public LiveData<List<Company>> getCompanies() {
        return db.companyDao().getAllCompanies();
    }

    public LiveData<Company> getCompany(String CIF) {
        return db.companyDao().getCompany(CIF);
    }

    public long insertCompany(Company company) {
        return db.companyDao().insert(company);
    }

    public int updateCompany(Company company) {
        return db.companyDao().update(company);
    }

    public int deleteCompany(Company company) {
        return db.companyDao().delete(company);
    }

    public AppDatabase getDb() {
        return db;
    }

}
