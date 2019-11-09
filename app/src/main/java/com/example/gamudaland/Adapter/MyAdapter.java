package com.example.gamudaland.Adapter;



import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends FragmentPagerAdapter {
    private final List<Fragment> listFrag=new ArrayList<>();
    private  final List<String> listTitle=new ArrayList<>();
    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return listFrag.get(position);
    }

    @Override
    public int getCount() {
        return listFrag.size();
    }
    public void addFrag(Fragment fragment,String title){

        listFrag.add(fragment);
        listTitle.add(title);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }

}
