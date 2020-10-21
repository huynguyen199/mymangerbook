package com.example.myproject1.Other;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myproject1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class lichpicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private View view;
    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

    public lichpicker(View view) {
        this.view = view;
    }

    @Override
    public void onDateSet(DatePicker view2, int year, int month, int dayOfMonth) {
        EditText mmahoadon = (EditText) view.getTag();
        final Button lich = view.findViewById(R.id.lich);
        String date = null;
        if(month < 10 && dayOfMonth < 10){
            date = year+"-0"+month+"-0"+dayOfMonth;
        }
        else if(month < 10){
             date = year+"-0"+month+"-"+dayOfMonth;
        }
        else if(dayOfMonth < 10){
             date = year+"-"+month+"-0"+dayOfMonth;
        }
        else{
             date = year+"-"+month+"-"+dayOfMonth;
        }


        if(view == lich){
            mmahoadon.setText(date);
        }




    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


}
