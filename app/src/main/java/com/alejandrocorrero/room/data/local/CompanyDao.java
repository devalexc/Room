package com.alejandrocorrero.room.data.local;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.alejandrocorrero.room.data.model.Company;

import java.util.List;

@Dao
public interface CompanyDao {
    @Insert()
    public long insert(Company company);

    @Update
    public int update(Company company);

    @Delete
    public int delete(Company company);

    @Query("SELECT * FROM companies ORDER BY name")
    public LiveData<List<Company>> getAllCompanies();

    @Query("SELECT * FROM companies WHERE CIF=:cif")
    public LiveData<Company> getCompany(String cif);

    @Query("SELECT name From companies")
    public LiveData<List<String>> getCompaniesName();

}
