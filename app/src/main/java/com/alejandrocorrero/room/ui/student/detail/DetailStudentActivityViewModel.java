package com.alejandrocorrero.room.ui.student.detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.database.sqlite.SQLiteConstraintException;
import android.support.annotation.NonNull;

import com.alejandrocorrero.room.data.local.DBrepository;
import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.data.model.Student;

import java.util.List;

class DetailStudentActivityViewModel extends AndroidViewModel {

    private final DBrepository db;
    private LiveData<List<String>> companyName;
    private LiveData<Student> student;
    private LiveData<List<Company>> companies;
    private LiveData<Company> company;

    public DetailStudentActivityViewModel(@NonNull Application application) {
        super(application);
        db = DBrepository.getInstance(application.getApplicationContext());

    }

    LiveData<List<String>> getCompaniesName() {
        if (companyName == null) {
            companyName = db.getCompaniesName();
        }
        return companyName;
    }
    public LiveData<List<Company>> getCompanies() {
        if (companies == null) {
            companies = db.getCompanies();
        }

        return companies;
    }

    LiveData<Student> getStudent(int studentId) {
        if (student == null) {
            student = db.getStudent(studentId);
        }
        return student;
    }
    LiveData<Company> getCompany(String CIF) {
        if (company == null) {
            company = db.getCompany(CIF);
        }
        return company;
    }

    int updateStudent(Student student) {
        return db.updateStudent(student);
    }

    long insertStudent(Student student) {
        try {
            return db.insertStudent(student);
        } catch (SQLiteConstraintException e) {
            return 0;
        }

    }

}