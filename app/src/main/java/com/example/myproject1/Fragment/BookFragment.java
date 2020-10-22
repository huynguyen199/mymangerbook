package com.example.myproject1.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject1.Adapter.adapterBook;
import com.example.myproject1.Dao.Dao_Book;
import com.example.myproject1.MainActivity;
import com.example.myproject1.Model.Book;
import com.example.myproject1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BookFragment extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton btnfloat;
    private DatabaseReference firebaseDatabase;
    EditText search;

    EditText masach,tieude,tacgia,soluong,giabia,nxb;
    Button btnadd;
    Spinner tentheloai;
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-30");
    FirebaseRecyclerAdapter<Book, adapterBook.BookHoder> adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book,container,false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("");

        recyclerView = v.findViewById(R.id.recycler_book);
        recyclerView.setHasFixedSize(true);
        btnfloat = v.findViewById(R.id.float_book);
        search = v.findViewById(R.id.search_bar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Sach");



        btnfloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(container.getContext());
                LayoutInflater inflater = getLayoutInflater();
                final View view = inflater.inflate(R.layout.dialog_book,null);
                builder.setView(view);

                 masach = view.findViewById(R.id.masach);
                tentheloai = view.findViewById(R.id.tentheloai);
                tieude = view.findViewById(R.id.tieude);
                tacgia = view.findViewById(R.id.tacgia);
                soluong = view.findViewById(R.id.soluong);
                giabia = view.findViewById(R.id.giabia);
                nxb = view.findViewById(R.id.nxb);
                btnadd = view.findViewById(R.id.themsach);
                final AlertDialog  dialog = builder.create();

                masach.setText("book01");
                tieude.setText("sach bla bla");
                tacgia.setText("van C");
                soluong.setText("200");
                giabia.setText("20000");
                nxb.setText("ai biet");

                masach.addTextChangedListener(textWatcher);
                tieude.addTextChangedListener(textWatcher);
                tacgia.addTextChangedListener(textWatcher);
                soluong.addTextChangedListener(textWatcher);
                giabia.addTextChangedListener(textWatcher);
                nxb.addTextChangedListener(textWatcher);

                view.setTag(dialog);

                Dao_Book dao_book = new Dao_Book(container.getContext(),view);
                dao_book.readlistSpinner();


                builder.setView(view);


                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            String mmasach = masach.getText().toString();
                            String mtheloai = tentheloai.getSelectedItem().toString();
                            String mtieude = tieude.getText().toString();
                            String mtacgia = tacgia.getText().toString();
                            String mnxb = nxb.getText().toString();
                            String mgiabia = giabia.getText().toString();
                            String msoluong = soluong.getText().toString();

                                Book book = new Book(mmasach, mtheloai, mtieude, mtacgia, mnxb, mgiabia, msoluong);
                                Dao_Book dao = new Dao_Book(container.getContext(), view);
                                dao.insert(book);
                        }catch (Exception e){

                        }
                    }
                });

                dialog.show();
            }
        });


//        Query query = firebaseDatabase.orderByChild("masach").startAt(data).endAt(data + "\uf8ff");
        final FirebaseRecyclerOptions<Book> options = new FirebaseRecyclerOptions.Builder<Book>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Sach"),Book.class)
                .build();
        adapter = new adapterBook(options,getContext(),v);
        recyclerView.setAdapter(adapter);


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
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String mmasach = masach.getText().toString();
            String mtieude = tieude.getText().toString();
            String mtacgia = tacgia.getText().toString();
            String mnxb = nxb.getText().toString();
            String mgiabia = giabia.getText().toString();
            String msoluong = soluong.getText().toString();
            boolean spinner = (tentheloai.getCount() == 0);

//            String mahoadon = mmahoadon.getText().toString();
//            String date = mdate.getText().toString();
            Boolean btn = !mmasach.isEmpty() && !mtieude.isEmpty()
                    &&!mtacgia.isEmpty() && !mnxb.isEmpty()
                    &&!mgiabia.isEmpty() && !msoluong.isEmpty()
                    &&spinner;
            btnadd.setEnabled(btn);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void search(String data){
        Query query = firebaseDatabase.orderByChild("masach").startAt(data).endAt(data + "\uf8ff");
        final FirebaseRecyclerOptions<Book> options = new FirebaseRecyclerOptions.Builder<Book>()
                .setQuery(query,Book.class)
                .build();
        adapter.updateOptions(options);

    }

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
