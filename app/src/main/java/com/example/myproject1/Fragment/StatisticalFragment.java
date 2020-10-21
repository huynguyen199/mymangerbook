package com.example.myproject1.Fragment;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject1.Adapter.adapterBook;
import com.example.myproject1.Adapter.adapterStatistical;
import com.example.myproject1.Dao.Dao_statistical;
import com.example.myproject1.MainActivity;
import com.example.myproject1.Model.Book;
import com.example.myproject1.Model.HoaDon;
import com.example.myproject1.Model.HoaDonChiTiet;
import com.example.myproject1.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.paging.FirebaseDataSource;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.DataCollectionConfigStorage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StatisticalFragment extends Fragment {
    private RecyclerView recyclerView;

    public ArrayList<HoaDonChiTiet> list  = new ArrayList<>();
    public adapterStatistical adapter;
    Dao_statistical dao_statistical;
/**
 * thống kê top 10 theo tháng
*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_statistical, container, false);

        recyclerView = v.findViewById(R.id.recycler_top10);


        recyclerView.setHasFixedSize(true);


        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());

        //dao nguoc limittolast
        linearLayout.setReverseLayout(true);
        linearLayout.setStackFromEnd(true);
        //****
        recyclerView.setLayoutManager(linearLayout);

        dao_statistical = new Dao_statistical(container.getContext(),v);


        dao_statistical.readAll();
        adapter = new adapterStatistical(list,getActivity());

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
