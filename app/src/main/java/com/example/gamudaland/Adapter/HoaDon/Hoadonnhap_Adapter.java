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
    private List<Hoadonnhap> chothuelodatList;
    private List<Hoadonnhap> chothuelodatFilter;
    private Context context;
    AlertDialog alertDialog;
    private Hoadonnhap chothuelodat;
    private HoadonnhapDAO chothuelodatDAO;




    public Hoadonnhap_Adapter(List<Hoadonnhap> chothuelodatList, Context context) {
        this.chothuelodatList = chothuelodatList;
        this.context = context;

    }

    @NonNull
    @Override
    public Hoadonnhap_Adapter.ChothueLoDatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ChothueLoDatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Hoadonnhap_Adapter.ChothueLoDatHolder holder, final int position) {
        chothuelodatDAO=new HoadonnhapDAO(context);
        chothuelodat=new Hoadonnhap();



        Random r = new Random();



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


//                        Toast.makeText(context,"Xóa Thành công "+chothuelodatList.get(position).getTittle(),Toast.LENGTH_LONG).show();
//                        chothuelodatDAO.delete(chothuelodatList.get(position).getMachothuelodat());
//                        chothuelodatList.remove(position);

                        for (int i=0;i<chothuelodatList.size();i++){
                        chothuelodatDAO.delete(chothuelodatList.get(i).getMahoadonnhap());
                        chothuelodatList.remove(i);
                        }
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




                final TextInputEditText edttieude,edttheloai,edtdate,edtgia,edtdientich;
                final Button ok1,cancel1,btnSET;
                ok1=dialog1.findViewById(R.id.btnok);



                btnSET=dialog1.findViewById(R.id.btnSet);
                edttieude=dialog1.findViewById(R.id.edttieude);
                edtdate=dialog1.findViewById(R.id.edtdate);
                edtgia=dialog1.findViewById(R.id.edtgia);
                edttheloai=dialog1.findViewById(R.id.edttheloai);
                edtdientich=dialog1.findViewById(R.id.edtdientich);

                



                edttieude.setText(chothuelodatList.get(position).getTieudenhap());
                edtdate.setText(chothuelodatList.get(position).getDatenhap());
                edtgia.setText(chothuelodatList.get(position).getGianhap());
                edtdientich.setText(chothuelodatList.get(position).getDientichnhap());
                edttheloai.setText(chothuelodatList.get(position).getTheloainhap());




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



                        String tittle = edttieude.getText().toString().trim();
                        String date  = edtdate.getText().toString().trim();
                        String gia = edtgia.getText().toString().trim();
                        String dientich = edtdientich.getText().toString().trim();




                        if (tittle.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Tiêu Đề!",Toast.LENGTH_SHORT).show();
                        }else if (date.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Ngày!",Toast.LENGTH_SHORT).show();
                        }else if (gia.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Giá!",Toast.LENGTH_SHORT).show();
                        }else if (dientich.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Diện TÍch!",Toast.LENGTH_SHORT).show();
                        }else if (edttheloai.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Link!",Toast.LENGTH_SHORT).show();
                        }else {


                            chothuelodat =new Hoadonnhap();

                            chothuelodat.setTieudenhap(edttieude.getText().toString().trim());
                            chothuelodat.setTheloainhap(edtdate.getText().toString().trim());
                            chothuelodat.setGianhap(edtgia.getText().toString().trim());
                            chothuelodat.setDientichnhap(edtdientich.getText().toString().trim());
                            chothuelodat.setDatenhap(edtdate.getText().toString().trim());
                            chothuelodat.setMahoadonnhap(chothuelodatList.get(position).getMahoadonnhap());


                            chothuelodatDAO = new HoadonnhapDAO(context);

                            long resurt = chothuelodatDAO.update(chothuelodat);
                            if(resurt>0){
                                Toast.makeText(context,"Update Thành Công!",Toast.LENGTH_SHORT).show();
                                chothuelodatList=chothuelodatDAO.getAll();
                                notifyDataSetChanged();

                                alertDialog.dismiss();


                            }else {
                                Toast.makeText(context,"Update Thất Bại!",Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                                notifyDataSetChanged();

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
        return chothuelodatList.size();
    }

    public class ChothueLoDatHolder extends RecyclerView.ViewHolder {
        TextView tieude,theloai,gia,dientich,date;
        Button btnSet;
        ImageView xemthem,update,xoa;
        public ChothueLoDatHolder(@NonNull View itemView) {
            super(itemView);
            tieude=itemView.findViewById(R.id.tvtittle);
            theloai=itemView.findViewById(R.id.tvdate);
            gia=itemView.findViewById(R.id.tvdate);
            dientich=itemView.findViewById(R.id.tvdate);
            date=itemView.findViewById(R.id.tvdate);
            
            xemthem=itemView.findViewById(R.id.ivxemthem);
            update=itemView.findViewById(R.id.ivupdate);
            xoa=itemView.findViewById(R.id.ivxoa);
            btnSet=itemView.findViewById(R.id.btnSet);


        }
    }


    public Filter getFilter() {
        return qLnguoidungFilter;
    }

    private Filter qLnguoidungFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Hoadonnhap> filteredlist=new ArrayList<>();

            if(constraint ==null || constraint.length()==0){
                chothuelodatDAO=new HoadonnhapDAO(context);
                chothuelodatList=chothuelodatDAO.getAll();
                filteredlist.addAll(chothuelodatList);
            }else {
                String filterPattern=constraint.toString().toLowerCase().trim();
                chothuelodatDAO=new HoadonnhapDAO(context);
                chothuelodatList=chothuelodatDAO.getAll();
                for (Hoadonnhap item: chothuelodatList){
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
            chothuelodatList.clear();
            chothuelodatList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
