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

    //Create -> add - insert - persist
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

    //Read -> uno, todos, condición + lista, cursor
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

    //Update -> edit - save - update
    public int edit(Lugar lugar) {
        // objeto -> contentvalues
        //...
        return 0;
    }

    //Delete -> delete - erase - remove
    public int remove(Lugar lugar) {
        return remove(lugar.getId());
    }

    public int remove(long id) {
        //...
        return 0;
    }

    //others

    public static Lugar get(Cursor c) {
        //...
        return null;
    }

    private static ContentValues get(Lugar lugar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contrato.TableLugar._ID, lugar.getId());
        contentValues.put(Contrato.TableLugar.NOMBRE, lugar.getNombre());
        contentValues.put(Contrato.TableLugar.LATITUD, lugar.getLatitud());
        contentValues.put(Contrato.TableLugar.LONGITUD, lugar.getLongitud());
        contentValues.put(Contrato.TableLugar.LOCALIDAD, lugar.getLocalidad());
        contentValues.put(Contrato.TableLugar.PAIS, lugar.getPais());
        contentValues.put(Contrato.TableLugar.COMENTARIO, lugar.getComentario());
        contentValues.put(Contrato.TableLugar.PUNTOS, lugar.getPuntos());
        contentValues.put(Contrato.TableLugar.FECHA, lugar.getFecha());
        return contentValues;
    }

    private static ContentValues getContentValues(Lugar lugar) {
        //versión para millenials
        return GestorLugar.get(lugar);
    }
}
