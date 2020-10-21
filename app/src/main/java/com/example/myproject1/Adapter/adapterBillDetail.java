package com.example.myproject1.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject1.Dao.Dao_Bill;
import com.example.myproject1.Dao.Dao_Pay;
import com.example.myproject1.Model.HoaDon;
import com.example.myproject1.Model.HoaDonChiTiet;
import com.example.myproject1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;

public class adapterBillDetail extends FirebaseRecyclerAdapter<HoaDonChiTiet,adapterBillDetail.BillDetailHolder > {
    private Context context;
    private View view;

    public adapterBillDetail(@NonNull FirebaseRecyclerOptions<HoaDonChiTiet> options,Context context,View view) {
        super(options);
        this.context = context;
        this.view = view;
    }


    @Override
    protected void onBindViewHolder(@NonNull final BillDetailHolder holder, int position, @NonNull HoaDonChiTiet model) {

        holder.masach.setText(model.getMaSach());
        holder.soluong.setText(model.getSoLuongMua());

        Dao_Pay dao_pay = new Dao_Pay(context,holder.itemView);
        dao_pay.readBook(model.getMaSach());

        final String index = getRef(position).getKey();
        holder.btndel.setVisibility(View.GONE);
    }

    @NonNull
    @Override
    public BillDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bill_detail_item, parent, false);
        return new BillDetailHolder(view);
    }


    public static class BillDetailHolder extends RecyclerView.ViewHolder{
        TextView masach,soluong,giabia,money;
        Button btndel;
        public BillDetailHolder(@NonNull View itemView) {
            super(itemView);
            btndel = itemView.findViewById(R.id.image_del);
            masach = itemView.findViewById(R.id.masach);
            soluong = itemView.findViewById(R.id.soluong);
            giabia = itemView.findViewById(R.id.giabia);
            money = itemView.findViewById(R.id.money);

        }
    }
}
