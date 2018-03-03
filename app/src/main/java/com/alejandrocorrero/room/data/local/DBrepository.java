package com.alejandrocorrero.room.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.data.model.NextVisits;
import com.alejandrocorrero.room.data.model.Student;
import com.alejandrocorrero.room.data.model.Visit;

import java.util.List;

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
    public int updateCompany(Company company) {
        return db.companyDao().update(company);
    }
    public int deleteCompany(Company company) {
        return db.companyDao().delete(company);
    }
    public LiveData<List<String>> getCompaniesName() {
        return db.companyDao().getCompaniesName();
    }


    public LiveData<List<Student>> getStudents() {
        return db.studentDao().getAllStudent();
    }
    public LiveData<Student> getStudent(int studentid) {
        return db.studentDao().getStudent(studentid);
    }
    public long insertStudent(Student student) {
        return db.studentDao().insert(student);
    }
    public int updateStudent(Student student) {
        return db.studentDao().update(student);
    }
    public int deleteStudent(Student student) {
        return db.studentDao().delete(student);
    }

    public LiveData<List<Visit>> getVisits() {
        return db.visitDao().getAllVisits();
    }
    public LiveData<Visit> getVisit(int visitID) {
        return db.visitDao().getVisit(visitID);
    }
    public long insertVisit(Visit visit) {
        return db.visitDao().insert(visit);
    }
    public int updateVisit(Visit visit) {
        return db.visitDao().update(visit);
    }
    public int deleteVisit(Visit visit) {
        return db.visitDao().delete(visit);
    }
    public LiveData<String> getStudentName(int studentID){
        return db.studentDao().getStudentName(studentID);
    }

    public LiveData<List<NextVisits>> getNextVisits() {
        return db.studentDao().getNextVisists();
    }

    public AppDatabase getDb() {
        return db;
    }


}
