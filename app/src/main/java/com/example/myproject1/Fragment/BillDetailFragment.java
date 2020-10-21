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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject1.Adapter.adapterBillDetail;
import com.example.myproject1.Adapter.adapterBook;
import com.example.myproject1.Adapter.adapterCategory;
import com.example.myproject1.Dao.Dao_Pay;
import com.example.myproject1.Model.Book;
import com.example.myproject1.Model.HoaDonChiTiet;
import com.example.myproject1.Model.bookCategory;
import com.example.myproject1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class BillDetailFragment extends Fragment {
    private DatabaseReference firebaseDatabase;
    private RecyclerView recyclerView;
    EditText search;
    adapterBillDetail adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bill_detail,container,false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Category");

        recyclerView = v.findViewById(R.id.recycler_bill_detail);
        recyclerView.setHasFixedSize(true);
        search = v.findViewById(R.id.search_bar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("HoaDonChiTiet");

        FirebaseRecyclerOptions<HoaDonChiTiet> options =
                new FirebaseRecyclerOptions.Builder<HoaDonChiTiet>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("HoaDonChiTiet"),HoaDonChiTiet.class)
                        .build();
        adapter = new adapterBillDetail(options,container.getContext(),v);


        recyclerView.setAdapter(adapter);


        adapter.notifyDataSetChanged();

        return v;
    }

//    public void search(String data){
//        Query query = firebaseDatabase.orderByChild("masach").startAt(data).endAt(data + "\uf8ff");
//        final FirebaseRecyclerOptions<Book> options = new FirebaseRecyclerOptions.Builder<Book>()
//                .setQuery(query,Book.class)
//                .build();
//        adapter.updateOptions(options);
//
//    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}
