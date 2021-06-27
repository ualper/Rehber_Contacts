package com.example.rehber;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

class DatabaseYardimcisi extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "Rehber.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "kisiler";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_AD = "ad";
    private static final String COLUMN_SOYAD = "soyad";
    private static final String COLUMN_ADRES = "adres";
    private static final String COLUMN_TEL = "tel";
    private static final String COLUMN_MAIL = "mail";

    DatabaseYardimcisi(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_AD + " TEXT, " +
                COLUMN_SOYAD + " TEXT, " +
                COLUMN_TEL + " TEXT, " +
                COLUMN_MAIL + " TEXT, " +
                COLUMN_ADRES + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addKisi(String ad, String soyad, String tel, String mail, String adres) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_AD, ad);
        cv.put(COLUMN_SOYAD, soyad);
        cv.put(COLUMN_TEL, tel);
        cv.put(COLUMN_MAIL, mail);
        cv.put(COLUMN_ADRES, adres);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Hata", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Ekleme Başarılı!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String ad, String soyad, String adres, String mail, String tel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_AD, ad);
        cv.put(COLUMN_SOYAD, soyad);
        cv.put(COLUMN_TEL, tel);
        cv.put(COLUMN_MAIL, mail);
        cv.put(COLUMN_ADRES, adres);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Hatalı", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Güncelleme Başarılı", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Silme Hatası", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Silme Başarılı", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
