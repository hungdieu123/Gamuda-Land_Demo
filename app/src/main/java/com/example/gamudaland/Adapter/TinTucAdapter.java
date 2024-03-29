package com.example.gamudaland.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gamudaland.Activity.Tin_Tuc.TinTuc;
import com.example.gamudaland.Activity.Tin_Tuc.WebviewActivity;
import com.example.gamudaland.R;

import java.util.List;


    public class TinTucAdapter extends BaseAdapter {
        private Context context;
        private List<TinTuc> tinTucList;

        public TinTucAdapter(Context context, List<TinTuc> tinTucList) {
            this.context = context;
            this.tinTucList = tinTucList;
        }



        @Override
        public int getCount() {
            return tinTucList.size();
        }

        @Override
        public Object getItem(int position) {
            return tinTucList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.tintuc, parent, false);
            TextView tvData = view.findViewById(R.id.tvtittle);
            TextView tvdate = view.findViewById(R.id.tvDate);

            TinTuc tinTuc = tinTucList.get(position);
            tvData.setText(tinTuc.title);
            tvdate.setText(tinTuc.pubDate);



            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TinTuc tinTuc = tinTucList.get(position);
                    Intent intent = new Intent(context, WebviewActivity.class);
                    intent.putExtra("link", tinTuc.linkk);
                    context.startActivity(intent);

                }
            });

            return view;
        }




    }

