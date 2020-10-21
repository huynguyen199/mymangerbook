/*
 *  @Exclude
 *     public Map<String, Object> toMap() {
 *         HashMap<String, Object> result = new HashMap<>();
 *         result.put("matheloai", matheloai);
 *         result.put("tentheloai", tentheloai);
 *         result.put("mota", mota);
 *         result.put("vitri", vitri);
 *
 *         return result;
 *     }
 */

package com.example.myproject1.Dao;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.myproject1.Model.Book;
import com.example.myproject1.Model.bookCategory;
import com.example.myproject1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Dao_Book {
    private Context context;
    private View v;
    public Dao_Book(Context context, View v) {
        this.context = context;
        this.v = v;
    }

    public void insert(final Book book){
        DatabaseReference datastudent = FirebaseDatabase.getInstance().getReference().child("Sach");
        datastudent.orderByChild("masach")
                .equalTo(book.getMasach())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.exists()) {
                            HashMap<String, Object> map = (HashMap<String, Object>) book.toMap();
                            DatabaseReference datastudent = FirebaseDatabase.getInstance().getReference().child("Sach").push();
                            datastudent.setValue(map);
                            AlertDialog dialog = (AlertDialog) v.getTag();
                            dialog.dismiss();

                        } else {

                                    final EditText masv = v.findViewById(R.id.masach);
                                    masv.setError("mã sách đã tồn tại");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void readSpinner(String index){
        final ArrayList<Book> list = new ArrayList<>();
        final DatabaseReference datacourse = FirebaseDatabase.getInstance().getReference().child("Book");
        datacourse.orderByKey()
                .equalTo(index)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Book book = null;
                        for(DataSnapshot data : snapshot.getChildren()){
                            book = data.getValue(Book.class);
                        }

                        switch (v.getId()){
                            case R.id.linear_book:
                                try{
                                final EditText masach = v.findViewById(R.id.masach);
                                final Spinner tentheloai = v.findViewById(R.id.tentheloai);
                                final EditText tieude = v.findViewById(R.id.tieude);
                                final EditText tacgia = v.findViewById(R.id.tacgia);
                                final EditText soluong = v.findViewById(R.id.soluong);
                                final EditText giabia = v.findViewById(R.id.giabia);
                                final EditText nxb = v.findViewById(R.id.nxb);
                                final Button btnadd = v.findViewById(R.id.themsach);

                                    ArrayAdapter<String> adapter = (ArrayAdapter<String>) tentheloai.getAdapter();

//                                    masach.setText(course.getTenkhoahoc());
//                                    mota.setText(course.getMota());
//                                    giangvien.setText(course.getGiangvien());
//                                    startday.setText(course.getStartday());
//                                    endday.setText(course.getEndday());


                                }catch (Exception e){

                                }
                                break;
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void readlistSpinner(){
        DatabaseReference datacourse = FirebaseDatabase.getInstance().getReference().child("TheLoaiSach");
        datacourse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> list = new ArrayList<>();
                if (snapshot.exists()){
                    for(DataSnapshot data : snapshot.getChildren()){
                        list.add(data.getValue(Book.class).getTentheloai());
//                        Log.d("data",)
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.simple_spinner, list);

                    final Spinner sptheloai = v.findViewById(R.id.tentheloai);

                    sptheloai.setAdapter(arrayAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void delete(String index){
        FirebaseDatabase.getInstance().getReference()
                .child("Sach")
                .child(index)
                .removeValue();

    }

}
