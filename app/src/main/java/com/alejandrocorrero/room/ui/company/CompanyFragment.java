package com.alejandrocorrero.room.ui.company;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alejandrocorrero.room.R;
import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.databinding.FragmentItemListBinding;
import com.alejandrocorrero.room.ui.company.detail.DetailActivity;
import com.alejandrocorrero.room.ui.main.MainActivityViewModel;

import java.util.List;
import io.github.tonnyl.light.Light;
import static android.app.Activity.RESULT_OK;


public class CompanyFragment extends Fragment implements CompanyFragmentAdapter.Callback {


    private MainActivityViewModel viewModel;
    private CompanyFragmentAdapter adapter;
    private FragmentItemListBinding binding;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);


    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentItemListBinding.inflate(inflater, container, false);
        binding.setPresenter(this);
        adapter = new CompanyFragmentAdapter(this);
        RecyclerView recyclerView = binding.list;
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));
        viewModel.getCompanies().observe(this, this::setupRecycleList);

        return binding.getRoot();
    }

    private void setupRecycleList(List<Company> companies) {
        adapter.setList(companies);
        binding.lblEmpty.setVisibility(companies.size() == 0 ? View.VISIBLE : View.INVISIBLE);

    }

    public void fabOnClick(View v) {
        startActivityForResult(new Intent(getActivity(), DetailActivity.class), 2);
    }


    @Override
    public void onItemClick(Company company) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("PrimaryKey", company.getCIF());
        startActivityForResult(intent, 1);

    }

    @Override
    public void onLongItemClick(Company company) {
        new Thread(() -> viewModel.deleteCompany(company)).start();
        Light.success(binding.lblEmpty, String.format(getResources().getString(R.string.fragment_item_remove), company.getName()), Snackbar.LENGTH_LONG)
                .setAction(getResources().getString(R.string.fragment_item_undo), v -> new Thread(() -> viewModel.insertCompany(company)).start()).show();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            Light.success(binding.lblEmpty, getResources().getString(R.string.ItemFragment_snackbar_update), Snackbar.LENGTH_SHORT).show();
        }
        if (resultCode == RESULT_OK && requestCode == 2) {
            Light.success(binding.lblEmpty, getResources().getString(R.string.ItemFragment_snackbar_add), Snackbar.LENGTH_SHORT).show();
        }
    }
}
