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

import com.alejandrocorrero.room.BR;
import com.alejandrocorrero.room.R;
import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.databinding.FragmentCompanyListBinding;
import com.alejandrocorrero.room.ui.company.detail.DetailActivity;
import com.alejandrocorrero.room.ui.main.MainActivityViewModel;
import com.alejandrocorrero.room.utils.GenericAdapter;

import java.util.List;

import io.github.tonnyl.light.Light;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;


public class CompanyFragment extends Fragment {


    private MainActivityViewModel viewModel;
    private GenericAdapter<Company> adapter;
    private FragmentCompanyListBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);


    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCompanyListBinding.inflate(inflater, container, false);
        binding.setPresenter(this);
        adapter = new GenericAdapter<>(BR.item, R.layout.fragment_company_list_item);
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

    }

    public void fabOnClick(View v) {
        startActivityForResult(new Intent(getActivity(), DetailActivity.class), 2);
    }


    public void onItemClick(View view, Object item, int position) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("PrimaryKey", ((Company) item).getCIF());
        startActivityForResult(intent, 1);

    }

    public boolean onItemLongClick(View view, Object item, int position) {
        Company company = (Company) item;
        Single<Integer> result = Single.create(emitter -> emitter.onSuccess(viewModel.deleteCompany(company)));
        result.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(t -> deleteCompany(t, company));
        return true;

    }

    private void deleteCompany(int result, Company company) {
        if (result == 0)
            Light.error(binding.lblEmpty,getResources().getString(R.string.company_fragment_undo_error), Snackbar.LENGTH_SHORT).show();
        else
            Light.success(binding.lblEmpty, String.format(getResources().getString(R.string.fragment_item_remove), company.getName()), Snackbar.LENGTH_LONG)
                    .setAction(getResources().getString(R.string.undo), v -> new Thread(() -> viewModel.insertCompany(company)).start()).show();
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
