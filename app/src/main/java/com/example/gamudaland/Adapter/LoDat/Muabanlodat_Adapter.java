package com.example.gamudaland.Adapter.LoDat;

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
import com.example.gamudaland.Model.Muabanlodat;
import com.example.gamudaland.R;
import com.example.gamudaland.SQLDAO.MuabanlodatDAO;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Muabanlodat_Adapter extends RecyclerView.Adapter<Muabanlodat_Adapter.MuabanLoDatHolder> {
    private List<Muabanlodat> muabanlodatList;
    private List<Muabanlodat> muabanlodatFilter;
    private Context context;
    AlertDialog alertDialog;
    private Muabanlodat muabanlodat;
    private MuabanlodatDAO muabanlodatDAO;




    public Muabanlodat_Adapter(List<Muabanlodat> muabanlodatList, Context context) {
        this.muabanlodatList = muabanlodatList;
        this.context = context;

    }

    @NonNull
    @Override
    public Muabanlodat_Adapter.MuabanLoDatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MuabanLoDatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Muabanlodat_Adapter.MuabanLoDatHolder holder, final int position) {
        muabanlodatDAO=new MuabanlodatDAO(context);
        muabanlodat=new Muabanlodat();
        Random r = new Random();
        final int i1 = (r.nextInt(8000) + 65);
        if (muabanlodatList.get(position).getGia()!=null){
            holder.gia.setText(muabanlodatList.get(position).getGia()+"$");
        }else {
            holder.gia.setText("Click Để Xem Thêm!");
        }
        holder.tittile.setText(muabanlodatList.get(position).getTittle());
        holder.date.setText(muabanlodatList.get(position).getDate());
        holder.dientich.setText(muabanlodatList.get(position).getDientich()+"m2");



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Muabanlodat tinTuc = muabanlodatList.get(position);
                Intent intent = new Intent(context, WebView_Chothue_Canho.class);
                intent.putExtra("link", tinTuc.link);
                if (muabanlodatList.get(position).getLink().equals("")){

                }else {
                    context.startActivity(intent);
                }


            }
        });



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


                        Toast.makeText(context,"Xóa Thành công "+muabanlodatList.get(position).getTittle(),Toast.LENGTH_LONG).show();
                        muabanlodatDAO.delete(muabanlodatList.get(position).getMamuabanlodat());
                        muabanlodatList.remove(position);
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
                cancel1=dialog1.findViewById(R.id.btncancellodat);

                btnSET=dialog1.findViewById(R.id.btnSet);
                edttiitle=dialog1.findViewById(R.id.edttittle);
                edtdate=dialog1.findViewById(R.id.edtdate);
                edtgia=dialog1.findViewById(R.id.edtgia);
                edtdientich=dialog1.findViewById(R.id.edtdientich);

                edtlink=dialog1.findViewById(R.id.edtlink);



                edttiitle.setText(muabanlodatList.get(position).getTittle());
                edtdate.setText(muabanlodatList.get(position).getDate());
                edtgia.setText(muabanlodatList.get(position).getGia());
                edtdientich.setText(muabanlodatList.get(position).getDientich());

                edtlink.setText(muabanlodatList.get(position).getLink());



                cancel1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        notifyDataSetChanged();

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


                            muabanlodat =new Muabanlodat();

                            muabanlodat.setTittle(edttiitle.getText().toString().trim());
                            muabanlodat.setDate(edtdate.getText().toString().trim());
                            muabanlodat.setGia(edtgia.getText().toString().trim());
                            muabanlodat.setDientich(edtdientich.getText().toString().trim());
                            muabanlodat.setLink(edtlink.getText().toString().trim());
                            muabanlodat.setMamuabanlodat(muabanlodatList.get(position).getMamuabanlodat());


                            muabanlodatDAO = new MuabanlodatDAO(context);

                            long resurt = muabanlodatDAO.update(muabanlodat);
                            if(resurt>0){
                                Toast.makeText(context,"Update Thành Công!",Toast.LENGTH_SHORT).show();
                                muabanlodatList=muabanlodatDAO.getAll();
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
                Muabanlodat tinTuc = muabanlodatList.get(position);
                Intent intent = new Intent(context, WebView_Chothue_Canho.class);
                intent.putExtra("link", tinTuc.link);
                if (muabanlodatList.get(position).getLink().equals("")){
                    Toast.makeText(context,"Vui lòng cập nhập đường dẫn để xem chi tiết!",Toast.LENGTH_LONG).show();
                }else {
                    context.startActivity(intent);
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return muabanlodatList.size();
    }

    public class MuabanLoDatHolder extends RecyclerView.ViewHolder {
        TextView tittile,date,gia,dientich;
        Button btnSet;
        ImageView xemthem,update,xoa;
        public MuabanLoDatHolder(@NonNull View itemView) {
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
        return qLnguoidungFilter;
    }

    private Filter qLnguoidungFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Muabanlodat> filteredlist=new ArrayList<>();

            if(constraint ==null || constraint.length()==0){
                muabanlodatDAO=new MuabanlodatDAO(context);
                muabanlodatList=muabanlodatDAO.getAll();
                filteredlist.addAll(muabanlodatList);
            }else {
                String filterPattern=constraint.toString().toLowerCase().trim();
                muabanlodatDAO=new MuabanlodatDAO(context);
                muabanlodatList=muabanlodatDAO.getAll();
                for (Muabanlodat item: muabanlodatList){
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
            muabanlodatList.clear();
            muabanlodatList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
