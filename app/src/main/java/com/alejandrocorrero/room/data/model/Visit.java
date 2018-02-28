package com.alejandrocorrero.room.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "visits", foreignKeys = {@ForeignKey(entity = Teacher.class, parentColumns = "teacherID", childColumns = "techerID"), @ForeignKey(entity = Student.class, parentColumns = "studentID", childColumns = "studentID")})
public class Visit {
    private int visitID;

    private String day;
    private String startTime;
    private String endTime;
    private String observations;
    private int studentID;
    private int teacherID;



    public Visit(String day, String startTime, String endTime, String observations, int studentID, int teacherID) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.observations = observations;
        this.studentID = studentID;
        this.teacherID = teacherID;
    }

}
