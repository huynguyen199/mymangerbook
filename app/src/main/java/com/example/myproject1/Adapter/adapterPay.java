package com.example.myproject1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject1.Dao.Dao_Pay;
import com.example.myproject1.Model.Book;
import com.example.myproject1.Model.HoaDonChiTiet;
import com.example.myproject1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.Collection;

public class adapterPay extends FirebaseRecyclerAdapter<HoaDonChiTiet, adapterPay.PayHoder> {
    private Context context;
    ArrayList<Book> list;

    public adapterPay(@NonNull FirebaseRecyclerOptions<HoaDonChiTiet> options,Context context) {
        super(options);
        this.context = context;
    }


    @Override
    protected void onBindViewHolder(@NonNull final PayHoder holder, final int position, @NonNull HoaDonChiTiet model) {

        holder.masach.setText(model.getMaSach());
        holder.soluong.setText(model.getSoLuongMua());
        Dao_Pay dao_pay = new Dao_Pay(context,holder.itemView);
        dao_pay.readBook(model.getMaSach());
        final String index = getRef(position).getKey();


        holder.btnimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Dao_Pay dao_pay = new Dao_Pay(context, holder.itemView);


                    dao_pay.delete(index);

                }catch (Exception e){
                    e.printStackTrace();
                }
                notifyDataSetChanged();

            }
        });


    }

    @NonNull
    @Override
    public PayHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bill_detail_item, parent, false);
        return new PayHoder(view);
    }

    public static class PayHoder extends RecyclerView.ViewHolder{
        TextView masach , soluong , giabia , money;
        Button btnimage;
        public PayHoder(@NonNull View itemView) {
            super(itemView);
            masach = itemView.findViewById(R.id.masach);
            soluong = itemView.findViewById(R.id.soluong);
            giabia = itemView.findViewById(R.id.giabia);
            money = itemView.findViewById(R.id.money);
            btnimage = itemView.findViewById(R.id.image_del);

        }
    }
}
