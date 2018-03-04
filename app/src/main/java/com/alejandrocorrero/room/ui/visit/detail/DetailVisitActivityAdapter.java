package com.alejandrocorrero.room.ui.visit.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alejandrocorrero.room.R;
import com.alejandrocorrero.room.data.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DetailVisitActivityAdapter extends BaseAdapter {

    private final LayoutInflater mLayoutInflater;
    private List<Student> mData= new ArrayList<>();

    public DetailVisitActivityAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    void setList(List<Student> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.activity_detail_visit_item, parent,
                    false);
            viewHolder = onCreateViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        onBindViewHolder(viewHolder, position);
        return convertView;
    }

    private ViewHolder onCreateViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    private void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.bind(mData.get(position));
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.activity_detail_visit_item, parent,
                    false);
            viewHolder = onCreateViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        onBindViewHolder(viewHolder, position);
        return convertView;
    }

    static class ViewHolder {

        private final TextView lblName;

        public ViewHolder(View itemView) {
            lblName = itemView.findViewById(R.id.lblName);
        }

        public void bind(Student student) {
            lblName.setText(student.getName()+" "+student.getLastName());
        }

    }

}

