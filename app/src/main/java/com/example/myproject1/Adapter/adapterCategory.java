package com.example.myproject1.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject1.Dao.Dao_Book;
import com.example.myproject1.Dao.Dao_Book_Category;
import com.example.myproject1.Model.bookCategory;
import com.example.myproject1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;

public class adapterCategory extends FirebaseRecyclerAdapter<bookCategory,adapterCategory.CategoryHolder> {
    private Context context;
    private View view;
    public adapterCategory(@NonNull FirebaseRecyclerOptions<bookCategory> options,Context context,View view) {
        super(options);
        this.context = context;
        this.view = view;
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryHolder holder, final int position, @NonNull bookCategory model) {
        holder.tentheloai.setText(model.getMatheloai());
        holder.vitri.setText(model.getVitri());
        final String index = getRef(position).getKey();
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Snackbar.make(view,"ban co chac chan xoa",5000)
                            .setActionTextColor(Color.GREEN)
                            .setAction("Yes", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Dao_Book_Category dao = new Dao_Book_Category(context,v);
                                    dao.delete(index);
                                    notifyDataSetChanged();
                                }
                            }).show();

                }catch (Exception e){
                    e.printStackTrace();
                }
                notifyDataSetChanged();
            }
        });

    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookcategory_item, parent, false);
        return new CategoryHolder(view);
    }


    class CategoryHolder extends RecyclerView.ViewHolder{
        private TextView vitri,tentheloai;
        ImageButton xoa;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            vitri = itemView.findViewById(R.id.textv_vitri);
            tentheloai = itemView.findViewById(R.id.textv_namecategory);
            xoa = itemView.findViewById(R.id.image_xoa);
        }
    }
}
