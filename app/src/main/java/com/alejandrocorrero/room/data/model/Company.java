package com.alejandrocorrero.room.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.alejandrocorrero.room.BR;

@Entity(tableName = "companies", indices = {@Index(value = {"name"}, unique = true)})
public class Company extends BaseObservable {
    @PrimaryKey @NonNull
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

    public Company() {
    }

    @Bindable
    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
        notifyPropertyChanged(BR.cIF);
    }

    @Bindable
    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
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

    @Bindable
    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    @NonNull
    public String getLogo() {
        return logo;
    }

    public void setLogo(@NonNull String logo) {
        this.logo = logo;
        notifyPropertyChanged(BR.logo);
    }

    @Bindable
    @NonNull
    public String getPerson() {
        return person;
    }

    public void setPerson(@NonNull String person) {
        this.person = person;
        notifyPropertyChanged(BR.person);
    }


}
