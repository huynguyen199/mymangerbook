package com.example.myproject1.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject1.Dao.Dao_User;
import com.example.myproject1.Model.User;
import com.example.myproject1.Model.bookCategory;
import com.example.myproject1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class adapterMangerUser extends FirebaseRecyclerAdapter<User,adapterMangerUser.ManagerUserHolder> {
    private Context context;



    public adapterMangerUser(@NonNull FirebaseRecyclerOptions<User> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final ManagerUserHolder holder, final int position, @NonNull User model) {
        holder.user_name.setText(model.getName());
        holder.user_number_phone.setText(model.getNumberphone());
        final String index = getRef(position).getKey();
        holder.image_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Dao_User dao = new Dao_User(context,holder.itemView);
                    dao.delete(index);

                    notifyDataSetChanged();
                }catch (Exception e){
                    e.printStackTrace();
                }
                notifyDataSetChanged();
            }
        });
    }


    @NonNull
    @Override
    public ManagerUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);

        return new ManagerUserHolder(view);
    }


    class ManagerUserHolder extends RecyclerView.ViewHolder{
        private TextView user_name,user_number_phone;
        private ImageButton image_del;
        public ManagerUserHolder(@NonNull View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.user_name);
            user_number_phone = itemView.findViewById(R.id.user_number_phone);
            image_del = itemView.findViewById(R.id.image_del);

        }
    }

}
