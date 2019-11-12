package com.example.gamudaland.Activity.Can_Ho;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.gamudaland.Adapter.PagerAdapter.CanHo_PagerAdapter;
import com.example.gamudaland.R;
import com.google.android.material.tabs.TabLayout;

public class CanHo_Fragment extends Fragment {

    private ViewPager viewpager;


    public CanHo_Fragment() {

    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        viewpager=view.findViewById(R.id.viewpager_CanHo);

        addTab2(viewpager);
        ((TabLayout)view.findViewById(R.id.tabLayout_canHo)).setupWithViewPager(viewpager);
        return view;
    }
    public void addTab2(ViewPager viewPager){

        CanHo_PagerAdapter adapter=new CanHo_PagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag("Mua Bán");
        adapter.addFrag("Cho Thuê");
        viewPager.setAdapter(adapter);

    }
}