package com.alejandrocorrero.room.ui;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.alejandrocorrero.room.data.local.BD;
import com.alejandrocorrero.room.data.model.Company;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {


    private LiveData<List<Company>> company;
    private BD db;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        db = BD.getInstance(application.getApplicationContext());
    }

    public LiveData<List<Company>> getCompanies() {
        if (company == null) {
            company = db.getCompanies();
        }
        return company;
    }


}