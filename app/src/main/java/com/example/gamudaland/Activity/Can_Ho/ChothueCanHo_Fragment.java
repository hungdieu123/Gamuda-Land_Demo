package com.example.gamudaland.Activity.Can_Ho;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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

import com.example.gamudaland.Adapter.CanHo.Chothuecanho_Adapter;
import com.example.gamudaland.Model.Chothuecanho;
import com.example.gamudaland.R;
import com.example.gamudaland.SQLDAO.ChothuecanhoDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ChothueCanHo_Fragment extends Fragment {
    private ChothuecanhoDAO muabancanhoDAO;
    private RecyclerView recyclerView;
    AlertDialog alertDialog;
    private Chothuecanho_Adapter muabanLoDat_adapter;
    private List<Chothuecanho> muabancanhos;
    FloatingActionButton floatingActionButton;
    private Chothuecanho muabancanho;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chothue_canho_fragment, container, false);

        recyclerView = view.findViewById(R.id.chothuecanho_rcview);
        floatingActionButton = view.findViewById(R.id.floatingActionButton3);

        muabancanho = new Chothuecanho();

        muabancanhoDAO = new ChothuecanhoDAO(getActivity());
        muabancanhos = muabancanhoDAO.getAll();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                View dialog1 = LayoutInflater.from(getActivity()).inflate(R.layout.update_chothuelodat, null);
                builder1.setView(dialog1);

                final TextInputEditText edttiitle, edtdate, edtgia, edtdientich, edtlink, edtma;
                final Button ok1, cancel1, btnSET;

                ok1 = dialog1.findViewById(R.id.btnok);
                btnSET = dialog1.findViewById(R.id.btnSet);
                edttiitle = dialog1.findViewById(R.id.edttittle);
                edtdate = dialog1.findViewById(R.id.edtdate);
                edtgia = dialog1.findViewById(R.id.edtgia);
                edtdientich = dialog1.findViewById(R.id.edtdientich);
                edtlink = dialog1.findViewById(R.id.edtlink);
                btnSET.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance(); //khoi tao
                        int nam = calendar.get(Calendar.YEAR);  //thiet lap ngay thang nam
                        int thang = calendar.get(Calendar.MONTH);  //thiet lap ngay thang nam
                        int ngay = calendar.get(Calendar.DAY_OF_MONTH);  //thiet lap ngay thang nam
                        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edtdate.setText(view.getDayOfMonth() + "/" + (view.getMonth() + 1) + "/" + view.getYear());

                            }
                        }, nam, thang, ngay);
                        dialog.show();
                    }
                });

                ok1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String tittle = edttiitle.getText().toString().trim();
                        String date = edtdate.getText().toString().trim();
                        String gia = edtgia.getText().toString().trim();
                        String dientich = edtdientich.getText().toString().trim();
                        String link = edtlink.getText().toString().trim();

                        if (tittle.equals("")) {
                            Toast.makeText(getActivity(), "Vui Lòng Không Để Trống Tiêu Đề!", Toast.LENGTH_SHORT).show();
                        } else if (date.equals("")) {
                            Toast.makeText(getActivity(), "Vui Lòng Không Để Trống Ngày!", Toast.LENGTH_SHORT).show();
                        } else if (gia.equals("")) {
                            Toast.makeText(getActivity(), "Vui Lòng Không Để Trống Giá!", Toast.LENGTH_SHORT).show();
                        } else if (dientich.equals("")) {
                            Toast.makeText(getActivity(), "Vui Lòng Không Để Trống Diện TÍch!", Toast.LENGTH_SHORT).show();
                        } else if (link.equals("")) {
                            Toast.makeText(getActivity(), "Vui Lòng Không Để Trống Link!", Toast.LENGTH_SHORT).show();
                        } else {
                            Random r = new Random();
                            int i1 = (r.nextInt(8000) + 65);

                            muabancanho = new Chothuecanho();

                            muabancanho.setTittle(edttiitle.getText().toString().trim());
                            muabancanho.setDate(edtdate.getText().toString().trim());
                            muabancanho.setGia(edtgia.getText().toString().trim());
                            muabancanho.setDientich(edtdientich.getText().toString().trim());
                            muabancanho.setLink(edtlink.getText().toString().trim());
                            muabancanho.setMachothuecanho(String.valueOf(i1));

                            muabancanhoDAO = new ChothuecanhoDAO(getActivity());

                            long resurt = muabancanhoDAO.insert(muabancanho);
                            if (resurt > 0) {
                                Toast.makeText(getActivity(), "Thêm Thành Công!", Toast.LENGTH_SHORT).show();

                                muabancanhos = muabancanhoDAO.getAll();
                                muabanLoDat_adapter = new Chothuecanho_Adapter(muabancanhos, getActivity());
                                StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(gridLayoutManager);
                                recyclerView.setAdapter(muabanLoDat_adapter);

                                alertDialog.dismiss();


                            } else {
                                Toast.makeText(getActivity(), "Xóa Thất Bại!", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();


                            }

                        }
                    }
                });

                builder1.create();
                alertDialog = builder1.show();
            }
        });


        muabanLoDat_adapter = new Chothuecanho_Adapter(muabancanhos, getActivity());
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(muabanLoDat_adapter);

        setHasOptionsMenu(true);


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