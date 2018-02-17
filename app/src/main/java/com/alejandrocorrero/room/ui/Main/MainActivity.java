package com.alejandrocorrero.room.ui.Main;

import android.app.FragmentManager;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alejandrocorrero.room.R;
import com.alejandrocorrero.room.ui.FragmentCompanyList.ItemFragment;

public class MainActivity extends AppCompatActivity {

    private final String TAG_FRAGMENT_LIST = "TAG_FRAGMENT_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.findFragmentByTag(TAG_FRAGMENT_LIST) == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frmList, new ItemFragment(), TAG_FRAGMENT_LIST);
            transaction.commit();
        }
    }
}
