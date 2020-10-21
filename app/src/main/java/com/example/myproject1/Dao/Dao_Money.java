package com.example.myproject1.Dao;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.myproject1.Model.Book;
import com.example.myproject1.Model.HoaDon;
import com.example.myproject1.Model.HoaDonChiTiet;
import com.example.myproject1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Dao_Money {
    Context context;
    View v;
    private DatabaseReference hoadonchitiet;
    private DatabaseReference hoadon;
    private DatabaseReference Sach;

    public static int RESULT_TODAY = 0;
    public static int RESULT_MONTH = 0;
    public static int RESULT_YEAR = 0;

    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");


    public Dao_Money(Context context, View v) {
        this.context = context;
        this.v = v;
        this.hoadonchitiet = FirebaseDatabase.getInstance().getReference("HoaDonChiTiet");
        this.hoadon = FirebaseDatabase.getInstance().getReference("HoaDon");
        this.Sach = FirebaseDatabase.getInstance().getReference("Sach");
    }

    public Dao_Money() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getToday(){
        RESULT_TODAY = 0;

        final Date today = new Date();
        final Calendar calenstart = Calendar.getInstance();
        calenstart.setTime(today);
        long now = System.currentTimeMillis();

        Date date = new Date(now);
        Log.d("today", String.valueOf(sdf.format(today.getTime())));
//
//        final String start = sdf.format(calenstart.getTime());
//        final String end = sdf.format(calendar.getTime());

        hoadon.orderByChild("ngaymua")
                    .equalTo(sdf.format(date.getTime()))
                    .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    HoaDon hoaDon = null;
                    for(DataSnapshot data:snapshot.getChildren()){
                        hoaDon = data.getValue(HoaDon.class);
                        hoadonchitiet
                                .orderByChild("maHoaDon")
                                .equalTo(hoaDon.getMaHoadon())
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.exists()){
                                            HoaDonChiTiet hoaDonChiTiet = null;
                                            for(DataSnapshot data:snapshot.getChildren()){
                                                hoaDonChiTiet = data.getValue(HoaDonChiTiet.class);

                                                final HoaDonChiTiet finalHoaDonChiTiet = hoaDonChiTiet;
                                                Sach.orderByChild("masach")
                                                        .equalTo(hoaDonChiTiet.getMaSach())
                                                        .addValueEventListener(new ValueEventListener() {

                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                int sum =0;
                                                                if(snapshot.exists()){
                                                                    Book book = null;
                                                                    for(DataSnapshot data:snapshot.getChildren()){

                                                                            book = data.getValue(Book.class);
                                                                            sum = Integer.valueOf(book.getGiabia()) *
                                                                                    Integer.valueOf(finalHoaDonChiTiet.getSoLuongMua());
                                                                            Log.d("sum", String.valueOf(sum));
                                                                    }
                                                                    RESULT_TODAY+=sum;
                                                                    Locale localeVN = new Locale("vi", "VN");
                                                                    NumberFormat nf = NumberFormat.getCurrencyInstance(localeVN);
                                                                    TextView text_today = v.findViewById(R.id.today_money);

                                                                    text_today.setText("Hôm nay : "+nf.format(RESULT_TODAY));

                                                                    Log.d("RESULT_TODAY", String.valueOf(RESULT_TODAY));

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
                                });

                    }

                }else{
                    TextView text_today = v.findViewById(R.id.today_money);

                    text_today.setText(String.valueOf(RESULT_TODAY));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void getMonth(){
        RESULT_MONTH = 0;
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

                Log.d("date",start);
                Log.d("date",end);


        hoadon.orderByChild("ngaymua")
                .startAt(start).endAt(end)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            HoaDon hoaDon = null;
                            for(DataSnapshot data:snapshot.getChildren()){
                                hoaDon = data.getValue(HoaDon.class);
                                hoadonchitiet
                                        .orderByChild("maHoaDon")
                                        .equalTo(hoaDon.getMaHoadon())
                                        .addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.exists()){
                                                    HoaDonChiTiet hoaDonChiTiet = null;
                                                    for(DataSnapshot data:snapshot.getChildren()){
                                                        hoaDonChiTiet = data.getValue(HoaDonChiTiet.class);

                                                        final HoaDonChiTiet finalHoaDonChiTiet = hoaDonChiTiet;
                                                        Sach.orderByChild("masach")
                                                                .equalTo(hoaDonChiTiet.getMaSach())
                                                                .addValueEventListener(new ValueEventListener() {

                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                        int sum =0;
                                                                        if(snapshot.exists()){
                                                                            Book book = null;
                                                                            for(DataSnapshot data:snapshot.getChildren()){
                                                                                book = data.getValue(Book.class);
                                                                                sum = Integer.valueOf(book.getGiabia()) *
                                                                                        Integer.valueOf(finalHoaDonChiTiet.getSoLuongMua());
                                                                                Log.d("sum", String.valueOf(sum));
                                                                            }
                                                                            RESULT_MONTH+=sum;
                                                                            TextView text_month = v.findViewById(R.id.month_money);
                                                                            Locale localeVN = new Locale("vi", "VN");
                                                                            NumberFormat nf = NumberFormat.getCurrencyInstance(localeVN);
                                                                            text_month.setText("Tháng này : " + nf.format(RESULT_MONTH));

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
                                        });

                            }

                        }else{
                            TextView text_month = v.findViewById(R.id.month_money);

                            text_month.setText(String.valueOf(RESULT_MONTH));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
    public void getYear(){
        RESULT_YEAR = 0;
        final Date today = new Date();
        final Calendar calenstart = Calendar.getInstance();
        calenstart.setTime(today);
        calenstart.set(Calendar.MONTH, Calendar.JANUARY);
        calenstart.set(Calendar.DAY_OF_MONTH, 1);

        final Calendar calenend = Calendar.getInstance();
        calenend.setTime(today);
        calenend.set(Calendar.MONTH, Calendar.JANUARY);
        calenend.set(Calendar.DAY_OF_MONTH, 1);
        calenend.add(Calendar.DATE, -1);


        final String start = sdf.format(calenstart.getTime());
        final String end = sdf.format(calenend.getTime());


        Log.d("start",start);
        Log.d("end",end);

        hoadon.orderByChild("ngaymua")
                .startAt(start).endAt(end)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            HoaDon hoaDon = null;
                            for(DataSnapshot data:snapshot.getChildren()){
                                hoaDon = data.getValue(HoaDon.class);
                                hoadonchitiet
                                        .orderByChild("maHoaDon")
                                        .equalTo(hoaDon.getMaHoadon())
                                        .addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.exists()){
                                                    HoaDonChiTiet hoaDonChiTiet = null;
                                                    for(DataSnapshot data:snapshot.getChildren()){
                                                        hoaDonChiTiet = data.getValue(HoaDonChiTiet.class);

                                                        final HoaDonChiTiet finalHoaDonChiTiet = hoaDonChiTiet;
                                                        Sach.orderByChild("masach")
                                                                .equalTo(hoaDonChiTiet.getMaSach())
                                                                .addValueEventListener(new ValueEventListener() {

                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                        int sum =0;
                                                                        if(snapshot.exists()){
                                                                            Book book = null;
                                                                            for(DataSnapshot data:snapshot.getChildren()){
                                                                                book = data.getValue(Book.class);
                                                                                sum = Integer.valueOf(book.getGiabia()) *
                                                                                        Integer.valueOf(finalHoaDonChiTiet.getSoLuongMua());
                                                                                Log.d("sum", String.valueOf(sum));
                                                                            }

                                                                                RESULT_YEAR+=sum;
                                                                            Locale localeVN = new Locale("vi", "VN");
                                                                            NumberFormat nf = NumberFormat.getCurrencyInstance(localeVN);

                                                                            TextView textyear = v.findViewById(R.id.year_money);

                                                                            textyear.setText("Năm nay : " + nf.format(RESULT_YEAR));

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
                                        });

                            }

                        }else{
                            TextView textyear = v.findViewById(R.id.year_money);
                            textyear.setText(String.valueOf(RESULT_YEAR));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

}
