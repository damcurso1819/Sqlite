package org.izv.aad.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static org.izv.aad.sqlite.MainActivity.TAG;

public class Ayudante extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "lugar.sqlite";
    private static final int VERSION = 1;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        Log.v(TAG, "constructor ayudante");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(TAG, "oncreate basedatos");
        db.execSQL(Contrato.TableLugar.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG, "onupgrade basedatos");
        db.execSQL(Contrato.TableLugar.DROP_TABLE);
        onCreate(db);
    }
}
