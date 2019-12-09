package com.example.gamudaland.Activity;

import android.os.Bundle;

import com.example.gamudaland.Adapter.LoDat.Chothuelodat_Adapter;
import com.example.gamudaland.Model.Chothuelodat;
import com.example.gamudaland.Model.DataBase;
import com.example.gamudaland.R;
import com.example.gamudaland.SQLDAO.ChothuelodatDAO;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Chothuelodat_Adapter chothueLoDat_adapter;

    List<Chothuelodat> chothuelodatList;
    ChothuelodatDAO chothuelodatDAO;



    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        DataBase dataBase = new DataBase(this);
        dataBase.createDataBase();
        chothuelodatDAO=new ChothuelodatDAO(this);
        chothuelodatList=chothuelodatDAO.getAll();
        chothueLoDat_adapter=new Chothuelodat_Adapter(chothuelodatList,this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery,R.id.nav_hoadon, R.id.nav_slideshow,
                R.id.nav_tools)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //

    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
