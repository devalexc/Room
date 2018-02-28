package com.alejandrocorrero.room.ui.Main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alejandrocorrero.room.R;
import com.alejandrocorrero.room.databinding.ActivityMainBinding;
import com.alejandrocorrero.room.ui.About.AboutActivity;
import com.alejandrocorrero.room.ui.FragmentCompanyList.Detail.DetailActivity;
import com.alejandrocorrero.room.ui.FragmentCompanyList.ItemFragment;
import com.alejandrocorrero.room.ui.Student.FragmentStudentsList;
import com.alejandrocorrero.room.utils.BottomNavigatorUtils;

import java.util.Objects;

import io.github.tonnyl.light.Light;

public class MainActivity extends AppCompatActivity {

    private final String TAG_FRAGMENT_COMPANY_LIST = "TAG_FRAGMENT_COMPANY_LIST";
    private final String TAG_FRAGMENT_STUDENT_LIST = "TAG_FRAGMENT_STUDENT_LIST";
    private ActivityMainBinding mbinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        BottomNavigatorUtils.disableShiftMode(mbinding.navigation);
        mbinding.setPresenter(this);
        managerBottomSheet();

    }

    private void managerBottomSheet() {
        BottomNavigationView bottomNavigationView = mbinding.navigation;
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    switch (item.getItemId()) {
                        case R.id.mnuCompanies:
                            if (fragmentManager.findFragmentByTag(TAG_FRAGMENT_COMPANY_LIST) == null) {
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.frmList, new ItemFragment(), TAG_FRAGMENT_COMPANY_LIST);
                                transaction.commit();
                            }
                            break;
                        case R.id.mnuStudents:
                            if (fragmentManager.findFragmentByTag(TAG_FRAGMENT_STUDENT_LIST) == null) {
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.frmList, new FragmentStudentsList(), TAG_FRAGMENT_STUDENT_LIST);
                                transaction.commit();
                            }
                            break;

                    }
                    return true;
                });
        if (getSupportFragmentManager().findFragmentById(R.id.frmList) == null) {
            bottomNavigationView.findViewById(R.id.mnuCompanies).performClick();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuPreferences:
                showPreferences();
                break;
            case R.id.mnuAboutIt:
                showAbout();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void showAbout() {
        startActivity(new Intent(this, AboutActivity.class));
    }

    public void fabOnClick(View v) {
        if (Objects.equals(getSupportFragmentManager().findFragmentById(R.id.frmList).getTag(), TAG_FRAGMENT_COMPANY_LIST)) {
            startActivityForResult(new Intent(this, DetailActivity.class), 2);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 2) {
            Light.success(mbinding.navigation, getResources().getString(R.string.ItemFragment_snackbar_add), Snackbar.LENGTH_SHORT).show();
        }
    }

    private void showPreferences() {
        startActivity(new Intent(this, PreferencesActivity.class));
    }


}
