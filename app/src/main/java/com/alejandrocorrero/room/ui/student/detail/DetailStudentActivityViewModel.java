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
    private LiveData<Student> student;
    private LiveData<List<Company>> companies;

    public DetailStudentActivityViewModel(@NonNull Application application) {
        super(application);
        db = DBrepository.getInstance(application.getApplicationContext());

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