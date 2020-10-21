package com.example.myproject1.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myproject1.Adapter.adapterMangerUser;
import com.example.myproject1.Dao.Dao_User;
import com.example.myproject1.Model.User;
import com.example.myproject1.R;

import java.util.regex.Pattern;

public class RegisterUserFragment extends Fragment {
    EditText account,password,cofirm_pass,number_phone,name;
    Button save,close;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register_user,container,false);
        init(v);

        account.setText("huynguyen123");
        password.setText("huynguyen123");
        cofirm_pass.setText("huynguyen123");
        number_phone.setText("1232132");
        account.setText("huynguyen123");
        name.setText("huy nguyen");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String maccount = account.getText().toString();
                String mpassword = password.getText().toString();
                String mcofirm_pass = cofirm_pass.getText().toString();
                String mnumber_phone = number_phone.getText().toString();
                String mname = name.getText().toString();
                Pattern pattern = Pattern.compile("[a-zA-Z0-9\\\\._\\\\-]{6,15}");

                if(!pattern.matcher(maccount).matches()){
                    account.setError("nhập ít nhất từ 6 đến 15");
                }if(mnumber_phone.length() > 10){
                    number_phone.setError("lỗi");
                }if(maccount.isEmpty()){
                    account.setError("Dòng này bị trống");
                }if(mpassword.isEmpty()){
                    password.setError("Dòng này bị trống");

                }if(mcofirm_pass.isEmpty()){
                    cofirm_pass.setError("Dòng này bị trống");

                }if(mnumber_phone.isEmpty()){
                    number_phone.setError("Dòng này bị trống");

                }if(mname.isEmpty()){
                    name.setError("Dòng này bị trống");
                }if(!mpassword.equals(mcofirm_pass)){
                    password.setError("sai mật khẩu");

                }else{

                    User user = new User(maccount,mpassword,mname,mnumber_phone);

                    Dao_User dao = new Dao_User(container.getContext(),account);
                    dao.insert(user);
                    Toast.makeText(container.getContext(),"đăng ký thành công",Toast.LENGTH_SHORT).show();

                }


            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }

    private void init(View v){
        account = v.findViewById(R.id.user_account);
        password = v.findViewById(R.id.user_password);
        cofirm_pass = v.findViewById(R.id.user_cofirm_password);
        number_phone = v.findViewById(R.id.user_number_phone);
        name = v.findViewById(R.id.user_name);
        save = v.findViewById(R.id.user_save);
        close = v.findViewById(R.id.user_close);

    }
}
