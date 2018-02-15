package com.alejandrocorrero.room.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alejandrocorrero.room.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_detail);
        //TODO VINCULAR DATOS CREAR UPDATE Y GUARDAR DATOS AÑADIR EMPTY VIEW

    }
}
