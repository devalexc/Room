package com.alejandrocorrero.room.ui.visit;

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
import com.alejandrocorrero.room.data.model.Visit;
import com.alejandrocorrero.room.databinding.FragmentVisitBinding;
import com.alejandrocorrero.room.ui.student.detail.DetailStudentActivity;
import com.alejandrocorrero.room.ui.visit.detail.DetailVisitActivity;
import com.alejandrocorrero.room.ui.main.MainActivityViewModel;
import com.alejandrocorrero.room.utils.GenericAdapter;

import java.util.List;

import io.github.tonnyl.light.Light;

import static android.app.Activity.RESULT_OK;


public class VisitFragment extends Fragment {
    private MainActivityViewModel viewModel;
    private GenericAdapter adapter;
    private FragmentVisitBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentVisitBinding.inflate(inflater, container, false);
        binding.setPresenter(this);
        adapter = new GenericAdapter<Visit>(BR.visit, R.layout.fragment_visit_list_item);
        RecyclerView recyclerView = binding.list;
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));
        viewModel.getVisits().observe(this, this::setupRecycleList);

        return binding.getRoot();
    }

    private void setupRecycleList(List<Visit> visits) {
        adapter.setList(visits);
        binding.lblEmpty.setVisibility(visits.size() == 0 ? View.VISIBLE : View.INVISIBLE);

    }

    public void fabOnClick(View v) {
        startActivityForResult(new Intent(getActivity(), DetailStudentActivity.class), 2);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            Light.success(binding.lblEmpty, getResources().getString(R.string.FragmentStudent_snackbar_update), Snackbar.LENGTH_SHORT).show();
        }
        if (resultCode == RESULT_OK && requestCode == 2) {
            Light.success(binding.lblEmpty, getResources().getString(R.string.FragmentStudent_snackbar_add), Snackbar.LENGTH_SHORT).show();
        }
    }

    public void onItemClick(View view, Object item, int position) {
        Intent intent = new Intent(getActivity(), DetailVisitActivity.class);
        intent.putExtra("PrimaryKey", ((Visit) item).getVisitID());
        startActivityForResult(intent, 1);
    }


    public boolean onItemLongClick(View view, Object item, int position) {
        new Thread(() -> viewModel.deleteVisit((Visit) item)).start();
        Light.success(binding.lblEmpty, String.format(getResources().getString(R.string.FragmentStudent_removed), ((Visit) item).getStudentName()), Snackbar.LENGTH_LONG)
                .setAction(getResources().getString(R.string.fragment_item_undo), v -> new Thread(() -> viewModel.insertVisit((Visit) item)).start()).show();


        return true;
    }
}
