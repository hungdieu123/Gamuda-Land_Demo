package com.example.gamudaland.Adapter.HoaDon;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
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

import com.example.gamudaland.Model.Hoadonxuat;
import com.example.gamudaland.R;
import com.example.gamudaland.SQLDAO.HoadonxuatDAO;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Hoadonxuat_Adapter extends RecyclerView.Adapter<Hoadonxuat_Adapter.ChothueLoDatHolder> {
    private List<Hoadonxuat> hoadonxuatList;
    private Context context;
    AlertDialog alertDialog;
    private Hoadonxuat hoadonxuat;
    private HoadonxuatDAO hoadonxuatDAO;




    public Hoadonxuat_Adapter(List<Hoadonxuat> hoadonxuatList, Context context) {
        this.hoadonxuatList = hoadonxuatList;
        this.context = context;

    }

    @NonNull
    @Override
    public Hoadonxuat_Adapter.ChothueLoDatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoadonnhap,parent,false);
        return new ChothueLoDatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Hoadonxuat_Adapter.ChothueLoDatHolder holder, final int position) {
        hoadonxuatDAO=new HoadonxuatDAO(context);
        hoadonxuat=new Hoadonxuat();



        holder.tieude.setText(hoadonxuatList.get(position).getTieudexuat());
        holder.theloai.setText(hoadonxuatList.get(position).getTheloaixuat());
        holder.date.setText(hoadonxuatList.get(position).getDatexuat());
        holder.dientich.setText(hoadonxuatList.get(position).getDientichxuat());
        holder.gia.setText(hoadonxuatList.get(position).getGiaxuat());



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

                        for (int i=0;i<hoadonxuatList.size();i++){
                        hoadonxuatDAO.delete(hoadonxuatList.get(i).getMahoadonxuat());
                        hoadonxuatList.remove(i);
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

                final TextInputEditText edttieude,edtdate,edtgia,edtdientich,edttheloai;
                final Button ok1,cancel1,btnSET;

                edttieude=dialog1.findViewById(R.id.edttieude);
                edtdate=dialog1.findViewById(R.id.edtdatehoadon);
                edtgia=dialog1.findViewById(R.id.edtdatehoadon);
                edtdientich=dialog1.findViewById(R.id.edtdientichhoadon);
                edttheloai=dialog1.findViewById(R.id.edttheloai);
                ok1=dialog1.findViewById(R.id.btnok);
                btnSET=dialog1.findViewById(R.id.btnSet);
                cancel1=dialog1.findViewById(R.id.btncancelhoadon);

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

                            hoadonxuat =new Hoadonxuat();

                            hoadonxuat.setDientichxuat(edtdientich.getText().toString().trim());
                            hoadonxuat.setMahoadonxuat(hoadonxuatList.get(position).getMahoadonxuat());
                            hoadonxuat.setGiaxuat(edtgia.getText().toString().trim());
                            hoadonxuat.setDatexuat(edtdientich.getText().toString().trim());
                            hoadonxuat.setTheloaixuat(edttheloai.getText().toString().trim());
                            hoadonxuat.setTieudexuat(hoadonxuatList.get(position).getMahoadonxuat());

                            hoadonxuatDAO = new HoadonxuatDAO(context);

                            long resurt = hoadonxuatDAO.update(hoadonxuat);
                            if(resurt>0){
                                Toast.makeText(context,"Update Thành Công!",Toast.LENGTH_SHORT).show();

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
        return hoadonxuatList.size();
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
        return qlhoadonxuatFilter;
    }

    private Filter qlhoadonxuatFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Hoadonxuat> filteredlist=new ArrayList<>();

            if(constraint ==null || constraint.length()==0){
                hoadonxuatDAO=new HoadonxuatDAO(context);
                hoadonxuatList=hoadonxuatDAO.getAll();
                filteredlist.addAll(hoadonxuatList);
            }else {
                String filterPattern=constraint.toString().toLowerCase().trim();
                hoadonxuatDAO=new HoadonxuatDAO(context);
                hoadonxuatList=hoadonxuatDAO.getAll();
                for (Hoadonxuat item: hoadonxuatList){
                    if (item.tieudexuat.toLowerCase().contains(filterPattern)|| item.datexuat.toLowerCase().contains(filterPattern)){
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
            hoadonxuatList.clear();
            hoadonxuatList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
