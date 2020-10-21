package com.example.myproject1.Fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.myproject1.Dao.Dao_Money;
import com.example.myproject1.R;

import java.util.Random;

public class MoneyFragment extends Fragment {
    TextView today,month,year;
    Dao_Money dao;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_money,container,false);
        dao = new Dao_Money(getContext(),v);

        month = v.findViewById(R.id.month_money);
        year = v.findViewById(R.id.year_money);
        today = v.findViewById(R.id.today_money);
        dao.getToday();
        dao.getMonth();
        dao.getYear();
        return v;
    }


}
