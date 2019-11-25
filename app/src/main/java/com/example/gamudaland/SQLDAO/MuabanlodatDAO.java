package com.example.gamudaland.SQLDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gamudaland.Model.Muabanlodat;
import com.example.gamudaland.Model.DataBase;

import java.util.ArrayList;
import java.util.List;

public class MuabanlodatDAO {
    private DataBase dataBase;
    

    public MuabanlodatDAO(Context context) {
        this.dataBase = new DataBase(context);
    }

    private String USER_TABLE = "Muabanlodat";

    public String TITTLE = "tittle";
    public String MACHOTHUELODAT = "mamuabanlodat";
    public String LINK = "link";
    public String DIENTICH = "dientich";
    public String GIA = "gia";
    public String DATE = "date";
    public String IMG = "img";


    public List<Muabanlodat> getAll() {
        List<Muabanlodat> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();

        String SQL = "SELECT * FROM " + USER_TABLE ;

        Cursor cursor = sqLiteDatabase.rawQuery(SQL, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {

                    Muabanlodat muabanlodat = new Muabanlodat();

                    muabanlodat.setTittle(cursor.getString(cursor.getColumnIndex(TITTLE)));
                    muabanlodat.setDate(cursor.getString(cursor.getColumnIndex(DATE)));
                    muabanlodat.setLink(cursor.getString(cursor.getColumnIndex(LINK)));
                    muabanlodat.setMamuabanlodat(cursor.getString(cursor.getColumnIndex(MACHOTHUELODAT)));
                    muabanlodat.setGia(cursor.getString(cursor.getColumnIndex(GIA)));
                    muabanlodat.setDientich(cursor.getString(cursor.getColumnIndex(DIENTICH)));
                    muabanlodat.setImg(cursor.getString(cursor.getColumnIndex(IMG)));


                    list.add(muabanlodat);
                    cursor.moveToNext();

                }
                cursor.close();
            }
        }

        return list;
    }

    public long insert(Muabanlodat muabanlodat) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TITTLE, muabanlodat.getTittle());
        contentValues.put(LINK, muabanlodat.getLink());
        contentValues.put(DATE, muabanlodat.getDate());
        contentValues.put(MACHOTHUELODAT,muabanlodat.getMamuabanlodat());
        contentValues.put(GIA, muabanlodat.getGia());
        contentValues.put(DIENTICH,muabanlodat.getDientich());
        contentValues.put(IMG,muabanlodat.getImg());



        long result = sqLiteDatabase.insert(USER_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long update(Muabanlodat muabanlodat) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();




        ContentValues contentValues = new ContentValues();
        contentValues.put(TITTLE, muabanlodat.getTittle());
        contentValues.put(LINK, muabanlodat.getLink());
        contentValues.put(DATE, muabanlodat.getDate());
        contentValues.put(MACHOTHUELODAT,muabanlodat.getMamuabanlodat());
        contentValues.put(GIA, muabanlodat.getGia());
        contentValues.put(DIENTICH,muabanlodat.getDientich());
        contentValues.put(IMG,muabanlodat.getImg());

        long result = sqLiteDatabase.update(USER_TABLE,contentValues, MACHOTHUELODAT + "=?", new String[]{muabanlodat.getMamuabanlodat()});
        sqLiteDatabase.close();
        return result;
    }
    public void delete(String id) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        sqLiteDatabase.delete(USER_TABLE, MACHOTHUELODAT + "=?", new String[]{id});

    }
}
