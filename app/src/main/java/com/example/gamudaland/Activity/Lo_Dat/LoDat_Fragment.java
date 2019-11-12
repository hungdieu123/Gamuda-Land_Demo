package com.example.gamudaland.Activity.Lo_Dat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.gamudaland.Adapter.PagerAdapter.LoDat_PagerAdapter;
import com.example.gamudaland.R;
import com.google.android.material.tabs.TabLayout;

public class LoDat_Fragment extends Fragment {


    private ViewPager pager;

    public LoDat_Fragment() {

    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        pager=view.findViewById(R.id.viewpager);

        addTab(pager);

        ((TabLayout)view.findViewById(R.id.tablayout)).setupWithViewPager(pager);
        return view;
    }
    public void addTab(ViewPager viewPager){
        LoDat_PagerAdapter adapter=new LoDat_PagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag("Mua Bán");
        adapter.addFrag("Cho Thuê");
        viewPager.setAdapter(adapter);
    }

}