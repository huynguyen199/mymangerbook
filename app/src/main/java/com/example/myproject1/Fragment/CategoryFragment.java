package com.example.myproject1.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject1.Adapter.adapterCategory;
import com.example.myproject1.Dao.Dao_Book_Category;
import com.example.myproject1.Model.bookCategory;
import com.example.myproject1.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class CategoryFragment extends Fragment {
    private adapterCategory adapter;
    private RecyclerView recyclerView;
    private FloatingActionButton btnfloat;
    SearchView searchView;



    EditText matheloai,tentheloai,mota,vitri;
    Button btnthem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category,container,false);

        recyclerView = v.findViewById(R.id.recycler_book_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        btnfloat = v.findViewById(R.id.floating_category);


        FirebaseRecyclerOptions<bookCategory> options =
                new FirebaseRecyclerOptions.Builder<bookCategory>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("TheLoaiSach"),bookCategory.class)
                        .build();
        adapter = new adapterCategory(options,container.getContext(),v);
        recyclerView.setAdapter(adapter);

        btnfloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 final DialogPlus dialog = DialogPlus.newDialog(getContext())
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new ViewHolder(R.layout.dialog_book_category))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                final View holderView = (LinearLayout)dialog.getHolderView();

                matheloai = holderView.findViewById(R.id.matheloai);
                tentheloai = holderView.findViewById(R.id.tentheloai);
                mota = holderView.findViewById(R.id.mota);
                vitri = holderView.findViewById(R.id.vitri);
                btnthem = holderView.findViewById(R.id.them);

                matheloai.addTextChangedListener(textWatcher);
                tentheloai.addTextChangedListener(textWatcher);
                mota.addTextChangedListener(textWatcher);
                vitri.addTextChangedListener(textWatcher);



                holderView.setTag(dialog);
                matheloai.setText("book01");
                tentheloai.setText("cntt");
                mota.setText("ai biet");
                vitri.setText("1");

                btnthem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String mtheloai = matheloai.getText().toString();
                        String mtentheloai = tentheloai.getText().toString();
                        String mmota = mota.getText().toString();
                        String mvitri = vitri.getText().toString();
                        Dao_Book_Category dao = new Dao_Book_Category(container.getContext(),holderView);
                        bookCategory bookCategory = new bookCategory(mtheloai,mtentheloai,mmota,mvitri);

                        dao.insert(bookCategory);
                    }
                });
                adapter.notifyDataSetChanged();

                dialog.show();

            }
        });
        return v;
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String mtheloai = matheloai.getText().toString();
            String mtentheloai = tentheloai.getText().toString();
            String mmota = mota.getText().toString();
            String mvitri = vitri.getText().toString();
            Boolean btn = !mtheloai.isEmpty() && !mtentheloai.isEmpty()
                    &&!mmota.isEmpty() && !mvitri.isEmpty();
            btnthem.setEnabled(btn);
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
        adapter.stopListening();
    }
}
