package com.example.gamudaland.SQLDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gamudaland.Model.Chothuecanho;
import com.example.gamudaland.Model.DataBase;

import java.util.ArrayList;
import java.util.List;

public class ChothuecanhoDAO {
    private DataBase dataBase;

    public ChothuecanhoDAO(Context context) {
        this.dataBase = new DataBase(context);
    }

    private String USER_TABLE = "Chothuecanho";

    public String TITTLE = "tittle";
    public String MACHOTHUELODAT = "machothuecanho";
    public String LINK = "link";
    public String DIENTICH = "dientich";
    public String GIA = "gia";
    public String DATE = "date";
    public String IMG = "img";


    public List<Chothuecanho> getAll() {
        List<Chothuecanho> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();

        String SQL = "SELECT * FROM " + USER_TABLE ;

        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {

                    Chothuecanho chothuecanho = new Chothuecanho();

                    chothuecanho.setTittle(cursor.getString(cursor.getColumnIndex(TITTLE)));
                    chothuecanho.setDate(cursor.getString(cursor.getColumnIndex(DATE)));
                    chothuecanho.setLink(cursor.getString(cursor.getColumnIndex(LINK)));
                    chothuecanho.setMachothuecanho(cursor.getString(cursor.getColumnIndex(MACHOTHUELODAT)));
                    chothuecanho.setGia(cursor.getString(cursor.getColumnIndex(GIA)));
                    chothuecanho.setDientich(cursor.getString(cursor.getColumnIndex(DIENTICH)));
                    chothuecanho.setImg(cursor.getString(cursor.getColumnIndex(IMG)));


                    list.add(chothuecanho);
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }

        return list;
    }

    public long insert(Chothuecanho chothuecanho) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TITTLE, chothuecanho.getTittle());
        contentValues.put(LINK, chothuecanho.getLink());
        contentValues.put(DATE, chothuecanho.getDate());
        contentValues.put(MACHOTHUELODAT,chothuecanho.getMachothuecanho());
        contentValues.put(GIA, chothuecanho.getGia());
        contentValues.put(DIENTICH,chothuecanho.getDientich());
        contentValues.put(IMG,chothuecanho.getImg());



        long result = sqLiteDatabase.insert(USER_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long update(Chothuecanho chothuecanho) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();




        ContentValues contentValues = new ContentValues();
        contentValues.put(TITTLE, chothuecanho.getTittle());
        contentValues.put(LINK, chothuecanho.getLink());
        contentValues.put(DATE, chothuecanho.getDate());
        contentValues.put(MACHOTHUELODAT,chothuecanho.getMachothuecanho());
        contentValues.put(GIA, chothuecanho.getGia());
        contentValues.put(DIENTICH,chothuecanho.getDientich());
        contentValues.put(IMG,chothuecanho.getImg());

        long result = sqLiteDatabase.update(USER_TABLE,contentValues, MACHOTHUELODAT + "=?", new String[]{chothuecanho.getMachothuecanho()});
        sqLiteDatabase.close();
        return result;
    }
    public void delete(String id) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        sqLiteDatabase.delete(USER_TABLE, MACHOTHUELODAT + "=?", new String[]{id});

    }
}
