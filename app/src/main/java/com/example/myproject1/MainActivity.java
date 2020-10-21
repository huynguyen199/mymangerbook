package com.example.myproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject1.Dao.Dao_User;
import com.example.myproject1.Fragment.MoneyFragment;
import com.example.myproject1.Fragment.Payfragment;
import com.example.myproject1.Fragment.StatisticalFragment;
import com.example.myproject1.Pager.Pager_book_Fragment;
import com.example.myproject1.Pager.Pager_hoadon_Fragment;
import com.example.myproject1.Pager.Pager_user_Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Toolbar toolbar = findViewById(R.id.mytoolbar);

        setSupportActionBar(toolbar);

        final FrameLayout frame = findViewById(R.id.frameContainer);
        drawer = findViewById(R.id.drawer_layout);


        Dao_User dao_user = new Dao_User(this);
        dao_user.readnameuser();

        NavigationView navigationView = findViewById(R.id.nav_view);

        final TextView yourname = navigationView.getHeaderView(0).findViewById(R.id.yourname);

        final TextView yourphone = navigationView.getHeaderView(0).findViewById(R.id.yourphone);
        if(LoginActivity.USER!=null) {
            Log.d("data", LoginActivity.USER.getName());
            Log.d("data", LoginActivity.USER.getNumberphone());

            yourname.setText(LoginActivity.USER.getName());
            yourphone.setText(LoginActivity.USER.getNumberphone());
        }



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameContainer,new Pager_book_Fragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_sach);
        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){

                    case R.id.nav_sach:
                        fragment = new Pager_book_Fragment();
                        Toast.makeText(MainActivity.this,"the loai",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_hoadon:
                        fragment = new Pager_hoadon_Fragment();
                        Toast.makeText(MainActivity.this,"Bill",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_manager_user:
                        fragment = new Pager_user_Fragment();
                        break;
                    case R.id.nav_thanhtoan:
                        fragment = new Payfragment();
                        break;
                    case R.id.nav_banchay:
                        fragment = new StatisticalFragment();
                        break;
                    case R.id.nav_thongke:
                        fragment = new MoneyFragment();
                        break;
                    case R.id.nav_thoat:
                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        break;
                }
                if(fragment!=null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameContainer, fragment).commit();
                    drawer.setSelected(true);
                    drawer.closeDrawer(GravityCompat.START);
                }
                    return true;
            }
        });

    }

}

