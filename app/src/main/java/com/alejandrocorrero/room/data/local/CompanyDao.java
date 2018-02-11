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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Company company);

    @Update
    public void update(Company company);

    @Delete
    public void delete(Company company);

    @Query("SELECT * FROM companies")
    public List<Company> loadAllCompnay(Company company);

    @Query("SELECT name From companies")
    public LiveData<List<String>> loadAllName();
}
