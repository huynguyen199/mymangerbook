package com.example.myproject1.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject1.Adapter.adapterBook;
import com.example.myproject1.Adapter.adapterMangerUser;
import com.example.myproject1.Model.Book;
import com.example.myproject1.Model.User;
import com.example.myproject1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ManagerUserFragment extends Fragment {
    private RecyclerView recyclerView;
    private DatabaseReference firebaseDatabase;
    EditText search;
    adapterMangerUser adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_manager_user,container,false);

        recyclerView = v.findViewById(R.id.recycler_manager_user);
        recyclerView.setHasFixedSize(true);
        search = v.findViewById(R.id.search_bar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("User");


        search("");
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString()!=null){
                    search(s.toString());
                }else{
                    search("");
                }
            }
        });

        return v;
    }


    public void search(String data){

        Query query = firebaseDatabase.orderByChild("name").startAt(data).endAt(data + "\uf8ff");
        final FirebaseRecyclerOptions<User> options = new FirebaseRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .build();
        adapter = new adapterMangerUser(options,getContext());
        adapter.startListening();
        recyclerView.setAdapter(adapter);


    }





}
