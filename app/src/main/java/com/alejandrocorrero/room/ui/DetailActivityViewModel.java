package com.alejandrocorrero.room.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.alejandrocorrero.room.data.local.BD;
import com.alejandrocorrero.room.data.model.Company;


public class DetailActivityViewModel extends AndroidViewModel {
    private final BD db;
    private LiveData<Company> company;

    public DetailActivityViewModel(@NonNull Application application) {
        super(application);
        db = BD.getInstance(application.getApplicationContext());

    }
    public LiveData<Company> getCompany(String CIF) {
        if (company == null) {
            company = db.getCompany(CIF);
        }
        return company;
    }
    public int updateCompany(Company company) {
        return db.updateCompany(company);
    }
}
