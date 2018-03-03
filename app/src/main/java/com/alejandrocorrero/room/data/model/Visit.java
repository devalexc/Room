package com.alejandrocorrero.room.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.alejandrocorrero.room.BR;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "visits", foreignKeys = @ForeignKey(entity = Student.class, parentColumns = "studentID", childColumns = "studentID"))
public class Visit extends BaseObservable {

    public int getVisitID() {
        return visitID;
    }

    public void setVisitID(int visitID) {
        this.visitID = visitID;
    }

    @Bindable
    public String getDay() {
        return day;
    }

    public Visit() {
    }

    public void setDay(String day) {
        this.day = day;
        notifyPropertyChanged(BR.day);
    }

    @Bindable
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
        notifyPropertyChanged(BR.startTime);
    }

    @Bindable
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
        notifyPropertyChanged(BR.endTime);
    }

    @Bindable
    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
        notifyPropertyChanged(BR.observations);
    }


    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;

    }

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int visitID;
    private String day;
    private String startTime;
    private String endTime;
    private String observations;
    @NonNull
    private int studentID;
    private String studentName;

    @Bindable
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
        notifyPropertyChanged(BR.studentName);
    }

    @Ignore
    public Visit(String day, String startTime, String endTime, String observations, int studentID) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.observations = observations;
        this.studentID = studentID;
    }

}
