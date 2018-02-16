package com.alejandrocorrero.room.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.databinding.FragmentItemListBinding;


public class ItemFragment extends Fragment implements fragment_adapter.Callback {


    private MainActivityViewModel viewModel;
    private fragment_adapter adapter;

    public ItemFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);


    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentItemListBinding binding = FragmentItemListBinding.inflate(inflater, container, false);
        binding.setPresenter(this);
        adapter = new fragment_adapter(this);
        RecyclerView recyclerView = binding.list;
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));
        viewModel.getCompanies().observe(this, companies -> adapter.setList(companies));

        return binding.getRoot();
    }

    public void fabOnClick(View v){
        startActivity(new Intent(getActivity(),DetailActivity.class));
    }

    @Override
    public void onClick(Company company) {
        company.setName("Pipo3");
        new Thread(() -> viewModel.updateCompany(company));

    }
}
