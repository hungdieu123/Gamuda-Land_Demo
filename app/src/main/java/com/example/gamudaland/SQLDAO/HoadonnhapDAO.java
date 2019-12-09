package com.example.gamudaland.SQLDAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gamudaland.Model.DataBase;
import com.example.gamudaland.Model.Hoadonnhap;

import java.util.ArrayList;
import java.util.List;

public class HoadonnhapDAO {
    private DataBase dataBase;

    public HoadonnhapDAO(Context context) {
        this.dataBase = new DataBase(context);
    }

    private String USER_TABLE = "Hoadonnhap";

    public String THELOAINHAP = "theloainhap";
    public String TIEUDENHAP = "tieudenhap";
    public String DATENHAP = "datenhap";
    public String GIANHAP = "gianhap";
    public String MAHOADONNHAP = "mahoadonnhap";
    public String DIENTICHNHAP = "dientichnhap";
    


    public List<Hoadonnhap> getAll() {
        List<Hoadonnhap> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();

        String SQL = "SELECT * FROM " + USER_TABLE ;

        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {

                    Hoadonnhap hoadonnhap = new Hoadonnhap();

                    hoadonnhap.setTieudenhap(cursor.getString(cursor.getColumnIndex(TIEUDENHAP)));
                    hoadonnhap.setTheloainhap(cursor.getString(cursor.getColumnIndex(THELOAINHAP)));
                    hoadonnhap.setDatenhap(cursor.getString(cursor.getColumnIndex(DATENHAP)));
                    hoadonnhap.setGianhap(cursor.getString(cursor.getColumnIndex(GIANHAP)));
                    hoadonnhap.setMahoadonnhap(cursor.getString(cursor.getColumnIndex(MAHOADONNHAP)));
                    hoadonnhap.setDientichnhap(cursor.getString(cursor.getColumnIndex(DIENTICHNHAP)));



                    list.add(hoadonnhap);
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }

        return list;
    }

    public long insert(Hoadonnhap chothuecanho) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TIEUDENHAP, chothuecanho.getTieudenhap());
        contentValues.put(THELOAINHAP, chothuecanho.getTheloainhap());
        contentValues.put(DATENHAP, chothuecanho.getDatenhap());
        contentValues.put(GIANHAP,chothuecanho.getGianhap());
        contentValues.put(MAHOADONNHAP, chothuecanho.getMahoadonnhap());
        contentValues.put(DIENTICHNHAP,chothuecanho.getDientichnhap());




        long result = sqLiteDatabase.insert(USER_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long update(Hoadonnhap hoadonnhap) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TIEUDENHAP, hoadonnhap.getTieudenhap());
        contentValues.put(THELOAINHAP, hoadonnhap.getTheloainhap());
        contentValues.put(DATENHAP, hoadonnhap.getDatenhap());
        contentValues.put(GIANHAP,hoadonnhap.getGianhap());
        contentValues.put(MAHOADONNHAP, hoadonnhap.getMahoadonnhap());
        contentValues.put(DIENTICHNHAP,hoadonnhap.getDientichnhap());

        long result = sqLiteDatabase.update(USER_TABLE,contentValues, MAHOADONNHAP + "=?", new String[]{hoadonnhap.getMahoadonnhap()});
        sqLiteDatabase.close();
        return result;
    }
    public void delete(String id) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        sqLiteDatabase.delete(USER_TABLE, MAHOADONNHAP + "=?", new String[]{id});

    }
}
