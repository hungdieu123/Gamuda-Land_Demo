package com.example.gamudaland.Activity.Tin_Tuc;

import android.app.ActionBar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.gamudaland.Adapter.DataAdapter;
import com.example.gamudaland.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TinTuc_Fragment extends Fragment {

    private ArrayList<TinTuc> tinTucs;
    private ListView lvList;
    private DataAdapter dataAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);

        tinTucs = new ArrayList<>();
        lvList = view.findViewById(R.id.rcview);
        dataAdapter = new DataAdapter(getActivity(), tinTucs);
        lvList.setAdapter(dataAdapter);
        String url = "https://batdongsan.com.vn/Modules/RSS/RssDetail.aspx?catid=88&typeid=2";
        Data data = new Data();
        data.execute(url);


//        setupActionBar();

        return view;
    }
    private void setupActionBar() {
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);

        ActionBar.LayoutParams lp1 = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        View customNav = LayoutInflater.from(getActivity()).inflate(R.layout.actionbar, null); // layout which contains your button.

        actionBar.setCustomView(customNav, lp1);
    }



    class Data extends AsyncTask<String, Long, ArrayList<TinTuc>> {


        @Override
        protected ArrayList<TinTuc> doInBackground(String... strings) {
            String link = strings[0];
            try {
                URL url = new URL(link);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();
                //Khởi tạo đối tượng bằng XML
                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();

                XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();

                xmlPullParser.setInput(inputStream, "utf-8");


                int eventType = xmlPullParser.getEventType();
                TinTuc tinTuc = null;
                String text = "";
                while (eventType != xmlPullParser.END_DOCUMENT) {
                    eventType = xmlPullParser.getEventType();
                    String tag = xmlPullParser.getName();
                    switch (eventType) {
                        //Bắt đầu thẻ
                        case XmlPullParser.START_TAG:
                            if (tag.equalsIgnoreCase("item")) {
                                tinTuc = new TinTuc();
                            }
                            break;
                        case XmlPullParser.TEXT:
                            text = xmlPullParser.getText();
                            break;
                        case XmlPullParser.END_TAG:
                            if (tinTuc != null) {


                                if (tag.equalsIgnoreCase("title")) {
                                    tinTuc.title = text;
                                } else if (tag.equalsIgnoreCase("description")) {
                                    tinTuc.description = text;
                                } else if (tag.equalsIgnoreCase("pubDate")) {
                                    tinTuc.pubDate = text;
                                } else if (tag.equalsIgnoreCase("url")) {
                                    tinTuc.image = text;
                                } else if (tag.equalsIgnoreCase("link")) {
                                    tinTuc.linkk = text;
                                } else if (tag.equalsIgnoreCase("item")) {
                                    tinTucs.add(tinTuc);
                                }
                            }
                            break;

                    }
                    xmlPullParser.next();
                }


            } catch (MalformedURLException e) {
                //url bị sai : url
                e.printStackTrace();
            } catch (IOException e) {
                //Không kết nối đc : openConnection
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                //Sai định dạng : newInstance
                e.printStackTrace();
            }

            return tinTucs;
        }

        @Override
        protected void onPostExecute(ArrayList<TinTuc> tinTucs) {
            super.onPostExecute(tinTucs);
//            Toast.makeText(NewsActivity.this,tinTucs.size()+"",Toast.LENGTH_SHORT).show();
            dataAdapter.notifyDataSetChanged();
        }

    }
}