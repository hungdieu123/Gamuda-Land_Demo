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
import com.example.gamudaland.Model.Chothuelodat;
import com.example.gamudaland.R;
import com.example.gamudaland.SQLDAO.ChothuelodatDAO;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ChothueLoDat_Adapter extends RecyclerView.Adapter<ChothueLoDat_Adapter.ChothueLoDatHolder> {
    private List<Chothuelodat> chothuelodatList;
    private List<Chothuelodat> chothuelodatFilter;
    private Context context;
    AlertDialog alertDialog;
    private Chothuelodat chothuelodat;
    private ChothuelodatDAO chothuelodatDAO;




    public ChothueLoDat_Adapter(List<Chothuelodat> chothuelodatList, Context context) {
        this.chothuelodatList = chothuelodatList;
        this.context = context;

    }

    @NonNull
    @Override
    public ChothueLoDat_Adapter.ChothueLoDatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ChothueLoDatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChothueLoDat_Adapter.ChothueLoDatHolder holder, final int position) {
        chothuelodatDAO=new ChothuelodatDAO(context);
        chothuelodat=new Chothuelodat();
        holder.tittile.setText(chothuelodatList.get(position).getTittle());
        holder.date.setText(chothuelodatList.get(position).getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chothuelodat tinTuc = chothuelodatList.get(position);
                Intent intent = new Intent(context, WebView_Chothue_Canho.class);
                intent.putExtra("link", tinTuc.link);
                context.startActivity(intent);

            }
        });
        Random r = new Random();
        final int i1 = (r.nextInt(8000) + 65);
        if (chothuelodatList.get(position).getGia()!=null){
            holder.gia.setText(chothuelodatList.get(position).getGia()+"$");
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


//                        Toast.makeText(context,"Xóa Thành công "+chothuelodatList.get(position).getTittle(),Toast.LENGTH_LONG).show();
//                        chothuelodatDAO.delete(chothuelodatList.get(position).getMachothuelodat());
//                        chothuelodatList.remove(position);

                        for (int i=0;i<chothuelodatList.size();i++){
                        chothuelodatDAO.delete(chothuelodatList.get(i).getMachothuelodat());
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



                edttiitle.setText(chothuelodatList.get(position).getTittle());
                edtdate.setText(chothuelodatList.get(position).getDate());
                edtgia.setText(chothuelodatList.get(position).getGia());
                edtdientich.setText(chothuelodatList.get(position).getDientich());

                edtlink.setText(chothuelodatList.get(position).getLink());




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
                        }else if (link.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Link!",Toast.LENGTH_SHORT).show();
                        }else {


                            chothuelodat =new Chothuelodat();

                            chothuelodat.setTittle(edttiitle.getText().toString().trim());
                            chothuelodat.setDate(edtdate.getText().toString().trim());
                            chothuelodat.setGia(edtgia.getText().toString().trim());
                            chothuelodat.setDientich(edtdientich.getText().toString().trim());
                            chothuelodat.setLink(edtlink.getText().toString().trim());
                            chothuelodat.setMachothuelodat(chothuelodatList.get(position).getMachothuelodat());


                            chothuelodatDAO = new ChothuelodatDAO(context);

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

        holder.xemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chothuelodat tinTuc = chothuelodatList.get(position);
                Intent intent = new Intent(context, WebView_Chothue_Canho.class);
                intent.putExtra("link", tinTuc.link);
                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return chothuelodatList.size();
    }

    public class ChothueLoDatHolder extends RecyclerView.ViewHolder {
        TextView tittile,date,gia;
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


        }
    }


    public Filter getFilter() {
        return qLnguoidungFilter;
    }

    private Filter qLnguoidungFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Chothuelodat> filteredlist=new ArrayList<>();

            if(constraint ==null || constraint.length()==0){
                chothuelodatDAO=new ChothuelodatDAO(context);
                chothuelodatList=chothuelodatDAO.getAll();
                filteredlist.addAll(chothuelodatList);
            }else {
                String filterPattern=constraint.toString().toLowerCase().trim();
                chothuelodatDAO=new ChothuelodatDAO(context);
                chothuelodatList=chothuelodatDAO.getAll();
                for (Chothuelodat item: chothuelodatList){
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
            chothuelodatList.clear();
            chothuelodatList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
