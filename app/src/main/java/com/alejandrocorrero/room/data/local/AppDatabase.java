package com.alejandrocorrero.room.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.alejandrocorrero.room.data.model.Company;

@Database(entities = (Company.class), version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CompanyDao companyDao();
}
