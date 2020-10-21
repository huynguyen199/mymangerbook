package com.example.myproject1.Dao;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.myproject1.LoginActivity;
import com.example.myproject1.MainActivity;
import com.example.myproject1.Model.Book;
import com.example.myproject1.Model.User;
import com.example.myproject1.Model.bookCategory;
import com.example.myproject1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Dao_User {
     Context context;
     View view;

    public Dao_User(Context context) {
        this.context = context;
    }

    public Dao_User(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    public void insert(final User user){
        DatabaseReference datastudent = FirebaseDatabase.getInstance().getReference().child("User");
        datastudent.orderByChild("account")
                .equalTo(user.getAccount())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.exists()) {

                            HashMap<String, Object> map = (HashMap<String, Object>) user.toMap();
                            DatabaseReference datastudent = FirebaseDatabase.getInstance().getReference().child("User").push();
                            datastudent.setValue(map);


                        } else {
                            switch (view.getId()) {

                                case R.id.user_account:
                                    final EditText masv = view.findViewById(R.id.user_account);
                                    masv.setError("mã sách đã tồn tại");
                                    break;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    public void readnameuser(){
        if(LoginActivity.USER!=null) {
            DatabaseReference datastudent = FirebaseDatabase.getInstance().getReference().child("User");
            datastudent.orderByChild("account")
                    .equalTo(LoginActivity.USER.getAccount())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                User user = null;
                                for (DataSnapshot data : snapshot.getChildren()) {
                                    user = data.getValue(User.class);
                                    LoginActivity.USER = user;
                                }


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
    }


    public void delete(final String database){

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User");

            databaseReference.child(database).removeValue();


    }

}
