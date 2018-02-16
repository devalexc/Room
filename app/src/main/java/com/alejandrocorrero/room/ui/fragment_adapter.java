package com.alejandrocorrero.room.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.databinding.FragmentItemBinding;

import java.util.ArrayList;
import java.util.List;


public class fragment_adapter extends RecyclerView.Adapter<fragment_adapter.ViewHolder> {
    private final Callback mcallback;

    public interface Callback {
        void onClick(Company company);
    }

    List<Company> list = new ArrayList<>();

    public fragment_adapter(Callback mCallback) {
        this.mcallback=mCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentItemBinding binding = FragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(fragment_adapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));
        holder.itemView.setOnClickListener(view -> mcallback.onClick(list.get(position)));

    }

    public void setList(List<Company> list) {

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
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
