package com.alejandrocorrero.room.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "companies", indices = {@Index(value = {"name"}, unique = true)})
public class Company {
    @Ignore
    public Company(String CIF, @NonNull String address, @NonNull String name, @NonNull String phone, @NonNull String email, @NonNull String logo, @NonNull String person) {
        this.CIF = CIF;
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.logo = logo;
        this.person = person;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getLogo() {
        return logo;
    }

    public void setLogo(@NonNull String logo) {
        this.logo = logo;
    }

    @NonNull
    public String getPerson() {
        return person;
    }

    public void setPerson(@NonNull String person) {
        this.person = person;
    }

    @PrimaryKey
    private String CIF;
    @NonNull
    private String address;
    @NonNull
    private String name;
    @NonNull
    private String phone;
    @NonNull
    private String email;
    @NonNull
    private String logo;
    @NonNull
    private String person;


}
