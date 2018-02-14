package com.alejandrocorrero.room.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alejandrocorrero.room.R;
import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.databinding.FragmentItemBinding;
import com.alejandrocorrero.room.ui.ItemFragment.OnListFragmentInteractionListener;
import com.alejandrocorrero.room.ui.dummy.DummyContent.DummyItem;

import java.util.List;

public class fragment_adapter extends RecyclerView.Adapter<fragment_adapter.ViewHolder> {

    private final int mBRid;
    MainActivityViewModel viewModel;
    public fragment_adapter(int mBRid) {

        this.mBRid = mBRid;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentItemBinding itemBinding = FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemBinding, mBRid);
    }

    @Override
    public void onBindViewHolder(fragment_adapter.ViewHolder holder, int position) {
        holder.bind(mValues.get(position));

    }

    @Override
    public int getItemCount() {
        return mValues.size();
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
