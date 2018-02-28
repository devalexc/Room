package com.alejandrocorrero.room.ui.Main;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alejandrocorrero.room.R;

public class PreferencesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        loadSettings();
    }

    private void loadSettings() {
        getFragmentManager().beginTransaction()
                .replace(R.id.flPreferences, new PreferencesFragment())
                .commit();
    }


}

