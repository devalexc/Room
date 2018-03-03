package com.alejandrocorrero.room.data.model;


import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.alejandrocorrero.room.BR;

public class NextVisits extends BaseObservable{
    public String name;
    public String day;
    public String nextDay;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.studentName);
    }
    @Bindable
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
        notifyPropertyChanged(BR.day);
    }
    @Bindable
    public String getNextDay() {
        return nextDay;
    }

    public void setNextDay(String nextDay) {
        this.nextDay = nextDay;
        notifyPropertyChanged(BR.nextDay);
    }
}
