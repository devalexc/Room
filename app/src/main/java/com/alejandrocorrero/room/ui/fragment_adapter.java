package com.alejandrocorrero.room.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.databinding.FragmentItemBinding;


public class fragment_adapter extends RecyclerView.Adapter<fragment_adapter.ViewHolder> {

    private final int mBRid;

    private MainActivityViewModel viewModel;

    public fragment_adapter(int mBRid, FragmentActivity activity) {
        viewModel = ViewModelProviders.of(activity).get(MainActivityViewModel.class);

        this.mBRid = mBRid;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentItemBinding binding = FragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(fragment_adapter.ViewHolder holder, int position) {
        holder.bind(viewModel.getCompanies().getValue().get(position));

    }

    @Override
    public int getItemCount() {
        return viewModel.getCompanies().getValue().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final FragmentItemBinding binding;


        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(Company company) {
            binding.setItem(company);
            binding.executePendingBindings();
        }
    }
}
