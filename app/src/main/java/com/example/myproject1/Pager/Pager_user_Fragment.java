package com.example.myproject1.Pager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myproject1.Pager.Adapter.UserAdapter;
import com.example.myproject1.R;
import com.google.android.material.tabs.TabLayout;

public class Pager_user_Fragment extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;
    private int[] tabIcons = {
            R.drawable.ic_person_black_24dp,
            R.drawable.ic_baseline,
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pager_user_fragment,container,false);
        viewPager = v.findViewById(R.id.pager);
        tabLayout = v.findViewById(R.id.tab_layout);

        UserAdapter adapter = new UserAdapter(getActivity().getSupportFragmentManager(),0);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();

        return v;
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }
}
