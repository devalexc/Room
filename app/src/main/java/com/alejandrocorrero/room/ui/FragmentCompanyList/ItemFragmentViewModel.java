package com.alejandrocorrero.room.ui.FragmentCompanyList;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.database.sqlite.SQLiteConstraintException;
import android.support.annotation.NonNull;

import com.alejandrocorrero.room.data.local.DBrepository;
import com.alejandrocorrero.room.data.model.Company;

import java.util.List;

import io.reactivex.exceptions.OnErrorNotImplementedException;

public class ItemFragmentViewModel extends AndroidViewModel {


    private LiveData<List<Company>> companies;
    private DBrepository db;

    public ItemFragmentViewModel(@NonNull Application application) {
        super(application);
        db = DBrepository.getInstance(application.getApplicationContext());

    }

    public LiveData<List<Company>> getCompanies() {
        if (companies == null) {
            companies = db.getCompanies();
        }

        return companies;
    }
    public long insertCompany(Company company) {
        try {
            return db.insertCompany(company);
        } catch (OnErrorNotImplementedException | SQLiteConstraintException e) {
            return 0;
        }

    }


    public void deleteCompany(Company company) {
        db.deleteCompany(company);
    }



}