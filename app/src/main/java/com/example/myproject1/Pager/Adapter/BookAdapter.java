package com.example.myproject1.Pager.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.myproject1.Fragment.BookFragment;
import com.example.myproject1.Fragment.CategoryFragment;

public class BookAdapter extends FragmentStatePagerAdapter {
    public BookAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position){
            case 0: frag = new CategoryFragment();break;
            case 1: frag = new BookFragment(); break;


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
            case 0:title= "Thể loại sách";break;
            case 1:title= "Sách";break;
        }
        return title;
    }
}
