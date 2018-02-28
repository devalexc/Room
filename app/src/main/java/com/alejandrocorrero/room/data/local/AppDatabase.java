package com.alejandrocorrero.room.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.data.model.Student;

@Database(entities = {Company.class, Student.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CompanyDao companyDao();
    public abstract StudentDao studentDao();
}
