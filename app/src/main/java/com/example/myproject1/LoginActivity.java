
package com.example.myproject1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject1.Dao.Dao_User;
import com.example.myproject1.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    public static User USER= null;
    EditText account,password;
    CheckBox checkBox;

    //login
    RelativeLayout login;
    TextView text_login;
    ProgressBar btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        init();
        fillnhomatkhau();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginuser();
            }
        });


    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if( keyCode == KeyEvent.KEYCODE_BACK )
        {
            return true;
        }

        return super.onKeyUp(keyCode, event);
    }

    private void loginuser(){
        buttonactivated();

        SharedPreferences pref = getSharedPreferences("myuser.dat",MODE_PRIVATE);
        String user = account.getText().toString();
        String pass = password.getText().toString();
        Dao_User dao_user = new Dao_User(this);
        boolean checked = checkBox.isChecked();

        if(user.isEmpty()){
            account.setError("Field can't be empty");
        }else if(pass.isEmpty()){
            password.setError("Field can't be empty");
        }else {
            checkUser(user, pass, checked);
        }
    }


    public void checkUser(final String account, final String password, final Boolean check){

        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("User");
        Query query = firebaseDatabase.orderByChild("account")
                .equalTo(account);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot != null) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        if (data.exists()) {
                            User user = data.getValue(User.class);
                            if (user.password.equals(password.toString().trim())) {
                                Log.d("data", "dang nhap thanh cong");
                                closebuttonactivated();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                nhotaikhoan(account, password, check);
                                USER = user;
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Password is wrong", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        Log.d("data","qua day");
    }
    public void buttonactivated(){
        btn_login.setVisibility(View.VISIBLE);
        text_login.setText("Đang đăng nhập");
    }
    public void closebuttonactivated(){
        btn_login.setVisibility(View.GONE);
        text_login.setText("Đăng nhập");
    }

        private void init(){
        account = findViewById(R.id.edituser);
        password = findViewById(R.id.editpass);
        checkBox = findViewById(R.id.check_box);
        btn_login = findViewById(R.id.btnlogin);
        text_login = findViewById(R.id.text_login);
        login = findViewById(R.id.relative_login);

    }




    private void nhotaikhoan(String user,String pass,boolean checked){
        SharedPreferences pref = getSharedPreferences("rememberpass.dat",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if(checked) {
            editor.putString("taikhoan", user);
            editor.putString("matkhau",pass);
            editor.putBoolean("check",checked);
        }else{
            editor.clear();
        }
        editor.commit();
    }

    //fill mat khau
    private  void fillnhomatkhau(){
        SharedPreferences pref =getSharedPreferences("rememberpass.dat",MODE_PRIVATE);
        boolean checked =pref.getBoolean("check",false);
        if(checked){
            account.setText(pref.getString("taikhoan",""));
            password.setText(pref.getString("matkhau",""));
            checkBox.setChecked(checked);
        }
    }
}
