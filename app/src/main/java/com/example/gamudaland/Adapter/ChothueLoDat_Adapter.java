package com.example.gamudaland.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.gamudaland.Activity.Lo_Dat.ChothueLodat_Fragment;
import com.example.gamudaland.Activity.Lo_Dat.WebView_Chothue_Canho;
import com.example.gamudaland.Activity.Tin_Tuc.TinTuc;
import com.example.gamudaland.Activity.Tin_Tuc.WebviewActivity;
import com.example.gamudaland.Model.Chothuelodat;
import com.example.gamudaland.R;
import com.example.gamudaland.SQLDAO.ChothuelodatDAO;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

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
    public void onBindViewHolder(@NonNull ChothueLoDat_Adapter.ChothueLoDatHolder holder, final int position) {
        chothuelodatDAO=new ChothuelodatDAO(context);
        chothuelodat=new Chothuelodat();
        holder.tittile.setText(chothuelodatList.get(position).getTittle());
        holder.date.setText(chothuelodatList.get(position).getDate());
        if (chothuelodatList.get(position).getGia()!=null){
            holder.gia.setText(chothuelodatList.get(position).getGia()+"$");
        }


        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chothuelodatDAO.delete(chothuelodatList.get(position).getMachothuelodat());
                chothuelodatList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                View dialog1  = LayoutInflater.from(context).inflate(R.layout.update_chothuelodat,null);
                builder1.setView(dialog1);




                final TextInputEditText edttiitle,edtdate,edtgia,edtdientich,edtlink,edtma;
                final Button ok1,cancel1;
                ok1=dialog1.findViewById(R.id.btnok_sach);



                edttiitle=dialog1.findViewById(R.id.edttittle);
                edtdate=dialog1.findViewById(R.id.edtdate);
                edtgia=dialog1.findViewById(R.id.edtgia);
                edtdientich=dialog1.findViewById(R.id.edtdientich);
                edtma=dialog1.findViewById(R.id.edtma);
                edtlink=dialog1.findViewById(R.id.edtlink);



                edttiitle.setText(chothuelodatList.get(position).getTittle());
                edtdate.setText(chothuelodatList.get(position).getDate());
                edtgia.setText(chothuelodatList.get(position).getGia());
                edtdientich.setText(chothuelodatList.get(position).getDientich());

                edtlink.setText(chothuelodatList.get(position).getLink());
                edtma.setText(chothuelodatList.get(position).getMachothuelodat());
                edtma.setEnabled(false);


                ok1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        String tittle = edttiitle.getText().toString().trim();
                        String date  = edtdate.getText().toString().trim();
                        String gia = edtgia.getText().toString().trim();
                        String dientich = edtdientich.getText().toString().trim();
                        String link = edtlink.getText().toString().trim();
                        String ma = edtma.getText().toString().trim();


                        if (tittle.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Thể Loại!",Toast.LENGTH_SHORT).show();
                        }else if (date.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Tên Sách!",Toast.LENGTH_SHORT).show();
                        }else if (gia.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Số Lượng!",Toast.LENGTH_SHORT).show();
                        }else if (dientich.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Giá!",Toast.LENGTH_SHORT).show();
                        }else if (link.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Ngày Nhập!",Toast.LENGTH_SHORT).show();
                        }else if (ma.equals("")){
                            Toast.makeText(context,"Vui Lòng Không Để Trống Ngày Nhập!",Toast.LENGTH_SHORT).show();
                        }else {


                            chothuelodat =new Chothuelodat();

                            chothuelodat.setTittle(edttiitle.getText().toString().trim());
                            chothuelodat.setDate(edtdate.getText().toString().trim());
                            chothuelodat.setGia(edtgia.getText().toString().trim());
                            chothuelodat.setDientich(edtdientich.getText().toString().trim());
                            chothuelodat.setLink(edtlink.getText().toString().trim());
                            chothuelodat.setMachothuelodat(edtma.getText().toString().trim());


                            chothuelodatDAO = new ChothuelodatDAO(context);

                            long resurt = chothuelodatDAO.update(chothuelodat);
                            if(resurt>0){
                                Toast.makeText(context,"Update Thành Công!",Toast.LENGTH_SHORT).show();
                                notif();

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
    public void notif(){
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return chothuelodatList.size();
    }

    public class ChothueLoDatHolder extends RecyclerView.ViewHolder {
        TextView tittile,date,gia;
        ImageView xemthem,update,xoa;
        public ChothueLoDatHolder(@NonNull View itemView) {
            super(itemView);
            tittile=itemView.findViewById(R.id.tvtittle);
            date=itemView.findViewById(R.id.tvdate);
            xemthem=itemView.findViewById(R.id.ivxemthem);
            update=itemView.findViewById(R.id.ivupdate);
            xoa=itemView.findViewById(R.id.ivxoa);
            gia=itemView.findViewById(R.id.tvgia);


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
                filteredlist.addAll(chothuelodatList);
            }else {
                String filterPattern=constraint.toString().toLowerCase().trim();
                chothuelodatDAO=new ChothuelodatDAO(context);
                chothuelodatFilter=chothuelodatDAO.getAll();
                for (Chothuelodat item: chothuelodatFilter){
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
