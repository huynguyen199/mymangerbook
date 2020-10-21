package com.example.myproject1.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject1.Adapter.adapterCategory;
import com.example.myproject1.Adapter.adapterPay;
import com.example.myproject1.Dao.Dao_Pay;
import com.example.myproject1.Model.HoaDonChiTiet;
import com.example.myproject1.Model.bookCategory;
import com.example.myproject1.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.Locale;

public class Payfragment extends Fragment {
    private adapterPay adapter;
    private RecyclerView recyclerView;
    Spinner spinnerhd,spinnersach;
    Button btnadd,btnpay;
    EditText soluong;
    TextView pay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pay,container,false);

        recyclerView = v.findViewById(R.id.recycler_pay);
        spinnerhd = v.findViewById(R.id.spinner_mahoadon);
        spinnersach = v.findViewById(R.id.spinner_masach);

        btnadd = v.findViewById(R.id.btn_addhoadon);
        btnpay = v.findViewById(R.id.btn_pay);
        pay = v.findViewById(R.id.pay);
        soluong = v.findViewById(R.id.soluongmua);

        soluong.addTextChangedListener(textWatcher);


        final Dao_Pay dao = new Dao_Pay(container.getContext(),v);

        dao.readlistSpinner();

//        them hoa don
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soluong.getText().equals(null)){
                    soluong.setError("không được để trống");
                }
                Dao_Pay dao_pay = new Dao_Pay(container.getContext(),v);

                String mahoadon = spinnerhd.getSelectedItem().toString();
                String masach = spinnersach.getSelectedItem().toString();
                String soluongmua = soluong.getText().toString();
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(null,mahoadon, masach, soluongmua);
                dao_pay.insert(hoaDonChiTiet);



            }
        });


//        thanh toan
        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setTag(pay);
                Dao_Pay dao_pay = new Dao_Pay(container.getContext(),v);
                dao_pay.querySum();

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        FirebaseRecyclerOptions<HoaDonChiTiet> options =
                new FirebaseRecyclerOptions.Builder<HoaDonChiTiet>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("HoaDonChiTiet"),HoaDonChiTiet.class)
                        .build();
        adapter = new adapterPay(options,container.getContext());
        recyclerView.setAdapter(adapter);

        return v;
    }



    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String amount = soluong.getText().toString().trim();
            btnadd.setEnabled(!amount.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }



    @Override
    public void onStop() {
        super.onStop();
//        adapter.stopListening();
    }
}
