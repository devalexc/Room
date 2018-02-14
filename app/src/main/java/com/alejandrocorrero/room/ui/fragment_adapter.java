package com.alejandrocorrero.room.ui;

import android.app.Application;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.databinding.FragmentItemBinding;

public class fragment_adapter extends RecyclerView.Adapter<fragment_adapter.ViewHolder> {

    private final int mBRid;
    private MainActivityViewModel viewModel;

    public fragment_adapter(int mBRid, Application application) {
        viewModel = new MainActivityViewModel(application);
        this.mBRid = mBRid;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentItemBinding itemBinding = FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(itemBinding, mBRid);
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
        private final int mBRid;

        public ViewHolder(FragmentItemBinding binding, int mBRid) {
            super(binding.getRoot());
            this.binding = binding;
            this.mBRid = mBRid;
        }

        public void bind(Company company) {
            binding.setVariable(mBRid, company);
            binding.executePendingBindings();
        }
    }
}
