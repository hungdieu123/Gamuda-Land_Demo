package com.example.gamudaland.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.gamudaland.Activity.Can_Ho.ChothueCanHo_Fragment;
import com.example.gamudaland.Activity.Can_Ho.MuaBanCanHo_Fragment;
import com.example.gamudaland.Activity.Lo_Dat.ChothueLodat_Fragment;
import com.example.gamudaland.Activity.Lo_Dat.MuaBanLoDat_Fragment;

import java.util.ArrayList;
import java.util.List;

public class LoDat_PagerAdapter extends FragmentStatePagerAdapter {
    private final List<String> listTitle=new ArrayList<>();
    public LoDat_PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment frag=null;
        switch (i){
            case 0:
                frag=new MuaBanLoDat_Fragment();
                break;
            case 1:
                frag=new ChothueLodat_Fragment();
                break;

        }
        return frag;
    }
    public void addFrag(String title){


        listTitle.add(title);

    }

    @Override
    public int getCount() {
        return 2;
    }
    public CharSequence getPageTitle(int position) {

        return listTitle.get(position);
    }
}
