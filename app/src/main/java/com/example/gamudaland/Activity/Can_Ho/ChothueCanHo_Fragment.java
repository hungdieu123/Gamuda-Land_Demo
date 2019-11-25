package com.example.gamudaland.Activity.Can_Ho;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gamudaland.R;

public class ChothueCanHo_Fragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chothue_canho_fragment, container, false);

        return view;
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();

        inflater.inflate(R.menu.home, menu);

        MenuItem searchItem=menu.findItem(R.id.app_bar_search);
        SearchView searchView=(SearchView) searchItem.getActionView();



        searchView.setQueryHint("Search....");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
//                chothueLoDat_adapter.getFilter().filter(s);
                return false;
            }
        });
    }
}
