package com.example.gamudaland.SQLDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gamudaland.Model.DataBase;
import com.example.gamudaland.Model.Muabancanho;


import java.util.ArrayList;
import java.util.List;

public class MuabancanhoDAO {
    private DataBase dataBase;


    public MuabancanhoDAO(Context context) {
        this.dataBase = new DataBase(context);
    }

    private String USER_TABLE = "Muabancanho";

    public String TITTLE = "tittle";
    public String MACHOTHUELODAT = "mamuabancanho";
    public String LINK = "link";
    public String DIENTICH = "dientich";
    public String GIA = "gia";
    public String DATE = "date";
    public String IMG = "img";


    public List<Muabancanho> getAll() {
        List<Muabancanho> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();

        String SQL = "SELECT * FROM " + USER_TABLE ;

        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {

                    Muabancanho muabancanho = new Muabancanho();

                    muabancanho.setTittle(cursor.getString(cursor.getColumnIndex(TITTLE)));
                    muabancanho.setDate(cursor.getString(cursor.getColumnIndex(DATE)));
                    muabancanho.setLink(cursor.getString(cursor.getColumnIndex(LINK)));
                    muabancanho.setMamuabancanho(cursor.getString(cursor.getColumnIndex(MACHOTHUELODAT)));
                    muabancanho.setGia(cursor.getString(cursor.getColumnIndex(GIA)));
                    muabancanho.setDientich(cursor.getString(cursor.getColumnIndex(DIENTICH)));
                    muabancanho.setImg(cursor.getString(cursor.getColumnIndex(IMG)));


                    list.add(muabancanho);
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }

        return list;
    }

    public long insert(Muabancanho muabancanho) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TITTLE, muabancanho.getTittle());
        contentValues.put(LINK, muabancanho.getLink());
        contentValues.put(DATE, muabancanho.getDate());
        contentValues.put(MACHOTHUELODAT,muabancanho.getMamuabancanho());
        contentValues.put(GIA, muabancanho.getGia());
        contentValues.put(DIENTICH,muabancanho.getDientich());
        contentValues.put(IMG,muabancanho.getImg());



        long result = sqLiteDatabase.insert(USER_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long update(Muabancanho muabancanho) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();




        ContentValues contentValues = new ContentValues();
        contentValues.put(TITTLE, muabancanho.getTittle());
        contentValues.put(LINK, muabancanho.getLink());
        contentValues.put(DATE, muabancanho.getDate());
        contentValues.put(MACHOTHUELODAT,muabancanho.getMamuabancanho());
        contentValues.put(GIA, muabancanho.getGia());
        contentValues.put(DIENTICH,muabancanho.getDientich());
        contentValues.put(IMG,muabancanho.getImg());

        long result = sqLiteDatabase.update(USER_TABLE,contentValues, MACHOTHUELODAT + "=?", new String[]{muabancanho.getMamuabancanho()});
        sqLiteDatabase.close();
        return result;
    }
    public void delete(String id) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        sqLiteDatabase.delete(USER_TABLE, MACHOTHUELODAT + "=?", new String[]{id});

    }
}
