package com.omercikali.mobildefterim;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Veritabani extends SQLiteOpenHelper {
    private static final String VERITABANI_ISMI = "veritabanim";
    private static final int VERITABANI_VERSION = 1;
    private static final String TABLO_ISMI = "isci_takip_tablosu";


    private static final String ID = "_id";
    private static final String ISCI_ISMI = "isci_ismi";
    private static final String GUNLUK_UCRET = "gunluk_ucret";
    private static final String TARIH = "tarih";

    public Veritabani(@Nullable Context context) {
        super(context, VERITABANI_ISMI, null, VERITABANI_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tablo_olustur = "CREATE TABLE " + TABLO_ISMI +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ISCI_ISMI + " TEXT, " +
                GUNLUK_UCRET + " INTEGER NOT NULL, " +
                TARIH + " INTEGER NOT NULL);";
        db.execSQL(tablo_olustur);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLO_ISMI);
        onCreate(db);


    }

    public long KayitEkle(WorkerModel workerModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ISCI_ISMI, workerModel.getIsci_ismi());
        cv.put(GUNLUK_UCRET, workerModel.getGunluk_calisma_ucreti());
        cv.put(TARIH, workerModel.getTarih());

        long id = db.insert(TABLO_ISMI, null, cv);
        db.close();

        return id;
    }

    public List<WorkerModel> TumKayitlar() {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] sutunlar = new String[]{ISCI_ISMI, GUNLUK_UCRET, TARIH};
        Cursor c = db.query(TABLO_ISMI, sutunlar, null, null, null, null, null);
        int isci_sirano = c.getColumnIndex(ISCI_ISMI);
        int gunluk_calismasirano = c.getColumnIndex(GUNLUK_UCRET);
        int tarih_sirano = c.getColumnIndex(TARIH);
        List<WorkerModel> workerModelList = new ArrayList<WorkerModel>();

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

            WorkerModel workerModel = new WorkerModel();
            workerModel.setIsci_ismi(c.getString(isci_sirano));
            workerModel.setGunluk_calisma_ucreti(c.getInt(gunluk_calismasirano));
            workerModel.setTarih(c.getLong(tarih_sirano));
        }
        db.close();
        return workerModelList;
    }
}
