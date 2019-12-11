package com.example.gamudaland.Activity.Hoa_Don;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.gamudaland.Adapter.HoaDon.Hoadonxuat_Adapter;
import com.example.gamudaland.Adapter.LoDat.Muabanlodat_Adapter;
import com.example.gamudaland.Model.Hoadonxuat;
import com.example.gamudaland.Model.Muabanlodat;
import com.example.gamudaland.R;
import com.example.gamudaland.SQLDAO.HoadonxuatDAO;
import com.example.gamudaland.SQLDAO.MuabanlodatDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Hoadatxuat_Fragment extends Fragment {
    private HoadonxuatDAO muabanlodatDAO;
    private RecyclerView recyclerView;
    AlertDialog alertDialog;
    private Hoadonxuat_Adapter muabanLoDat_adapter;
    private List<Hoadonxuat> hoadonxuats;
    FloatingActionButton floatingActionButton;
    private Hoadonxuat hoadonxuat;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoadonxuat, container, false);
        recyclerView=view.findViewById(R.id.hoadonxuat_rcview);
        floatingActionButton=view.findViewById(R.id.fb_hoadonxuat);

        hoadonxuat=new Hoadonxuat();


        muabanlodatDAO=new HoadonxuatDAO(getActivity());
        hoadonxuats=muabanlodatDAO.getAll();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                View dialog1  = LayoutInflater.from(getActivity()).inflate(R.layout.update_hoadonnhap,null);
                builder1.setView(dialog1);

                final TextInputEditText edttieude,edtdate,edtgia,edtdientich,edttheloai;
                final Button ok1,cancel1,btnSET;

                ok1=dialog1.findViewById(R.id.btnok);
                btnSET=dialog1.findViewById(R.id.btnSet);



                edttieude=dialog1.findViewById(R.id.edttieude);
                edtdate=dialog1.findViewById(R.id.edtdatehoadon);
                edtgia=dialog1.findViewById(R.id.edtgiahoadon);
                edtdientich=dialog1.findViewById(R.id.edtdientichhoadon);
                edttheloai=dialog1.findViewById(R.id.edttheloai);


                btnSET.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar=Calendar.getInstance(); //khoi tao
                        int nam=calendar.get(Calendar.YEAR);  //thiet lap ngay thang nam
                        int thang=calendar.get(Calendar.MONTH);  //thiet lap ngay thang nam
                        int ngay=calendar.get(Calendar.DAY_OF_MONTH);  //thiet lap ngay thang nam
                        DatePickerDialog dialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edtdate.setText(view.getDayOfMonth()+"/"+(view.getMonth()+1)+"/"+view.getYear());

                            }
                        },nam,thang,ngay);
                        dialog.show();
                    }
                });

                ok1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String tittle = edttieude.getText().toString().trim();
                        String date  = edtdate.getText().toString().trim();
                        String gia = edtgia.getText().toString().trim();
                        String dientich = edtdientich.getText().toString().trim();
                        String link = edtdientich.getText().toString().trim();

                        if (tittle.equals("")){
                            Toast.makeText(getActivity(),"Vui Lòng Không Để Trống Tiêu Đề!",Toast.LENGTH_SHORT).show();
                        }else if (date.equals("")){
                            Toast.makeText(getActivity(),"Vui Lòng Không Để Trống Ngày!",Toast.LENGTH_SHORT).show();
                        }else if (gia.equals("")){
                            Toast.makeText(getActivity(),"Vui Lòng Không Để Trống Giá!",Toast.LENGTH_SHORT).show();
                        }else if (dientich.equals("")){
                            Toast.makeText(getActivity(),"Vui Lòng Không Để Trống Diện TÍch!",Toast.LENGTH_SHORT).show();
                        }else if (link.equals("")){
                            Toast.makeText(getActivity(),"Vui Lòng Không Để Trống Link!",Toast.LENGTH_SHORT).show();
                        }else {
                            Random r = new Random();
                            int i1 = (r.nextInt(8000) + 65);

                            hoadonxuat =new Hoadonxuat();

                            hoadonxuat.setDientichxuat(edtdientich.getText().toString().trim());
                            hoadonxuat.setMahoadonxuat(String.valueOf(i1));
                            hoadonxuat.setGiaxuat(edtgia.getText().toString().trim());
                            hoadonxuat.setDatexuat(edtdate.getText().toString().trim());
                            hoadonxuat.setTheloaixuat(edttheloai.getText().toString().trim());
                            hoadonxuat.setTieudexuat(edttieude.getText().toString().trim());

                            muabanlodatDAO = new HoadonxuatDAO(getActivity());

                            long resurt = muabanlodatDAO.insert(hoadonxuat);
                            if(resurt>0){
                                Toast.makeText(getActivity(),"Thêm Thành Công!",Toast.LENGTH_SHORT).show();

                                hoadonxuats=muabanlodatDAO.getAll();
                                muabanLoDat_adapter=new Hoadonxuat_Adapter(hoadonxuats,getActivity());
                                StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(gridLayoutManager);
                                recyclerView.setAdapter(muabanLoDat_adapter);

                                alertDialog.dismiss();


                            }else {
                                Toast.makeText(getActivity(),"Xóa Thất Bại!",Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();


                            }

                        }
                    }
                });

                builder1.create();
                alertDialog=builder1.show();
            }
        });


        muabanLoDat_adapter=new Hoadonxuat_Adapter(hoadonxuats,getActivity());
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(muabanLoDat_adapter);

        setHasOptionsMenu(true);
        return view;
    }

    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
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
                muabanLoDat_adapter.getFilter().filter(s);
                return false;
            }
        });
    }
    

}
