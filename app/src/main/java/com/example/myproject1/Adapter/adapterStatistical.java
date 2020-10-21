package com.example.myproject1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject1.Model.HoaDonChiTiet;
import com.example.myproject1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class adapterStatistical extends RecyclerView.Adapter<adapterStatistical.StatisticalHolder > {
    ArrayList<HoaDonChiTiet> list;
    Activity context;

    public adapterStatistical() {
    }

    public adapterStatistical(ArrayList<HoaDonChiTiet> list, Activity context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public StatisticalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.statistical_item,parent,false);
        return (new StatisticalHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticalHolder holder, int position) {
            holder.masach.setText(list.get(position).getMaSach());
            holder.soluongmua.setText(list.get(position).getSoLuongMua());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class StatisticalHolder extends RecyclerView.ViewHolder{
        TextView masach,soluongmua;
        public StatisticalHolder(@NonNull View itemView) {
            super(itemView);
            masach = itemView.findViewById(R.id.masach);
            soluongmua = itemView.findViewById(R.id.soluongmua);
        }
    }


}
