package com.example.myproject1.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject1.Adapter.adapterBill;
import com.example.myproject1.Dao.Dao_Bill;
import com.example.myproject1.MainActivity;
import com.example.myproject1.Model.HoaDon;
import com.example.myproject1.R;
import com.example.myproject1.Other.lichpicker;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BillFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton btnfloat;
    EditText search;
    adapterBill adapter;
    private DatabaseReference firebaseDatabase;
    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_bill,container,false);

        recyclerView = v.findViewById(R.id.recycler_hoadon);
        btnfloat = v.findViewById(R.id.floating);
        search = v.findViewById(R.id.search_hoadon);
        recyclerView.setHasFixedSize(true);
        search = v.findViewById(R.id.search_hoadon);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("HoaDon");

//        Dao_Bill dao = new Dao_Bill(container.getContext(),v);


        btnfloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialog = DialogPlus.newDialog(getContext())
                        .setGravity(Gravity.TOP)
                        .setContentHolder(new ViewHolder(R.layout.dialog_hoadon))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                final View holderView = (RelativeLayout)dialog.getHolderView();
                holderView.setTag(dialog);
                final EditText mmahoadon = holderView.findViewById(R.id.mahoadon);
                final EditText mdate = holderView.findViewById(R.id.ngaymua);
                final Button lich = holderView.findViewById(R.id.lich);
                final Button btnthem = holderView.findViewById(R.id.themhoadon);


                mmahoadon.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String mahoadon = mmahoadon.getText().toString();
                        String date = mdate.getText().toString();
                        btnthem.setEnabled(!mahoadon.isEmpty() && !date.isEmpty());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                mdate.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String mahoadon = mmahoadon.getText().toString();
                        String date = mdate.getText().toString();
                        btnthem.setEnabled(!mahoadon.isEmpty() && !date.isEmpty());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                btnthem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            sdf.parse(mdate.getText().toString());
                            final String mahoadon = mmahoadon.getText().toString();
                            final String date = mdate.getText().toString();

                            Dao_Bill dao_bill = new Dao_Bill(container.getContext(), holderView);
                            dao_bill.insert(new HoaDon(mahoadon, date));
                            adapter.notifyDataSetChanged();

                        } catch (Exception e) {
                            mdate.setError("sai định dạng ngày");
                        }

                    }
                });

                lich.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setTag(mdate);
                        DialogFragment newFragment = new lichpicker(v);
                        newFragment.show(getFragmentManager(), "timePicker");
                    }
                });


                adapter.notifyDataSetChanged();
                dialog.show();
            }
        });

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
            Query query = firebaseDatabase.orderByChild("maHoadon").startAt(data).endAt(data + "\uf8ff");
            final FirebaseRecyclerOptions<HoaDon> options = new FirebaseRecyclerOptions.Builder<HoaDon>()
                    .setQuery(query, HoaDon.class)
                    .build();
            adapter = new adapterBill(options, getContext(),v);
            adapter.startListening();
            recyclerView.setAdapter(adapter);

    }
}
