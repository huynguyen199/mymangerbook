package com.example.myproject1.Dao;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject1.Adapter.adapterStatistical;
import com.example.myproject1.Fragment.StatisticalFragment;
import com.example.myproject1.MainActivity;
import com.example.myproject1.Model.HoaDon;
import com.example.myproject1.Model.HoaDonChiTiet;
import com.example.myproject1.Pager.Adapter.HoadonAdapter;
import com.example.myproject1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Dao_statistical {

    private DatabaseReference firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<HoaDonChiTiet> list = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

    private Context context;
    private View v;

    public Dao_statistical(Context context, View v) {
        this.context = context;
        this.v = v;
        this.firebaseDatabase = FirebaseDatabase.getInstance().getReference("HoaDonChiTiet");
        this.databaseReference = FirebaseDatabase.getInstance().getReference("HoaDon");
    }

    public void readAll(){

        final Date today = new Date();
        final Calendar calenstart = Calendar.getInstance();
        calenstart.setTime(today);

        calenstart.set(Calendar.DAY_OF_MONTH, 1);


        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);

//        Log.d("date",);

        final String start = sdf.format(calenstart.getTime());
        final String end = sdf.format(calendar.getTime());

        final ValueEventListener valueEventListener1 = new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                    for(DataSnapshot data:snapshot.getChildren()){
                        hoaDonChiTiet = data.getValue(HoaDonChiTiet.class);
                        list.add(hoaDonChiTiet);
                    }
                    list.sort(new Comparator<HoaDonChiTiet>() {
                        @Override
                        public int compare(HoaDonChiTiet o1, HoaDonChiTiet o2) {
                            return Integer.parseInt(o1.getSoLuongMua()) > Integer.parseInt(o2.getSoLuongMua()) ? 1 : -1;
                        }
                    });

                    adapterStatistical adapter = new adapterStatistical(list, (Activity) context);
                    TextView date_today = v.findViewById(R.id.date_today);
                    TextView date_month = v.findViewById(R.id.date_month);

                    date_today.setText("Hôm nay: " + sdf.format(today.getTime()));
                    date_month.setText("Top 10 bán chạy từ ngày "+ sdf.format(calenstart.getTime()) +" đến "+sdf.format(calendar.getTime()));

                    RecyclerView recyclerView = v.findViewById(R.id.recycler_top10);
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };



        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    list.clear();
                    HoaDon hoaDon = new HoaDon();
                    for (DataSnapshot data : snapshot.getChildren()) {
                        hoaDon = data.getValue(HoaDon.class);
                        firebaseDatabase.orderByChild("maHoaDon")
                                .equalTo(hoaDon.getMaHoadon())
                                .limitToLast(10)
                                .addValueEventListener(valueEventListener1);
                    }
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };


        databaseReference.orderByChild("ngaymua").startAt(start).endAt(end)
                .addValueEventListener(valueEventListener);



/*
        Log.d("stt","1");
        databaseReference.orderByChild("ngaymua").startAt(sdfstart.format(datestart.getTime())).endAt(sdfend.format(dateend.getTime()))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.d("stt","2");

                        if(snapshot.exists()){
                            Log.d("stt","3");

                            HoaDon hoaDon = new HoaDon();
                            for(DataSnapshot data:snapshot.getChildren()){
                                hoaDon = data.getValue(HoaDon.class);
                                firebaseDatabase.orderByChild("maHoaDon")
                                        .equalTo(hoaDon.getMaHoadon())
                                        .limitToLast(5)
                                        .addValueEventListener(new ValueEventListener() {
                                            @RequiresApi(api = Build.VERSION_CODES.N)
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.exists()){
                                                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                                                    for(DataSnapshot data:snapshot.getChildren()){
                                                        hoaDonChiTiet = data.getValue(HoaDonChiTiet.class);
                                                        list.add(hoaDonChiTiet);

                                                    }



                                                }

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/


    }




}
