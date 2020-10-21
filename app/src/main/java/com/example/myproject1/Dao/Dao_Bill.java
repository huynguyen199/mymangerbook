package com.example.myproject1.Dao;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.myproject1.Model.Book;
import com.example.myproject1.Model.HoaDon;
import com.example.myproject1.Model.HoaDonChiTiet;
import com.example.myproject1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.HashMap;

public class Dao_Bill {
    Context context;
    View view;


    public Dao_Bill(Context context,View view) {
        this.context = context;
        this.view = view;
    }

    public void insert(final HoaDon hoaDon){
        DatabaseReference datastudent = FirebaseDatabase.getInstance().getReference().child("HoaDon");
        datastudent.orderByChild("maHoadon")
                .equalTo(hoaDon.getMaHoadon())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.exists()) {
                            HashMap<String, Object> map = (HashMap<String, Object>) hoaDon.toMap();
                            DatabaseReference datastudent = FirebaseDatabase.getInstance().getReference().child("HoaDon").push();
                            datastudent.setValue(map);
                            DialogPlus dialog = (DialogPlus) view.getTag();
                            dialog.dismiss();
                        } else {

                                    final EditText mahoadon = view.findViewById(R.id.mahoadon);
                                    mahoadon.setError("mã hóa đơn đã tồn tại");

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


    public void delete(final String database){
        FirebaseDatabase.getInstance().getReference()
                .child("HoaDon")
                .child(database)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            HoaDon hoaDon = null;
                            for(DataSnapshot data : snapshot.getChildren()){
                                hoaDon = data.getValue(HoaDon.class);
                            }
                            FirebaseDatabase.getInstance().getReference()
                                    .child("HoaDonChiTiet")
                                    .orderByChild("maHoaDon")
                                    .equalTo(hoaDon.getMaHoadon()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            snapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}
