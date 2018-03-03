package com.alejandrocorrero.room.data.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageView;

import com.alejandrocorrero.room.BR;
import com.alejandrocorrero.room.R;
import com.alejandrocorrero.room.utils.Utils;
import com.squareup.picasso.Picasso;


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
    private String companyCIF;
    private String nameTutor;
    private String phoneTutor;
    private String startHour;
    private String endHour;


    @Ignore
    public Student(@NonNull String name, String phone, String email, String companyCIF, String nameTutor, String phoneTutor, String startHour,String endHour) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.companyCIF = companyCIF;
        this.nameTutor = nameTutor;
        this.phoneTutor = phoneTutor;
        this.startHour = startHour;
        this.endHour = endHour;
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
    @Bindable
    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);

    }
    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }
    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }
    @Bindable
    public String getCompanyCIF() {
        return companyCIF;
    }

    public void setCompanyCIF(String companyCIF) {
        this.companyCIF = companyCIF;
        notifyPropertyChanged(BR.companyCIF);
    }
    @Bindable
    public String getNameTutor() {
        return nameTutor;
    }

    public void setNameTutor(String nameTutor) {
        this.nameTutor = nameTutor;
        notifyPropertyChanged(BR.nameTutor);
    }
    @Bindable
    public String getPhoneTutor() {
        return phoneTutor;

    }

    public void setPhoneTutor(String phoneTutor) {
        this.phoneTutor = phoneTutor;
        notifyPropertyChanged(BR.phoneTutor);
    }

    @Ignore
    @Bindable({"name"})
    public int getEnable() {
        if ((TextUtils.isEmpty(name))) {
            return View.INVISIBLE;
        } else {
            return View.VISIBLE;
        }


    }
    @Bindable
    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
        notifyPropertyChanged(BR.startHour);
    }
    @Bindable
    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
        notifyPropertyChanged(BR.endHour);
    }
    @Ignore
    @BindingAdapter("app:errorText")
    public static void setErrorMessage(TextInputLayout view, Integer errorMessage) {
        if (errorMessage != null) {
            view.setError(view.getResources().getString(errorMessage));
        } else {
            view.setError(null);
        }

    }

    @Ignore
    @Bindable
    public Integer getErrorName() {
        if (isNameError()) {
            return R.string.company_errorName;
        } else {
            return null;
        }
    }

    @Ignore
    @Bindable({"name"})
    public boolean isNameError() {
        if (TextUtils.isEmpty(name)) {
            notifyPropertyChanged(BR.errorName);
        }
        return TextUtils.isEmpty(name);

    }

    @Ignore
    @Bindable
    public Integer getErrorPhone() {
        if (isPhoneError()) {
            return R.string.company_error_phone;
        } else {
            return null;
        }
    }

    @Ignore
    @Bindable({"phone"})
    public boolean isPhoneError() {
        if (phone == null) {
            return true;
        }
        if (phone.length() < 9 || phone.length() > 9) {
            notifyPropertyChanged(BR.errorPhone);
        }
        return (phone.length() < 9 || phone.length() > 9);

    }

    @Ignore
    @Bindable
    public Integer getErrorPhoneTutor() {
        if (isPhoneError()) {
            return R.string.company_error_phone;
        } else {
            return null;
        }
    }

    @Ignore
    @Bindable({"phoneTutor"})
    public boolean isPhoneTutorError() {
        if (phoneTutor == null) {
            return true;
        }
        if (phoneTutor.length() < 9 || phoneTutor.length() > 9) {
            notifyPropertyChanged(BR.errorPhoneTutor);
        }
        return (phoneTutor.length() < 9 || phoneTutor.length() > 9);

    }

    @Ignore
    @Bindable
    public Integer getErrorEmail() {
        if (isEmailError()) {
            return R.string.company_error_email;
        } else {
            return null;
        }
    }

    @Ignore
    @Bindable({"email"})
    public boolean isEmailError() {
        if (!(!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            notifyPropertyChanged(BR.errorEmail);
        }
        return !(!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());

    }



}
