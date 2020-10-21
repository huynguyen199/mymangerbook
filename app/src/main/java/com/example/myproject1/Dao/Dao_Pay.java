package com.example.myproject1.Dao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myproject1.Adapter.adapterPay;
import com.example.myproject1.Model.Book;
import com.example.myproject1.Model.HoaDon;
import com.example.myproject1.Model.HoaDonChiTiet;
import com.example.myproject1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Dao_Pay {
    private Context context;
    private View v;
    public static  int COUNT = 0;
    public static float RESULT = 0;
    private ArrayList<Book> list = new ArrayList<>();
    Book book = new Book();

    public Dao_Pay(Context context, View v) {
        this.context = context;
        this.v = v;
    }

    public Dao_Pay() {
    }


    /*
    public void getCount(){
        DatabaseReference datasach = FirebaseDatabase.getInstance().getReference().child("HoaDonChiTiet");
        datasach.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    COUNT = (int) snapshot.getChildrenCount();
                    Log.d("count", String.valueOf(COUNT));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/

    public void readBook(String masach){
        DatabaseReference datasach = FirebaseDatabase.getInstance().getReference().child("Sach");
        datasach
                .orderByChild("masach")
                .equalTo(masach)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Book book = null;
                        if(snapshot.exists()) {

                            for (DataSnapshot data : snapshot.getChildren()) {
                                 book = data.getValue(Book.class);
                            }
                            try {
                                final TextView giabia = v.findViewById(R.id.giabia);
                                final TextView soluong = v.findViewById(R.id.soluong);

                                giabia.setText(book.getGiabia());

                                Float tongtien = Float.valueOf(soluong.getText().toString()) * Float.valueOf(giabia.getText().toString());

                                final TextView money = v.findViewById(R.id.money);
                                Locale localeVN = new Locale("vi", "VN");
                                NumberFormat nf = NumberFormat.getCurrencyInstance(localeVN);
                                money.setText("Thành tiền : "+nf.format(tongtien));


                            }catch (Exception e){
                                e.printStackTrace();
                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


    public void insert(HoaDonChiTiet hoaDonChiTiet){

//        hoaDonChiTiet.maHDCT = String.valueOf(++COUNT);
        DatabaseReference datasach = FirebaseDatabase.getInstance().getReference().child("HoaDonChiTiet");
//        HashMap<String, Object> map = (HashMap<String, Object>) hoaDonChiTiet.toMap();


        String maHDCT = String.valueOf(COUNT++);
        String maHoaDon = hoaDonChiTiet.getMaHoaDon();
        String maSach = hoaDonChiTiet.getMaSach();
        String soLuongMua = hoaDonChiTiet.getSoLuongMua();
        HoaDonChiTiet hoaDonChiTiet1 = new HoaDonChiTiet(maHDCT,maHoaDon,maSach,soLuongMua);
        datasach.push().setValue(hoaDonChiTiet1);

    }

    public void readlistSpinner(){
        DatabaseReference datasach = FirebaseDatabase.getInstance().getReference().child("Sach");
        DatabaseReference datahoadon = FirebaseDatabase.getInstance().getReference().child("HoaDon");

        datahoadon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> list = new ArrayList<>();
                if (snapshot.exists()){
                    for(DataSnapshot data : snapshot.getChildren()){
                        list.add(data.getValue(HoaDon.class).getMaHoadon());
                    }
                    try {
                        ArrayAdapter hoadonAdapter = new ArrayAdapter(context, R.layout.simple_spinner, list);
                        final Spinner spmasach = v.findViewById(R.id.spinner_mahoadon);
                        spmasach.setAdapter(hoadonAdapter);
                    }catch (Exception e){

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //

        datasach.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<String> list = new ArrayList<>();
                if (snapshot.exists()){
                    for(DataSnapshot data : snapshot.getChildren()){
                        list.add(data.getValue(Book.class).getMasach());
                    }
                    try {
                        ArrayAdapter masachAdapter = new ArrayAdapter(context, R.layout.simple_spinner, list);
                        final Spinner spmasach = v.findViewById(R.id.spinner_masach);
                        spmasach.setAdapter(masachAdapter);
                    }catch (Exception e){

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void querySum() {
        RESULT = 0;
        DatabaseReference dataHDCH = FirebaseDatabase.getInstance().getReference().child("HoaDonChiTiet");
        final DatabaseReference datasach = FirebaseDatabase.getInstance().getReference().child("Sach");


        dataHDCH.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                if(snapshot.exists()) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        hoaDonChiTiet = data.getValue(HoaDonChiTiet.class);
                        Log.d("hoadonchitiet", hoaDonChiTiet.getMaSach());


                        final HoaDonChiTiet finalHoaDonChiTiet = hoaDonChiTiet;
                        datasach.orderByChild("masach")
                                .equalTo(hoaDonChiTiet.getMaSach())
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        float sum = 0;
                                        Book book = null;
                                        for (DataSnapshot data : snapshot.getChildren()) {
                                            book = data.getValue(Book.class);
                                            sum += Float.valueOf(book.getGiabia()) * Float.valueOf(finalHoaDonChiTiet.getSoLuongMua());
                                            RESULT += sum;
                                            Log.d("sum", String.valueOf(RESULT));

                                            final TextView money = (TextView) v.getTag();
                                            Locale localeVN = new Locale("vi", "VN");
                                            NumberFormat nf = NumberFormat.getCurrencyInstance(localeVN);
                                            money.setText(nf.format(RESULT));
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });


                    }
                }else{
                    final TextView money = (TextView) v.getTag();
                    Locale localeVN = new Locale("vi", "VN");
                    NumberFormat nf = NumberFormat.getCurrencyInstance(localeVN);
                    money.setText(nf.format(RESULT));
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


            public void delete(final String database) {
                FirebaseDatabase.getInstance().getReference()
                        .child("HoaDonChiTiet")
                        .child(database)
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(context, "da xoa", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                FirebaseDatabase.getInstance().getReference()
                        .child("HoaDonChiTiet")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(!snapshot.exists()){
                                    COUNT=0;
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }
}

