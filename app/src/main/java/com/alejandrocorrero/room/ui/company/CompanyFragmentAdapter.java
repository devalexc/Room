package com.alejandrocorrero.room.ui.company;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.databinding.FragmentItemBinding;

import java.util.ArrayList;
import java.util.List;


public class CompanyFragmentAdapter extends RecyclerView.Adapter<CompanyFragmentAdapter.ViewHolder> {
    private final Callback mCallback;

    public interface Callback {
        void onItemClick(Company company);

        void onLongItemClick(Company company);
    }

    private List<Company> list = new ArrayList<>();

    CompanyFragmentAdapter(Callback mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentItemBinding binding = FragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(CompanyFragmentAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));
        holder.itemView.setOnClickListener(view -> mCallback.onItemClick(list.get(position)));
        holder.itemView.setOnLongClickListener(view -> {
            mCallback.onLongItemClick((list.get(position)));
            return true;
        });

    }

    void setList(List<Company> list) {

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private final FragmentItemBinding binding;


        ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void bind(Company company) {
            binding.setItem(company);
            binding.executePendingBindings();
        }
    }
}
