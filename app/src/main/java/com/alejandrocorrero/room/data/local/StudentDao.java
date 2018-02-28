package com.alejandrocorrero.room.data.local;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.data.model.Student;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert()
    public long insert(Student student);

    @Update
    public int update(Student student);

    @Delete
    public int delete(Student student);

    @Query("SELECT * FROM students ORDER BY name")
    public LiveData<List<Student>> getAllStudent();

    @Query("SELECT * FROM students WHERE studentID=:studentID")
    public LiveData<Student> getStudent(String studentID);

    @Query("SELECT name From students")
    public LiveData<List<String>> getByName();
}
