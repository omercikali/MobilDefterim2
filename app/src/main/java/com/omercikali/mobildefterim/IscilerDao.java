package com.omercikali.mobildefterim;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class IscilerDao {
    public ArrayList<WorkerModel> tumisciler(Veritabani vt) {
        ArrayList<WorkerModel> workerModelArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM isci_takip_tablosu", null);
        while (c.moveToNext()) {

            WorkerModel w = new WorkerModel(c.getString(c.getColumnIndex("isci_ismi"))
                    , c.getInt(c.getColumnIndex("gunluk_ucret"))
                    , c.getLong(c.getColumnIndex("tarih")));
            workerModelArrayList.add(w);
        }
        db.close();
        return workerModelArrayList;
    }
    public void notEkle(Veritabani vt,String isci_ismi,int gunluk_ucret,long tarih ){
        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("isci_ismi",isci_ismi);
        values.put("gunluk_ucret",gunluk_ucret);
        values.put("tarih",tarih);
        db.insertOrThrow("isci_takip_tablosu",null,values);
        db.close();


    }
}
