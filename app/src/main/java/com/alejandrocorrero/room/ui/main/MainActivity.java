package com.alejandrocorrero.room.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.alejandrocorrero.room.ui.about.AboutActivity;
import com.alejandrocorrero.room.ui.company.detail.DetailActivity;
import com.alejandrocorrero.room.ui.company.CompanyFragment;
import com.alejandrocorrero.room.ui.nextvisits.NextVisitsFragment;
import com.alejandrocorrero.room.ui.student.detail.DetailStudentActivity;
import com.alejandrocorrero.room.ui.student.FragmentStudentsList;
import com.alejandrocorrero.room.ui.visit.detail.DetailVisitActivity;
import com.alejandrocorrero.room.ui.visit.VisitFragment;
import com.alejandrocorrero.room.utils.BottomNavigatorUtils;

import java.util.Objects;

import io.github.tonnyl.light.Light;

public class MainActivity extends AppCompatActivity {

    private final String TAG_FRAGMENT_COMPANY_LIST = "TAG_FRAGMENT_COMPANY_LIST";
    private final String TAG_FRAGMENT_STUDENT_LIST = "TAG_FRAGMENT_STUDENT_LIST";
    private final String TAG_FRAGMENT_VISIT_LIST = "TAG_FRAGMENT_VISIT_LIST";
    private final String TAG_FRAGMENT_NEXT_VISITS_LIST = "TAG_FRAGMENT_NEXT_VISITS_LIST";
    private ActivityMainBinding mbinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("Mypref", 0);
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
                                transaction.replace(R.id.frmList, new CompanyFragment(), TAG_FRAGMENT_COMPANY_LIST);
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
                        case R.id.mnuVisits:
                            if (fragmentManager.findFragmentByTag(TAG_FRAGMENT_VISIT_LIST) == null) {
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.frmList, new VisitFragment(), TAG_FRAGMENT_VISIT_LIST);
                                transaction.commit();
                            }
                            break;
                        case R.id.mnuNextVisits:
                            if (fragmentManager.findFragmentByTag(TAG_FRAGMENT_NEXT_VISITS_LIST) == null) {
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.frmList, new NextVisitsFragment(), TAG_FRAGMENT_NEXT_VISITS_LIST);
                                transaction.commit();
                            }
                            break;

                    }
                    return true;
                });

        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        int defaultFrag = Integer.valueOf(preferencias.getString("prefDefaultFrag","1"));
        int frag=R.id.mnuNextVisits;
        switch (defaultFrag) {
            case 1:
                frag = R.id.mnuNextVisits;
                break;
            case 2:
                frag = R.id.mnuVisits;
                break;
            case 3:
                frag = R.id.mnuStudents;
                break;
            case 4:
                frag = R.id.mnuCompanies;
                break;
        }
        if (getSupportFragmentManager().findFragmentById(R.id.frmList) == null) {
            bottomNavigationView.findViewById(frag).performClick();
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
        } else if (Objects.equals(getSupportFragmentManager().findFragmentById(R.id.frmList).getTag(), TAG_FRAGMENT_STUDENT_LIST)) {
            startActivityForResult(new Intent(this, DetailStudentActivity.class), 3);
        } else if (Objects.equals(getSupportFragmentManager().findFragmentById(R.id.frmList).getTag(), TAG_FRAGMENT_VISIT_LIST)) {
            startActivityForResult(new Intent(this, DetailVisitActivity.class), 3);
        } else if (Objects.equals(getSupportFragmentManager().findFragmentById(R.id.frmList).getTag(), TAG_FRAGMENT_NEXT_VISITS_LIST)) {
            startActivityForResult(new Intent(this, DetailVisitActivity.class), 3);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 2) {
            Light.success(mbinding.navigation, getResources().getString(R.string.ItemFragment_snackbar_add), Snackbar.LENGTH_SHORT).show();
        } else if (resultCode == RESULT_OK && requestCode == 3) {
            Light.success(mbinding.navigation, getResources().getString(R.string.FragmentStudent_snackbar_add), Snackbar.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showPreferences() {
        startActivity(new Intent(this, PreferencesActivity.class));
    }


}
