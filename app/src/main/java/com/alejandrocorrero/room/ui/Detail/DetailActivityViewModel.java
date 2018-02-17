package com.alejandrocorrero.room.ui.Detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.alejandrocorrero.room.data.local.DBrepository;
import com.alejandrocorrero.room.data.model.Company;


public class DetailActivityViewModel extends AndroidViewModel {
    private final DBrepository db;
    private LiveData<Company> company;

    public DetailActivityViewModel(@NonNull Application application) {
        super(application);
        db = DBrepository.getInstance(application.getApplicationContext());

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
    public long insertCompany(Company company) {
        return db.insertCompany(company);

    }
}
