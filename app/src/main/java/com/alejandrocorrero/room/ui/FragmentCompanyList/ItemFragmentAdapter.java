package com.alejandrocorrero.room.ui.FragmentCompanyList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alejandrocorrero.room.data.model.Company;
import com.alejandrocorrero.room.databinding.FragmentItemBinding;

import java.util.ArrayList;
import java.util.List;


public class ItemFragmentAdapter extends RecyclerView.Adapter<ItemFragmentAdapter.ViewHolder> {
    private final Callback mcallback;

    public interface Callback {
        void onItemClick(Company company);
        void onLongItemClick(Company company);
    }

    List<Company> list = new ArrayList<>();

    public ItemFragmentAdapter(Callback mCallback) {
        this.mcallback=mCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentItemBinding binding = FragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(ItemFragmentAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));
        holder.itemView.setOnClickListener(view -> mcallback.onItemClick(list.get(position)));
        holder.itemView.setOnLongClickListener(view -> {
            mcallback.onLongItemClick((list.get(position)));
            return true;
        });

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
