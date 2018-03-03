package com.alejandrocorrero.room.ui.student.detail;

import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TimePicker;

import com.alejandrocorrero.room.R;
import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.data.model.Student;
import com.alejandrocorrero.room.databinding.ActivityDetailStudentBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.github.tonnyl.light.Light;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailStudentActivity extends AppCompatActivity {

    private ActivityDetailStudentBinding mbinding;
    private DetailStudentActivityViewModel viewModel;
    private DetailStudentActivityAdapter adapter;
    private Student student = null;
    private List<Company> companies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_student);
        viewModel = ViewModelProviders.of(this).get(DetailStudentActivityViewModel.class);
        mbinding.setPresenter(this);

        setupAdapter();

        setupTimePicker();
    }

    private void setupAdapter() {
        adapter = new DetailStudentActivityAdapter(this);
        viewModel.getCompanies().observe(this, this::setValues);
        mbinding.spnCompany.setAdapter(adapter);
        mbinding.spnCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                student.setCompanyCIF(((Company) mbinding.spnCompany.getItemAtPosition(i)).getCIF());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Intent intennt = getIntent();
        if (intennt != null) {
            Bundle extras = intennt.getExtras();
            if (extras != null) {
                if (intennt.hasExtra("PrimaryKey")) {
                    int primayKey = extras.getInt("PrimaryKey");
                    viewModel.getStudent(primayKey).observe(this, this::startValues);
                }
            }
        }
        if (student == null) {
            this.student = new Student();
            mbinding.setModel(student);
            mbinding.executePendingBindings();
        }
    }

    private void setupTimePicker() {
        mbinding.lblStartSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(DetailStudentActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mbinding.lblStartSchedule.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        mbinding.lblEndSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(DetailStudentActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mbinding.lblEndSchedule.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    private void setValues(List<Company> companies) {
        adapter.setList(companies);
        this.companies = companies;
    }


    private void startValues(Student student) {
        this.student = student;

        mbinding.setModel(student);
        for (Company comp:companies){
            if (comp.getCIF().equals(student.getCompanyCIF())){

                mbinding.spnCompany.setSelection(companies.indexOf(comp));
            }
        }

        mbinding.executePendingBindings();
    }

    public void fabOnClick(View v) {
        if (student.getStudentID() > 0) {
            Single<Integer> result = Single.create(emitter -> emitter.onSuccess(viewModel.updateStudent(student)));
            result.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(this::updateStudent);
        } else {
            Single<Long> result = Single.create(emitter -> emitter.onSuccess(viewModel.insertStudent(student)));
            result.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(this::insertStudent);
        }


    }

    private void updateStudent(int resultCode) {
        if (resultCode == 1) {
            Intent result = new Intent();
            setResult(RESULT_OK, result);
            finish();
        } else {
            Light.error(mbinding.fab, getString(R.string.DetailStudent_error_updating), Snackbar.LENGTH_LONG).show();

        }
    }

    private void insertStudent(long resultCode) {
        if (resultCode == 0) {
            Light.error(mbinding.fab, getString(R.string.DetailStudent_error_inserting), Snackbar.LENGTH_LONG).show();

        } else {
            Intent result = new Intent();
            setResult(RESULT_OK, result);
            finish();

        }
    }
    @Override
    public void onBackPressed() {
        finish();
    }



}
