package com.example.myproject1.Dao;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.myproject1.Model.Book;
import com.example.myproject1.Model.User;
import com.example.myproject1.Model.bookCategory;
import com.example.myproject1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.ArrayList;
import java.util.HashMap;

public class Dao_Book_Category {
    Context context;
    View v;
    public Dao_Book_Category(Context context,View v) {
        this.context = context;
        this.v = v;

    }


    public void insert(final bookCategory book){
        DatabaseReference datastudent = FirebaseDatabase.getInstance().getReference().child("TheLoaiSach");
        datastudent.orderByChild("matheloai")
                .equalTo(book.getMatheloai())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.exists()) {
                            HashMap<String, Object> map = (HashMap<String, Object>) book.toMap();
                            DatabaseReference datastudent = FirebaseDatabase.getInstance().getReference().child("TheLoaiSach").push();
                            datastudent.setValue(map);
                            DialogPlus dialog = (DialogPlus) v.getTag();
                            dialog.dismiss();

                        } else {
                                    final EditText masv = v.findViewById(R.id.matheloai);
                                    masv.setError("mã thể loại đã tồn tại");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void delete(final String database){


        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Sach");
        final Query query = databaseReference.orderByChild("tentheloai");
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        //lay du lieu theloaisach va xoa collection book
        firebaseDatabase.getReference()
                .child("TheLoaiSach")
                .orderByKey()
                .equalTo(database)
                .addValueEventListener(new ValueEventListener() {
                    bookCategory book;
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot data:snapshot.getChildren()){
                             book = data.getValue(bookCategory.class);
                            Log.d("data",book.getMatheloai());
                        }
                        Query query1 = databaseReference.orderByChild("tentheloai")
                                .equalTo(book.getTentheloai());
                        query1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot data: snapshot.getChildren()) {
                                    data.getRef().removeValue();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        firebaseDatabase.getReference().child("TheLoaiSach")
                .child(database)
                .removeValue();


    }


    public void update(){}

    public ArrayList<String> readCategory(){
        final ArrayList<String> list = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("TheLoaiSach");
        Query query = databaseReference.orderByChild("matheloai").equalTo("huy");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1 : snapshot.getChildren()) {

                    bookCategory book =  snapshot1.getValue(bookCategory.class);
                    list.add(book.getTentheloai());
                    Log.d("data", book.getMatheloai());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return list;
    }

}
