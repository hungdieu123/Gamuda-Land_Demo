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

    public String THELOAIXUAT = "theloaixuat";
    public String TIEUDEXUAT = "tieudexuat";
    public String DATEXUAT = "datexuat";
    public String GIAXUAT = "giaxuat";
    public String MAHOADONXUAT = "mahoadonxuat";
    public String DIENTICHXUAT = "dientichxuat";



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

                    hoadonxuat.setTieudexuat(cursor.getString(cursor.getColumnIndex(TIEUDEXUAT)));
                    hoadonxuat.setTheloaixuat(cursor.getString(cursor.getColumnIndex(THELOAIXUAT)));
                    hoadonxuat.setDatexuat(cursor.getString(cursor.getColumnIndex(DATEXUAT)));
                    hoadonxuat.setGiaxuat(cursor.getString(cursor.getColumnIndex(GIAXUAT)));
                    hoadonxuat.setMahoadonxuat(cursor.getString(cursor.getColumnIndex(MAHOADONXUAT)));
                    hoadonxuat.setDientichxuat(cursor.getString(cursor.getColumnIndex(DIENTICHXUAT)));



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
        contentValues.put(TIEUDEXUAT, chothuecanho.getTieudexuat());
        contentValues.put(THELOAIXUAT, chothuecanho.getTheloaixuat());
        contentValues.put(DATEXUAT, chothuecanho.getDatexuat());
        contentValues.put(GIAXUAT,chothuecanho.getGiaxuat());
        contentValues.put(MAHOADONXUAT, chothuecanho.getMahoadonxuat());
        contentValues.put(DIENTICHXUAT,chothuecanho.getDientichxuat());




        long result = sqLiteDatabase.insert(USER_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long update(Hoadonxuat hoadonxuat) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TIEUDEXUAT, hoadonxuat.getTieudexuat());
        contentValues.put(THELOAIXUAT, hoadonxuat.getTheloaixuat());
        contentValues.put(DATEXUAT, hoadonxuat.getDatexuat());
        contentValues.put(GIAXUAT,hoadonxuat.getGiaxuat());
        contentValues.put(MAHOADONXUAT, hoadonxuat.getMahoadonxuat());
        contentValues.put(DIENTICHXUAT,hoadonxuat.getDientichxuat());

        long result = sqLiteDatabase.update(USER_TABLE,contentValues, MAHOADONXUAT + "=?", new String[]{hoadonxuat.getMahoadonxuat()});
        sqLiteDatabase.close();
        return result;
    }
    public void delete(String id) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        sqLiteDatabase.delete(USER_TABLE, MAHOADONXUAT + "=?", new String[]{id});

    }
}
