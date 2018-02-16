package com.alejandrocorrero.room.ui;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.alejandrocorrero.room.data.local.BD;
import com.alejandrocorrero.room.data.model.Company;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {


    private LiveData<List<Company>> companies;
    private LiveData<Company> company;
    private BD db;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        db = BD.getInstance(application.getApplicationContext());
        //new Thread(() -> db.insertCompany(new Company("777777", "test", "test2", "test", "dede@h", "https://developer.android.com/studio/images/hero_image_studio.png", "paco"))).start();

    }

    public LiveData<List<Company>> getCompanies() {
        if (companies == null) {
            companies = db.getCompanies();
        }

        return companies;
    }

    public LiveData<Company> getCompany(String CIF) {
        if (company == null) {
            company = db.getCompany(CIF);
        }
        return company;
    }

    public void insertCompany(Company company) {
        db.insertCompany(company);

    }

    public void deleteCompany(Company company) {
        db.deleteCompany(company);
    }

    public int updateCompany(Company company) {
        return db.updateCompany(company);
    }

}