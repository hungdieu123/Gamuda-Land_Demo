package com.example.gamudaland.SQLDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gamudaland.Model.Chothuelodat;
import com.example.gamudaland.Model.DataBase;

import java.util.ArrayList;
import java.util.List;

public class ChothuelodatDAO {
    private DataBase dataBase;

    public ChothuelodatDAO(Context context) {
        this.dataBase = new DataBase(context);
    }

    private String USER_TABLE = "Chothuelodat";

    public String TITTLE = "tittle";
    public String MACHOTHUELODAT = "machothuelodat";
    public String LINK = "link";
    public String DIENTICH = "dientich";
    public String GIA = "gia";
    public String DATE = "date";
    public String IMG = "img";


    public List<Chothuelodat> getAll() {
        List<Chothuelodat> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();

        String SQL = "SELECT * FROM " + USER_TABLE ;

        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {

                    Chothuelodat chothuelodat = new Chothuelodat();

                    chothuelodat.setTittle(cursor.getString(cursor.getColumnIndex(TITTLE)));
                    chothuelodat.setDate(cursor.getString(cursor.getColumnIndex(DATE)));
                    chothuelodat.setLink(cursor.getString(cursor.getColumnIndex(LINK)));
                    chothuelodat.setMachothuelodat(cursor.getString(cursor.getColumnIndex(MACHOTHUELODAT)));
                    chothuelodat.setGia(cursor.getString(cursor.getColumnIndex(GIA)));
                    chothuelodat.setDientich(cursor.getString(cursor.getColumnIndex(DIENTICH)));
                    chothuelodat.setImg(cursor.getString(cursor.getColumnIndex(IMG)));


                    list.add(chothuelodat);
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }

        return list;
    }

    public long insert(Chothuelodat chothuelodat) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TITTLE, chothuelodat.getTittle());
        contentValues.put(LINK, chothuelodat.getLink());
        contentValues.put(DATE, chothuelodat.getDate());
        contentValues.put(MACHOTHUELODAT,chothuelodat.getMachothuelodat());
        contentValues.put(GIA, chothuelodat.getGia());
        contentValues.put(DIENTICH,chothuelodat.getDientich());
        contentValues.put(IMG,chothuelodat.getImg());



        long result = sqLiteDatabase.insert(USER_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long update(Chothuelodat chothuelodat) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();




        ContentValues contentValues = new ContentValues();
        contentValues.put(TITTLE, chothuelodat.getTittle());
        contentValues.put(LINK, chothuelodat.getLink());
        contentValues.put(DATE, chothuelodat.getDate());
        contentValues.put(MACHOTHUELODAT,chothuelodat.getMachothuelodat());
        contentValues.put(GIA, chothuelodat.getGia());
        contentValues.put(DIENTICH,chothuelodat.getDientich());
        contentValues.put(IMG,chothuelodat.getImg());

        long result = sqLiteDatabase.update(USER_TABLE,contentValues, MACHOTHUELODAT + "=?", new String[]{chothuelodat.getMachothuelodat()});
        sqLiteDatabase.close();
        return result;
    }
    public void delete(String id) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        sqLiteDatabase.delete(USER_TABLE, MACHOTHUELODAT + "=?", new String[]{id});

    }
}
