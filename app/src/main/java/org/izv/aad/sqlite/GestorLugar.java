package org.izv.aad.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.List;

import static org.izv.aad.sqlite.MainActivity.LOG;

public class GestorLugar {

    private Ayudante ayudante;
    private SQLiteDatabase bd;

    public GestorLugar(Context c) {
        this(c, true);
    }

    public GestorLugar(Context c, boolean write) {
        Log.v(LOG, "constructor gestor");
        this.ayudante = new Ayudante(c);
        if(write) {
            bd = ayudante.getWritableDatabase();
        } else {
            bd = ayudante.getReadableDatabase();
        }
    }

    public void cerrar() {
        ayudante.close();
    }

    //crud

    //create -> add - insert - persist
    public long add(Lugar lugar) {
        //... objeto -> contentvalues
        return 0;
    }

    //alias
    public long insert(Lugar lugar) {
        return add(lugar);
    }

    //alias
    public long persist(Lugar lugar) {
        return add(lugar);
    }

    //read -> uno, todos, condición + lista, cursor
    public List<Lugar> get() {
        return get(null, null);
    }

    public List<Lugar> get(String condicion, String[] parametros) {
        //...
        return null;
    }

    public Lugar get(long id) {
        //...
        return null;
    }

    public Cursor getCursor() {
        return getCursor(null, null);
    }

    public Cursor getCursor(String condicion, String[] parametros) {
        return null;
    }

    public Cursor getCursor(long id) {
        return getCursor(Contrato.TableLugar._ID + " = ?", new String[]{id + ""});
    }

    //update -> edit - update - save
    public int edit(Lugar lugar) {
        // objeto -> contentvalues
        //...
        return 0;
    }

    //delete -> delete, erase, remove
    public int remove(Lugar lugar) {
        return remove(lugar.getId());
    }

    public int remove(long id) {
        //...
        return 0;
    }

    public static Lugar get(Cursor c) {
        //...
        return null;
    }

    private static ContentValues get(Lugar lugar) {
        //...
        return null;
    }

    private static ContentValues getContentValues(Lugar lugar) {
        //versión para millenials
        return GestorLugar.get(lugar);
    }
}
