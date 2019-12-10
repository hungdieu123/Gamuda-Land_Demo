package com.example.gamudaland.SQLDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gamudaland.Model.DataBase;
import com.example.gamudaland.Model.Hoadonxuat;

import java.util.ArrayList;
import java.util.List;

public class HoadonxuatDAO {
    private DataBase dataBase;

    public HoadonxuatDAO(Context context) {
        this.dataBase = new DataBase(context);
    }

    private String USER_TABLE = "Hoadonxuat";

    public String THELOAINHAP = "theloaixuat";
    public String TIEUDENHAP = "tieudexuat";
    public String DATENHAP = "datexuat";
    public String GIANHAP = "giaxuat";
    public String MAHOADONNHAP = "mahoadonxuat";
    public String DIENTICHNHAP = "dientichxuat";



    public List<Hoadonxuat> getAll() {
        List<Hoadonxuat> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();

        String SQL = "SELECT * FROM " + USER_TABLE ;

        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {

                    Hoadonxuat hoadonxuat = new Hoadonxuat();

                    hoadonxuat.setTieudexuat(cursor.getString(cursor.getColumnIndex(TIEUDENHAP)));
                    hoadonxuat.setTheloaixuat(cursor.getString(cursor.getColumnIndex(THELOAINHAP)));
                    hoadonxuat.setDatexuat(cursor.getString(cursor.getColumnIndex(DATENHAP)));
                    hoadonxuat.setGiaxuat(cursor.getString(cursor.getColumnIndex(GIANHAP)));
                    hoadonxuat.setMahoadonxuat(cursor.getString(cursor.getColumnIndex(MAHOADONNHAP)));
                    hoadonxuat.setDientichxuat(cursor.getString(cursor.getColumnIndex(DIENTICHNHAP)));



                    list.add(hoadonxuat);
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }

        return list;
    }

    public long insert(Hoadonxuat chothuecanho) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TIEUDENHAP, chothuecanho.getTieudexuat());
        contentValues.put(THELOAINHAP, chothuecanho.getTheloaixuat());
        contentValues.put(DATENHAP, chothuecanho.getDatexuat());
        contentValues.put(GIANHAP,chothuecanho.getGiaxuat());
        contentValues.put(MAHOADONNHAP, chothuecanho.getMahoadonxuat());
        contentValues.put(DIENTICHNHAP,chothuecanho.getDientichxuat());




        long result = sqLiteDatabase.insert(USER_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long update(Hoadonxuat hoadonxuat) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TIEUDENHAP, hoadonxuat.getTieudexuat());
        contentValues.put(THELOAINHAP, hoadonxuat.getTheloaixuat());
        contentValues.put(DATENHAP, hoadonxuat.getDatexuat());
        contentValues.put(GIANHAP,hoadonxuat.getGiaxuat());
        contentValues.put(MAHOADONNHAP, hoadonxuat.getMahoadonxuat());
        contentValues.put(DIENTICHNHAP,hoadonxuat.getDientichxuat());

        long result = sqLiteDatabase.update(USER_TABLE,contentValues, MAHOADONNHAP + "=?", new String[]{hoadonxuat.getMahoadonxuat()});
        sqLiteDatabase.close();
        return result;
    }
    public void delete(String id) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        sqLiteDatabase.delete(USER_TABLE, MAHOADONNHAP + "=?", new String[]{id});

    }
}
