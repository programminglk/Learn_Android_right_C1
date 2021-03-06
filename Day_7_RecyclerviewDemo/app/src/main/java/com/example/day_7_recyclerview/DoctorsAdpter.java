package com.example.day_7_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day_7_recyclerview.data.Doctor;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DoctorsAdpter extends RecyclerView.Adapter<DoctorsAdpter.DoctorViewHolder> {

    private ArrayList<Doctor> drList = new ArrayList<>();
    private  OnDoctorClickListner doctorClickListner;
    public  DoctorsAdpter(ArrayList<Doctor> drList){
        this.drList = drList;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View v =inflator.inflate(R.layout.rv_row_item_layout, parent, false);
        DoctorViewHolder dvh = new DoctorViewHolder(v, doctorClickListner );

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
        ImageView sendEmail; //**2

        public DoctorViewHolder(@NonNull View itemView, OnDoctorClickListner listner ) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.dr_name);
            tv_email = itemView.findViewById(R.id.dr_email);
            sendEmail = itemView.findViewById(R.id.btn_sendemail); //**3

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listner != null){
                        int position =  getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listner.onDoctorItemClick(position);
                        }
                    }
                }
            });


            sendEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listner != null){
                        int position =  getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listner.onSendEmailClick(position);   //**5
                        }
                    }
               }
          });



        }
    }

    public interface OnDoctorClickListner{
        void onDoctorItemClick(int position);
        void onSendEmailClick(int position); // **4
    }

    public void setOnDoctorItemClickListner(OnDoctorClickListner listner){
        doctorClickListner = listner;
    }

}
