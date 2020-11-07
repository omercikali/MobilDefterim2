package com.omercikali.mobildefterim;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
}
