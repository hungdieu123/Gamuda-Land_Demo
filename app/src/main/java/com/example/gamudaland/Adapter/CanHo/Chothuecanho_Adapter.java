package com.example.gamudaland.Adapter.CanHo;

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
import com.example.gamudaland.Model.Chothuecanho;
import com.example.gamudaland.Model.Chothuecanho;
import com.example.gamudaland.R;
import com.example.gamudaland.SQLDAO.ChothuecanhoDAO;
import com.example.gamudaland.SQLDAO.ChothuecanhoDAO;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Chothuecanho_Adapter extends RecyclerView.Adapter<Chothuecanho_Adapter.ChothueLoDatHolder> {
    private List<Chothuecanho> chothuecanhoList;
    private List<Chothuecanho> chothuecanhoFilter;
    private Context context;
    AlertDialog alertDialog;
    private Chothuecanho chothuecanho;
    private ChothuecanhoDAO chothuecanhoDAO;




    public Chothuecanho_Adapter(List<Chothuecanho> chothuecanhoList, Context context) {
        this.chothuecanhoList = chothuecanhoList;
        this.context = context;

    }

    @NonNull
    @Override
    public Chothuecanho_Adapter.ChothueLoDatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ChothueLoDatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Chothuecanho_Adapter.ChothueLoDatHolder holder, final int position) {
        chothuecanhoDAO=new ChothuecanhoDAO(context);
        chothuecanho=new Chothuecanho();
        holder.tittile.setText(chothuecanhoList.get(position).getTittle());
        holder.date.setText(chothuecanhoList.get(position).getDate());
        holder.dientich.setText(chothuecanhoList.get(position).getDientich()+"m2");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chothuecanho tinTuc = chothuecanhoList.get(position);
                Intent intent = new Intent(context, WebView_Chothue_Canho.class);
                intent.putExtra("link", tinTuc.link);
                context.startActivity(intent);

            }
        });
        Random r = new Random();
        final int i1 = (r.nextInt(8000) + 65);
        if (chothuecanhoList.get(position).getGia()!=null){
            holder.gia.setText(chothuecanhoList.get(position).getGia()+"$");
        }else {
            holder.gia.setText("Click Để Xem Thêm!");
        }


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


//                        Toast.makeText(context,"Xóa Thành công "+chothuecanhoList.get(position).getTittle(),Toast.LENGTH_LONG).show();
//                        chothuecanhoDAO.delete(chothuecanhoList.get(position).getMachothuecanho());
//                        chothuecanhoList.remove(position);

                        for (int i=0;i<chothuecanhoList.size();i++){
                        chothuecanhoDAO.delete(chothuecanhoList.get(i).getMachothuecanho());
                        chothuecanhoList.remove(i);
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
                View dialog1  = LayoutInflater.from(context).inflate(R.layout.update_chothuelodat,null);
                builder1.setView(dialog1);




                final TextInputEditText edttiitle,edtdate,edtgia,edtdientich,edtlink,edtma;
                final Button ok1,cancel1,btnSET;
                ok1=dialog1.findViewById(R.id.btnok);



                btnSET=dialog1.findViewById(R.id.btnSet);
                edttiitle=dialog1.findViewById(R.id.edttittle);
                edtdate=dialog1.findViewById(R.id.edtdate);
                edtgia=dialog1.findViewById(R.id.edtgia);
                edtdientich=dialog1.findViewById(R.id.edtdientich);

                edtlink=dialog1.findViewById(R.id.edtlink);



                edttiitle.setText(chothuecanhoList.get(position).getTittle());
                edtdate.setText(chothuecanhoList.get(position).getDate());
                edtgia.setText(chothuecanhoList.get(position).getGia());
                edtdientich.setText(chothuecanhoList.get(position).getDientich());

                edtlink.setText(chothuecanhoList.get(position).getLink());




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



                        String tittle = edttiitle.getText().toString().trim();
                        String date  = edtdate.getText().toString().trim();
                        String gia = edtgia.getText().toString().trim();
                        String dientich = edtdientich.getText().toString().trim();
                        String link = edtlink.getText().toString().trim();



                        if (tittle.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Tiêu Đề!",Toast.LENGTH_SHORT).show();
                        }else if (date.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Ngày!",Toast.LENGTH_SHORT).show();
                        }else if (gia.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Giá!",Toast.LENGTH_SHORT).show();
                        }else if (dientich.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Diện TÍch!",Toast.LENGTH_SHORT).show();
                        }else {


                            chothuecanho =new Chothuecanho();

                            chothuecanho.setTittle(edttiitle.getText().toString().trim());
                            chothuecanho.setDate(edtdate.getText().toString().trim());
                            chothuecanho.setGia(edtgia.getText().toString().trim());
                            chothuecanho.setDientich(edtdientich.getText().toString().trim());
                            chothuecanho.setLink(edtlink.getText().toString().trim());
                            chothuecanho.setMachothuecanho(chothuecanhoList.get(position).getMachothuecanho());


                            chothuecanhoDAO = new ChothuecanhoDAO(context);

                            long resurt = chothuecanhoDAO.update(chothuecanho);
                            if(resurt>0){
                                Toast.makeText(context,"Update Thành Công!",Toast.LENGTH_SHORT).show();
                                chothuecanhoList=chothuecanhoDAO.getAll();
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

        holder.xemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chothuecanho tinTuc = chothuecanhoList.get(position);
                Intent intent = new Intent(context, WebView_Chothue_Canho.class);
                intent.putExtra("link", tinTuc.link);
                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return chothuecanhoList.size();
    }

    public class ChothueLoDatHolder extends RecyclerView.ViewHolder {
        TextView tittile,date,gia,dientich;
        Button btnSet;
        ImageView xemthem,update,xoa;
        public ChothueLoDatHolder(@NonNull View itemView) {
            super(itemView);
            tittile=itemView.findViewById(R.id.tvtittle);
            date=itemView.findViewById(R.id.tvdate);
            xemthem=itemView.findViewById(R.id.ivxemthem);
            update=itemView.findViewById(R.id.ivupdate);
            xoa=itemView.findViewById(R.id.ivxoa);
            gia=itemView.findViewById(R.id.tvgia);
            btnSet=itemView.findViewById(R.id.btnSet);
            dientich=itemView.findViewById(R.id.tvdientichlodat);


        }
    }


    public Filter getFilter() {
        return filter;
    }

    private Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Chothuecanho> filteredlist=new ArrayList<>();

            if(constraint ==null || constraint.length()==0){
                chothuecanhoDAO=new ChothuecanhoDAO(context);
                chothuecanhoList=chothuecanhoDAO.getAll();
                filteredlist.addAll(chothuecanhoList);
            }else {
                String filterPattern=constraint.toString().toLowerCase().trim();
                chothuecanhoDAO=new ChothuecanhoDAO(context);
                chothuecanhoList=chothuecanhoDAO.getAll();
                for (Chothuecanho item: chothuecanhoList){
                    if (item.tittle.toLowerCase().contains(filterPattern)|| item.date.toLowerCase().contains(filterPattern)){
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
            chothuecanhoList.clear();
            chothuecanhoList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
