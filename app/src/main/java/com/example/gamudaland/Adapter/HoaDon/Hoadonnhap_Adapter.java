package com.example.gamudaland.Adapter.HoaDon;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.gamudaland.Activity.Lo_Dat.WebView_Chothue_Canho;
import com.example.gamudaland.Model.Hoadonnhap;
import com.example.gamudaland.R;
import com.example.gamudaland.SQLDAO.HoadonnhapDAO;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Hoadonnhap_Adapter extends RecyclerView.Adapter<Hoadonnhap_Adapter.ChothueLoDatHolder> {
    private List<Hoadonnhap> hoadonnhapList;
    private Context context;
    AlertDialog alertDialog;
    private Hoadonnhap hoadonnhap;
    private HoadonnhapDAO hoadonnhapDAO;




    public Hoadonnhap_Adapter(List<Hoadonnhap> hoadonnhapList, Context context) {
        this.hoadonnhapList = hoadonnhapList;
        this.context = context;

    }

    @NonNull
    @Override
    public Hoadonnhap_Adapter.ChothueLoDatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoadonnhap,parent,false);
        return new ChothueLoDatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Hoadonnhap_Adapter.ChothueLoDatHolder holder, final int position) {
        hoadonnhapDAO=new HoadonnhapDAO(context);
        hoadonnhap=new Hoadonnhap();



        holder.tieude.setText(hoadonnhapList.get(position).getTieudenhap());
        holder.theloai.setText("Thể Loại: "+hoadonnhapList.get(position).getTheloainhap());
        holder.date.setText(hoadonnhapList.get(position).getDatenhap());
        holder.dientich.setText(hoadonnhapList.get(position).getDientichnhap()+"m2");
        holder.gia.setText("Giá: "+hoadonnhapList.get(position).getGianhap()+"$");



        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Button ok,cancel;

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final View dialog  = LayoutInflater.from(context).inflate(R.layout.xoa_dialog,null);
                builder.setView(dialog);

                ok=dialog.findViewById(R.id.btnallow);
                cancel=dialog.findViewById(R.id.btncancel);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        hoadonnhapDAO.delete(hoadonnhapList.get(position).getMahoadonnhap());
                        hoadonnhapList.remove(position);

                        notifyDataSetChanged();
                        alertDialog.dismiss();


                    }
                });

                builder.create();
                alertDialog=builder.show();

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                View dialog1  = LayoutInflater.from(context).inflate(R.layout.update_hoadonnhap,null);
                builder1.setView(dialog1);

                final TextInputEditText edttieude,edtdate,edtgia,edtdientich,edttheloai;
                final Button ok1,cancel1,btnSET;

                edttieude=dialog1.findViewById(R.id.edttieude);
                edtdate=dialog1.findViewById(R.id.edtdatehoadon);
                edtgia=dialog1.findViewById(R.id.edtgiahoadon);
                edtdientich=dialog1.findViewById(R.id.edtdientichhoadon);
                edttheloai=dialog1.findViewById(R.id.edttheloai);

                ok1=dialog1.findViewById(R.id.btnok);
                btnSET=dialog1.findViewById(R.id.btnSet);
                cancel1=dialog1.findViewById(R.id.btncancelhoadon);

                edttieude.setText(hoadonnhapList.get(position).getTieudenhap());
                edttheloai.setText(hoadonnhapList.get(position).getTheloainhap());
                edtdate.setText(hoadonnhapList.get(position).getDatenhap());
                edtdientich.setText(hoadonnhapList.get(position).getDientichnhap());
                edtgia.setText(hoadonnhapList.get(position).getGianhap());

                cancel1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });




                btnSET.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar=Calendar.getInstance(); //khoi tao
                        int nam=calendar.get(Calendar.YEAR);  //thiet lap ngay thang nam
                        int thang=calendar.get(Calendar.MONTH);  //thiet lap ngay thang nam
                        int ngay=calendar.get(Calendar.DAY_OF_MONTH);  //thiet lap ngay thang nam
                        DatePickerDialog dialog=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
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

                        String tieude = edttieude.getText().toString().trim();
                        String date  = edtdate.getText().toString().trim();
                        String gia = edtgia.getText().toString().trim();
                        String dientich = edtdientich.getText().toString().trim();
                        String theloai = edttheloai.getText().toString().trim();


                        if (tieude.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Tiêu Đề!",Toast.LENGTH_SHORT).show();
                        }else if (date.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Ngày!",Toast.LENGTH_SHORT).show();
                        }else if (gia.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Giá!",Toast.LENGTH_SHORT).show();
                        }else if (dientich.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Diện TÍch!",Toast.LENGTH_SHORT).show();
                        }else if (theloai.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Thể Loại!",Toast.LENGTH_SHORT).show();
                        }else {
                            Random r = new Random();
                            int i1 = (r.nextInt(8000) + 65);

                            hoadonnhap =new Hoadonnhap();

                            hoadonnhap.setDientichnhap(edtdientich.getText().toString().trim());
                            hoadonnhap.setMahoadonnhap(hoadonnhapList.get(position).getMahoadonnhap());
                            hoadonnhap.setGianhap(edtgia.getText().toString().trim());
                            hoadonnhap.setDatenhap(edtdate.getText().toString().trim());
                            hoadonnhap.setTheloainhap(edttheloai.getText().toString().trim());
                            hoadonnhap.setTieudenhap(edttieude.getText().toString().trim());

                            hoadonnhapDAO = new HoadonnhapDAO(context);

                            long resurt = hoadonnhapDAO.update(hoadonnhap);
                            if(resurt>0){
                                Toast.makeText(context,"Update Thành Công!",Toast.LENGTH_SHORT).show();
                                hoadonnhapDAO = new HoadonnhapDAO(context);
                                hoadonnhapList=hoadonnhapDAO.getAll();
                                notifyDataSetChanged();
                                alertDialog.dismiss();


                            }else {
                                Toast.makeText(context,"Update Thất Bại!",Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();


                            }

                        }
                    }
                });

                builder1.create();
                alertDialog=builder1.show();
            }
        });
            }


    @Override
    public int getItemCount() {
        return hoadonnhapList.size();
    }

    public class ChothueLoDatHolder extends RecyclerView.ViewHolder {
        TextView tieude,theloai,gia,dientich,date;

        ImageView xemthem,update,xoa;
        public ChothueLoDatHolder(@NonNull View itemView) {
            super(itemView);
            tieude=itemView.findViewById(R.id.tvtieude);
            theloai=itemView.findViewById(R.id.tvtheloai);
            gia=itemView.findViewById(R.id.tvgia);
            dientich=itemView.findViewById(R.id.tvdientich);
            date=itemView.findViewById(R.id.tvngay);
            
            xemthem=itemView.findViewById(R.id.ivxemthem);
            update=itemView.findViewById(R.id.ivupdate);
            xoa=itemView.findViewById(R.id.ivxoa);


        }
    }


    public Filter getFilter() {
        return qlhoadonnhapFilter;
    }

    private Filter qlhoadonnhapFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Hoadonnhap> filteredlist=new ArrayList<>();

            if(constraint ==null || constraint.length()==0){
                hoadonnhapDAO=new HoadonnhapDAO(context);
                hoadonnhapList=hoadonnhapDAO.getAll();
                filteredlist.addAll(hoadonnhapList);
            }else {
                String filterPattern=constraint.toString().toLowerCase().trim();
                hoadonnhapDAO=new HoadonnhapDAO(context);
                hoadonnhapList=hoadonnhapDAO.getAll();
                for (Hoadonnhap item: hoadonnhapList){
                    if (item.tieudenhap.toLowerCase().contains(filterPattern)|| item.datenhap.toLowerCase().contains(filterPattern)){
                        filteredlist.add(item);
                    }
                }


            }

            FilterResults results=new FilterResults();
            results.values=filteredlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            hoadonnhapList.clear();
            hoadonnhapList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
