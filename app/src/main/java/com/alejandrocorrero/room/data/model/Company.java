package com.alejandrocorrero.room.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
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

@Entity(tableName = "companies", indices = {@Index(value = {"name"})})
public class Company extends BaseObservable {
    @PrimaryKey
    @NonNull
    private String CIF;
    private String address;
    @NonNull
    private String name;
    private String phone;
    private String email;
    private String logo;
    private String person;


    @Ignore
    public Company(@NonNull String CIF, String address, @NonNull String name, String phone, String email, String logo, String person) {
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

    @NonNull
    @Bindable
    public String getCIF() {
        return CIF;
    }

    public void setCIF(@NonNull String CIF) {
        this.CIF = CIF;
        notifyPropertyChanged(BR.cIF);
    }

    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
        notifyPropertyChanged(BR.logo);
    }

    @Bindable
    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
        notifyPropertyChanged(BR.person);
    }

    @Ignore
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.with(view.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.no_image_available)
                    .error(R.drawable.no_image_available)
                    .into(view);
        } else {
            Picasso.with(view.getContext())
                    .load(R.drawable.no_image_available)
                    .placeholder(R.drawable.no_image_available)
                    .error(R.drawable.no_image_available)
                    .into(view);
        }
    }

    @Ignore
    @Bindable({"name", "CIF"})
    public int getEnable() {
        if ((TextUtils.isEmpty(name) || !(Utils.isCifNumValid(CIF)))) {
            return View.INVISIBLE;
        } else {
            return View.VISIBLE;
        }


    }

    @Ignore
    @BindingAdapter("app:errorText")
    public static void setErrorMessage(TextInputLayout view, String errorMessage) {
        view.setError(errorMessage);
    }

    @Ignore
    @Bindable
    public String getErrorCif() {
        if (isCifError()) {
            return "Incorrect CIF";
        } else {
            return null;
        }
    }

    @Ignore
    @Bindable({"CIF"})
    public boolean isCifError() {
        if (!Utils.isCifNumValid(CIF)) {
            notifyPropertyChanged(BR.errorCif);
        }
        return !Utils.isCifNumValid(CIF);

    }

    @Ignore
    @Bindable
    public String getErrorName() {
        if (isNameError()) {
            return "Cant be empty";
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
    public String getErrorPhone() {
        if (isPhoneError()) {
            return "Incorrect Phone";
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
    public String getErrorEmail() {
        if (isEmailError()) {
            return "Incorrect Email";
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

    @Ignore
    @Bindable
    public String getErrorLogo() {
        if (isLogoError()) {
            return "Incorrect Logo";
        } else {
            return null;
        }
    }

    @Ignore
    @Bindable({"logo"})
    public boolean isLogoError() {
        if (!URLUtil.isValidUrl(logo)) {
            notifyPropertyChanged(BR.errorLogo);
        }
        return !URLUtil.isValidUrl(logo);

    }

}
