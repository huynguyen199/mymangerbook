package com.example.myproject1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject1.Dao.Dao_Book;
import com.example.myproject1.Dao.Dao_Book_Category;
import com.example.myproject1.Model.Book;
import com.example.myproject1.Model.bookCategory;
import com.example.myproject1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class adapterBook extends FirebaseRecyclerAdapter<Book, adapterBook.BookHoder> {
    Context context;
    View view;
    public adapterBook(@NonNull FirebaseRecyclerOptions<Book> options,Context context,View view) {
        super(options);
        this.context = context;
        this.view = view;
    }

    @Override
    protected void onBindViewHolder(@NonNull final BookHoder holder, final int position, @NonNull Book model) {
        holder.masach.setText(model.getMasach());
        holder.amount.setText(model.getGiabia());
        final String index = getRef(position).getKey();
        Log.d("index1",index);
        holder.imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Snackbar.make(view,"ban co chac chan xoa",5000)
                            .setActionTextColor(Color.GREEN)
                            .setAction("Yes", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Dao_Book dao = new Dao_Book(context, holder.itemView);
                                    dao.delete(index);
                                    notifyDataSetChanged();
                                }
                            }).show();


                    }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



    }

    @NonNull
    @Override
    public BookHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        return new BookHoder(view);
    }

    public static class BookHoder extends RecyclerView.ViewHolder{
            TextView masach,amount;
            ImageButton imgxoa;

        public BookHoder(@NonNull View itemView) {
            super(itemView);
            masach = itemView.findViewById(R.id.textv_masach);
            amount = itemView.findViewById(R.id.textv_amount);
            imgxoa = itemView.findViewById(R.id.image_xoa);

        }
    }

}
