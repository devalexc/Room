package com.alejandrocorrero.room.data.local;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.alejandrocorrero.room.data.model.Student;
import com.alejandrocorrero.room.data.model.Visit;

import java.util.List;

@Dao
public interface VisitDao {
    @Insert()
    public long insert(Visit visit);

    @Update
    public int update(Visit visit);

    @Delete
    public int delete(Visit visit);

    @Query("SELECT * FROM visits")
    public LiveData<List<Visit>> getAllVisits();

    @Query("SELECT * FROM visits WHERE visitID=:visitID")
    public LiveData<Visit> getVisit(int visitID);

}
