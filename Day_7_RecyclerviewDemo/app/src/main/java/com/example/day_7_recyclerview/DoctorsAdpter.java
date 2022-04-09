package com.example.day_7_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day_7_recyclerview.data.Doctor;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DoctorsAdpter extends RecyclerView.Adapter<DoctorsAdpter.DoctorViewHolder> {

    private ArrayList<Doctor> drList = new ArrayList<>();

    public  DoctorsAdpter(ArrayList<Doctor> drList){
        this.drList = drList;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View v =inflator.inflate(R.layout.rv_row_item_layout, parent, false);
        DoctorViewHolder dvh = new DoctorViewHolder(v);

        return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
            Doctor dr = drList.get(position);

            holder.tv_email.setText(dr.getName());
            holder.tv_name.setText(dr.getEmail());
    }

    @Override
    public int getItemCount() {
        return drList.size();
    }

    public static class DoctorViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_email;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.dr_name);
            tv_email = itemView.findViewById(R.id.dr_email);
        }
    }

}
