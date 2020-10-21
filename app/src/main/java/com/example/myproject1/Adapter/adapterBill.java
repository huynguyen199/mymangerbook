package com.example.myproject1.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject1.Dao.Dao_Bill;
import com.example.myproject1.Dao.Dao_Book_Category;
import com.example.myproject1.Model.Book;
import com.example.myproject1.Model.HoaDon;
import com.example.myproject1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class adapterBill extends FirebaseRecyclerAdapter<HoaDon, adapterBill.BillHoder> {
    private Context context;
    private View view;

    public adapterBill(@NonNull FirebaseRecyclerOptions<HoaDon> options, Context context ,View view) {
        super(options);
        this.context = context;
        this.view = view;
    }

    public adapterBill(@NonNull FirebaseRecyclerOptions<HoaDon> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final BillHoder holder, final int position, @NonNull HoaDon model) {
        holder.bill.setText(model.getMaHoadon());
        holder.date.setText(model.getNgaymua());

        final String database = getRef(position).getKey();
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Snackbar.make(view,"ban co chac chan xoa",5000)
                            .setActionTextColor(Color.GREEN)
                            .setAction("Yes", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Dao_Bill dao = new Dao_Bill(context, holder.itemView);
                                    dao.delete(database);
                                    notifyDataSetChanged();
                                }
                            }).show();

                }catch (Exception e){
                    e.printStackTrace();
                }
                notifyDataSetChanged();
            }
        });

    }



    @NonNull
    @Override
    public BillHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hoadon_item, parent, false);
        return new BillHoder(view);
    }


    public static class BillHoder extends RecyclerView.ViewHolder{
        TextView bill,date;
        Button btn;
        public BillHoder(@NonNull View itemView) {
            super(itemView);
            bill = itemView.findViewById(R.id.mahoadon);
            date = itemView.findViewById(R.id.date_hoadon);
            btn = itemView.findViewById(R.id.hoadon_delete);
        }
    }
}
