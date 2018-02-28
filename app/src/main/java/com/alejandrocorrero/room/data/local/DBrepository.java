package com.alejandrocorrero.room.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.data.model.Student;

import java.util.List;

import io.reactivex.exceptions.OnErrorNotImplementedException;

public class DBrepository {

    private static DBrepository ourInstance;
    private AppDatabase db;

    public static synchronized DBrepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new DBrepository(context.getApplicationContext());
        }
        return ourInstance;
    }

    private DBrepository(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "tfg.db")
                .fallbackToDestructiveMigration()
                .build();
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
    public long insertStudent(Student student) {
        return db.studentDao().insert(student);
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

    public LiveData<List<Student>> getStudents() {
        return db.studentDao().getAllStudent();
    }
    public int deleteStudent(Student student) {
        return db.studentDao().delete(student);
    }

}
