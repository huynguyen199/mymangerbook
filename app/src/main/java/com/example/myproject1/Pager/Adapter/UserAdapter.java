package com.example.myproject1.Pager.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myproject1.Fragment.ManagerUserFragment;
import com.example.myproject1.Fragment.RegisterUserFragment;

public class UserAdapter extends FragmentStatePagerAdapter {

    public UserAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position){
            case 0: frag = new ManagerUserFragment();break;
            case 1: frag = new RegisterUserFragment();break;


        }
        return frag;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title ="";
        switch (position){
            case 0:title= "người dùng";break;
            case 1:title= "Đăng ký người dùng";break;
        }
        return title;
    }
}
