package com.alejandrocorrero.room.ui.nextvisits;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alejandrocorrero.room.BR;
import com.alejandrocorrero.room.R;
import com.alejandrocorrero.room.data.model.NextVisits;
import com.alejandrocorrero.room.databinding.FragmentVisitBinding;
import com.alejandrocorrero.room.ui.main.MainActivityViewModel;
import com.alejandrocorrero.room.utils.GenericAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class NextVisitsFragment extends Fragment {
    private MainActivityViewModel viewModel;
    private GenericAdapter<NextVisits> adapter;
    private FragmentVisitBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentVisitBinding.inflate(inflater, container, false);
        adapter = new GenericAdapter<NextVisits>(BR.nextvisits, R.layout.fragment_nextvisits_list_item);
        RecyclerView recyclerView = binding.list;
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));
        viewModel.getNextVisits().observe(this, this::setupRecycleList);

        return binding.getRoot();
    }

    private void setupRecycleList(List<NextVisits> nextVisits) {
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int numberOfDays = Integer.valueOf(preferencias.getString("prefDays","15"));

        for (NextVisits visits : nextVisits) {
            if (visits.getDay() == null)
                visits.nextDay = getString(R.string.nextVisitsfragment_first_visit);
            else {
                DateFormat format = new SimpleDateFormat("d/M/yyyy", Locale.ENGLISH);
                Date date = new Date();
                try {
                    date = format.parse(visits.getDay());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.DATE, numberOfDays);
                date = c.getTime();
                visits.nextDay = format.format(date);
            }

        }

        adapter.setList(nextVisits);
        binding.lblEmpty.setVisibility(nextVisits.size() == 0 ? View.VISIBLE : View.INVISIBLE);

    }
}
