package com.alejandrocorrero.room.ui.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;

import com.alejandrocorrero.room.R;

import java.util.Set;

public class PreferencesFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {


    public PreferencesFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        initSummaries();
    }

    private void initSummaries() {
        for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
            initSummary(getPreferenceScreen().getPreference(i));
        }
    }

    private void initSummary(Preference preference) {
        if (preference instanceof PreferenceScreen) {
            PreferenceScreen preferenceScreen = (PreferenceScreen) preference;
            for (int i = 0; i < preferenceScreen.getPreferenceCount(); i++) {
                initSummary(preferenceScreen.getPreference(i));
            }
        } else if (preference instanceof PreferenceCategory) {
            PreferenceCategory categoria = (PreferenceCategory) preference;
            for (int i = 0; i < categoria.getPreferenceCount(); i++) {
                initSummary(categoria.getPreference(i));
            }
        } else {
            updateSummary(preference);
        }

    }


    @Override
    public void onResume() {
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

        super.onPause();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        updateSummary(findPreference(key));
    }

    private void updateSummary(Preference preference) {
        if (preference instanceof EditTextPreference) {
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            editTextPreference.setSummary(editTextPreference.getText());
        } else if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            listPreference.setSummary(listPreference.getEntry());
        } else if (preference instanceof MultiSelectListPreference) {
            MultiSelectListPreference multiSelectListPreference = (MultiSelectListPreference) preference;
            Set<String> selectedValues = multiSelectListPreference.getValues();
            multiSelectListPreference.setSummary(selectedValues.toString());
        }
    }
}
