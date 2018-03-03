package com.alejandrocorrero.room.ui.main;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.database.sqlite.SQLiteConstraintException;
import android.support.annotation.NonNull;

import com.alejandrocorrero.room.data.local.DBrepository;
import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.data.model.NextVisits;
import com.alejandrocorrero.room.data.model.Student;
import com.alejandrocorrero.room.data.model.Visit;

import java.util.List;

import io.reactivex.exceptions.OnErrorNotImplementedException;

public class MainActivityViewModel extends AndroidViewModel {


    private LiveData<List<Company>> companies;
    private LiveData<List<Student>> students;
    private DBrepository db;
    private LiveData<List<Visit>> visits;
    private LiveData<List<NextVisits>> nextVisits;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        db = DBrepository.getInstance(application.getApplicationContext());

    }
    public LiveData<List<Visit>> getVisits() {
        if (visits == null) {
            visits = db.getVisits();
        }

        return visits;
    }
    public LiveData<List<Company>> getCompanies() {
        if (companies == null) {
            companies = db.getCompanies();
        }

        return companies;
    }
    public LiveData<List<Student>> getStudents() {
        if (students == null) {
            students = db.getStudents();
        }

        return students;
    }

    public long insertCompany(Company company) {
        try {
            return db.insertCompany(company);
        } catch (OnErrorNotImplementedException | SQLiteConstraintException e) {
            return 0;
        }

    }
    public long insertStudent(Student student) {
        try {
            return db.insertStudent(student);
        } catch (OnErrorNotImplementedException | SQLiteConstraintException e) {
            return 0;
        }

    }
    public long insertVisit(Visit visit) {
        try {
            return db.insertVisit(visit);
        } catch (OnErrorNotImplementedException | SQLiteConstraintException e) {
            return 0;
        }

    }


    public void deleteCompany(Company company) {
        db.deleteCompany(company);
    }
    public void deleteStudent(Student student) {
        db.deleteStudent(student);
    }
    public void deleteVisit(Visit visit) {
        db.deleteVisit(visit);
    }

    public LiveData<List<NextVisits>> getNextVisits(){
        if (nextVisits == null) {
            nextVisits = db.getNextVisits();
        }

        return nextVisits;
    }


}