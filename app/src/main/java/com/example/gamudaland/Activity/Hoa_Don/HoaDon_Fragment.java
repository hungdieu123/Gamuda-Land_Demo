package com.example.gamudaland.Activity.Hoa_Don;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.gamudaland.Adapter.PagerAdapter.Hoadon_PagerAdapter;
import com.example.gamudaland.Adapter.PagerAdapter.LoDat_PagerAdapter;
import com.example.gamudaland.R;
import com.google.android.material.tabs.TabLayout;

public class HoaDon_Fragment extends Fragment {


    private ViewPager pager;

    public HoaDon_Fragment() {

    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoadon, container, false);
        pager=view.findViewById(R.id.viewpager_Hoadon);

        addTab(pager);

        ((TabLayout)view.findViewById(R.id.tabLayout_Hoadon)).setupWithViewPager(pager);
        return view;
    }
    public void addTab(ViewPager viewPager){
        Hoadon_PagerAdapter adapter=new Hoadon_PagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag("Mua Bán");
        adapter.addFrag("Cho Thuê");
        viewPager.setAdapter(adapter);
    }


    }

