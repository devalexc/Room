package com.alejandrocorrero.room.ui.student;

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
import com.alejandrocorrero.room.data.model.Student;
import com.alejandrocorrero.room.databinding.FragmentStudentsListBinding;
import com.alejandrocorrero.room.ui.main.MainActivityViewModel;
import com.alejandrocorrero.room.ui.student.detail.DetailStudentActivity;
import com.alejandrocorrero.room.utils.GenericAdapter;

import java.util.List;

import io.github.tonnyl.light.Light;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

public class StudentFragment extends Fragment {
    private MainActivityViewModel viewModel;
    private GenericAdapter<Student> adapter;
    private FragmentStudentsListBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);


    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStudentsListBinding.inflate(inflater, container, false);
        binding.setPresenter(this);
        adapter = new GenericAdapter<>(BR.student, R.layout.fragment_students_list_item);
        RecyclerView recyclerView = binding.list;
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));
        viewModel.getStudents().observe(this, this::setupRecycleList);

        return binding.getRoot();
    }

    private void setupRecycleList(List<Student> students) {
        adapter.setList(students);
        binding.lblEmpty.setVisibility(students.size() == 0 ? View.VISIBLE : View.INVISIBLE);

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
        Intent intent = new Intent(getActivity(), DetailStudentActivity.class);
        intent.putExtra("PrimaryKey", ((Student) item).getStudentID());
        startActivityForResult(intent, 1);
    }


    public boolean onItemLongClick(View view, Object item, int position) {
        Student student = (Student) item;
        Single<Integer> result = Single.create(emitter -> emitter.onSuccess(viewModel.deleteStudent(student)));
        result.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(t -> deleteStudent(t, student));
        return true;

    }

    private void deleteStudent(int result, Student student) {
        if (result == 0)
            Light.error(binding.lblEmpty,getResources().getString(R.string.student_fragment_undo_error), Snackbar.LENGTH_SHORT).show();
        else
            Light.success(binding.lblEmpty, String.format(getResources().getString(R.string.FragmentStudent_removed), student.getName()), Snackbar.LENGTH_LONG)
                    .setAction(getResources().getString(R.string.undo), v -> new Thread(() -> viewModel.insertStudent(student)).start()).show();
    }
}
