package com.alejandrocorrero.room.data.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.alejandrocorrero.room.BR;


@Entity(tableName = "students", foreignKeys = @ForeignKey(entity = Company.class, parentColumns = "CIF", childColumns = "companyCIF"))
public class Student extends BaseObservable {


    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int studentID;
    @NonNull
    private String name;
    @NonNull
    private String lastName;
    private String phone;
    private String email;
    @NonNull
    private String companyCIF;
    private String nameTutor;

    private String phoneTutor;
    private String horario;


    @Ignore
    public Student(@NonNull String name, String phone, String email, @NonNull String companyCIF, String nameTutor, String phoneTutor, String horario) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.companyCIF = companyCIF;
        this.nameTutor = nameTutor;
        this.phoneTutor = phoneTutor;
        this.horario = horario;
    }

    public Student() {
    }

    @NonNull
    public int getStudentID() {
        return studentID;

    }

    public void setStudentID(@NonNull int studentID) {
        this.studentID = studentID;
    }

    @Bindable
    @NonNull
    public String getName() {
        return name;

    }

    public void setName(@NonNull String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    public String getCompanyCIF() {
        return companyCIF;
    }

    public void setCompanyCIF(@NonNull String companyCIF) {
        this.companyCIF = companyCIF;
    }

    public String getNameTutor() {
        return nameTutor;
    }

    public void setNameTutor(String nameTutor) {
        this.nameTutor = nameTutor;
    }

    public String getPhoneTutor() {
        return phoneTutor;
    }

    public void setPhoneTutor(String phoneTutor) {
        this.phoneTutor = phoneTutor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

}
