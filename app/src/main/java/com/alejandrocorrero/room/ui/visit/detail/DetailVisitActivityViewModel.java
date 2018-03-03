package com.alejandrocorrero.room.ui.visit.detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.alejandrocorrero.room.data.local.DBrepository;
import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.data.model.Student;
import com.alejandrocorrero.room.data.model.Visit;

import java.util.List;

class DetailVisitActivityViewModel extends AndroidViewModel {

    private final DBrepository db;
    private LiveData<List<String>> companyName;
    private LiveData<Student> student;
    private LiveData<Visit> visit;
    private LiveData<List<Student>> students;
    private LiveData<Company> company;

    public DetailVisitActivityViewModel(@NonNull Application application) {
        super(application);
        db = DBrepository.getInstance(application.getApplicationContext());

    }

    LiveData<List<String>> getCompaniesName() {
        if (companyName == null) {
            companyName = db.getCompaniesName();
        }
        return companyName;
    }
    public LiveData<List<Student>> getStudents() {
        if (students == null) {
            students = db.getStudents();
        }

        return students;
    }

    LiveData<Visit> getVisit(int visitID) {
        if (visit == null) {
            visit = db.getVisit(visitID);
        }
        return visit;
    }
    LiveData<Company> getCompany(String CIF) {
        if (company == null) {
            company = db.getCompany(CIF);
        }
        return company;
    }
    LiveData<String> getStudentName(int studentID) {
        return db.getStudentName(studentID);
    }

    int updateVisit(Visit visit) {
        return db.updateVisit(visit);
    }

    long insertVisit(Visit visit) {

            return db.insertVisit(visit);


    }

}