package com.example.gamudaland.Activity.Can_Ho;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.gamudaland.R;
import com.example.gamudaland.Adapter.MyAdapter;
import com.google.android.material.tabs.TabLayout;

public class CanHo_Fragment extends Fragment {

    private ViewPager pager;

    public CanHo_Fragment() {

    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        pager=view.findViewById(R.id.viewpager_CanHo);

        addTab(pager);

        ((TabLayout)view.findViewById(R.id.tabLayout_canHo)).setupWithViewPager(pager);
        return view;
    }
    public void addTab(ViewPager viewPager){
        MyAdapter adapter;
        adapter = new MyAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new MuaBanCanHo_Fragment(),"Mua Bán");
        adapter.addFrag(new ChothueCanHo_Fragment(),"Cho Thuê");
        viewPager.setAdapter(adapter);//set adapter
    }
}