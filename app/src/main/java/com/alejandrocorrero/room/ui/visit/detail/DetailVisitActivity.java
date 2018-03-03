package com.alejandrocorrero.room.ui.visit.detail;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.alejandrocorrero.room.R;
import com.alejandrocorrero.room.data.model.Student;
import com.alejandrocorrero.room.data.model.Visit;
import com.alejandrocorrero.room.databinding.ActivityDetailVisitBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.github.tonnyl.light.Light;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailVisitActivity extends AppCompatActivity {

    private ActivityDetailVisitBinding mbinding;
    private DetailVisitActivityAdapter adapter;
    private DetailVisitActivityViewModel viewModel;
    private Visit visit=null;
    private List<Student> students= new  ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_visit);
        mbinding.setPresenter(this);
        viewModel = ViewModelProviders.of(this).get(DetailVisitActivityViewModel.class);
        mbinding.startTimeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(DetailVisitActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mbinding.startTimeSelect.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        mbinding.daySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDay = Calendar.getInstance();
                int day = mcurrentDay.get(Calendar.DAY_OF_MONTH);
                int month = mcurrentDay.get(Calendar.MONTH);
                int year = mcurrentDay.get(Calendar.YEAR);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(DetailVisitActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        mbinding.daySelect.setText(i2 + "/" + (i1+1) + "/" + i);
                    }
                }, year, month, day);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        mbinding.endTimeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(DetailVisitActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mbinding.endTimeSelect.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        setupAdapter();
    }


    private void setupAdapter() {
        adapter = new DetailVisitActivityAdapter(this);
        viewModel.getStudents().observe(this, this::setValues);
        mbinding.spnStundet.setAdapter(adapter);
        mbinding.spnStundet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                visit.setStudentID(((Student) mbinding.spnStundet.getItemAtPosition(i)).getStudentID());

                viewModel.getStudentName(visit.getStudentID()).observe(DetailVisitActivity.this,s -> visit.setStudentName(s));


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
                    viewModel.getVisit(primayKey).observe(this, this::startValues);
                }
            }
        }
        if (visit == null) {
            this.visit = new Visit();
            mbinding.setModel(visit);
            mbinding.executePendingBindings();
        }
    }

    private void startValues(Visit visit) {
        this.visit = visit;

        mbinding.setModel(visit);
        for (Student std:students){
            if (std.getStudentID()==visit.getStudentID()){

                mbinding.spnStundet.setSelection(students.indexOf(std));
            }
        }

        mbinding.executePendingBindings();
    }

    private void setValues(List<Student> students) {
        adapter.setList(students);
        this.students = students;

    }public void fabOnClick(View v) {
        if (visit.getVisitID() > 0) {
            Single<Integer> result = Single.create(emitter -> emitter.onSuccess(viewModel.updateVisit(visit)));
            result.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(this::updateVisit);
        } else {
            Single<Long> result = Single.create(emitter -> emitter.onSuccess(viewModel.insertVisit(visit)));
            result.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(this::insertVisit);
        }


    }
    private void updateVisit(int resultCode) {
        if (resultCode == 1) {
            Intent result = new Intent();
            setResult(RESULT_OK, result);
            finish();
        } else {
            Light.error(mbinding.fab, getString(R.string.DetailStudent_error_updating), Snackbar.LENGTH_LONG).show();

        }
    }

    private void insertVisit(long resultCode) {
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
