package com.alejandrocorrero.room.ui.Detail;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alejandrocorrero.room.R;
import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.databinding.ActivityDetailBinding;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailActivity extends AppCompatActivity {


    private DetailActivityViewModel viewModel;
    private ActivityDetailBinding mbinding;
    private Company company = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        viewModel = ViewModelProviders.of(this).get(DetailActivityViewModel.class);
        Intent intennt = getIntent();
        if (intennt != null) {
            Bundle extras = intennt.getExtras();
            if (extras != null) {
                if (intennt.hasExtra("PrimaryKey")) {
                    String primayKey = extras.getString("PrimaryKey");
                    viewModel.getCompany(primayKey).observe(this, this::startValues);
                    mbinding.edtCIF.setEnabled(false);
                }
            }
        }
        mbinding.setPresenter(this);
        if (company == null) {
            this.company = new Company();
            mbinding.setModel(company);
            mbinding.executePendingBindings();
        }
        //TODO VINCULAR DATOS CREAR UPDATE Y GUARDAR DATOS AÑADIR EMPTY VIEW Y ELIMINAR CON LONG CLICK

    }

    private void startValues(Company company) {
        this.company = company;
        mbinding.setModel(company);
        mbinding.executePendingBindings();
    }

    public void fabOnClick(View v) {
        if (!mbinding.edtCIF.isEnabled()) {
            Single<Integer> result = Single.create(emitter -> emitter.onSuccess(viewModel.updateCompany(company)));
            result.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(this::updateCompany);
        } else {
            Single<Long> result = Single.create(emitter -> emitter.onSuccess(viewModel.insertCompany(company)));
            result.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(this::insertCompany);
        }


    }
    private void updateCompany(int resultCode){
        if(resultCode==1){
            Intent result = new Intent();
            setResult(RESULT_OK, result);
            finish();
        }
        else{
            Snackbar.make(mbinding.fab, "Error updating the company check all values", Snackbar.LENGTH_LONG).show();

        }
    }private void insertCompany(long resultCode){
        if(resultCode==1){
            Intent result = new Intent();
            setResult(RESULT_OK, result);
            finish();
        }
        else{
            Snackbar.make(mbinding.fab, "Error inserting the company check all values", Snackbar.LENGTH_LONG).show();

        }
    }
}
