package org.izv.aad.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.ArrayList;
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
        return bd.insert(Contrato.TableLugar.TABLE, null, GestorLugar.get(lugar));
    }

    //alias
    public long insert(Lugar lugar) {
        return add(lugar);
    }

    //alias
    public long persist(Lugar lugar) {
        return add(lugar);
    }

    //Read -> one, all, condition + list, cursor
    public List<Lugar> get() {
        return get(null, null);
    }

    //alias
    public List<Lugar> getLugar() {
        return get();
    }

    public List<Lugar> get(String condicion, String[] parametros) {
        List<Lugar> todos = new ArrayList<>();
        Cursor cursor = getCursor(condicion, parametros);
        while (cursor.moveToNext ()) {
            todos.add(GestorLugar.get(cursor));
        }
        cursor.close();
        return todos;
    }

    //alias
    public List<Lugar> getLugar(String condicion, String[] parametros) {
        return get(condicion, parametros);
    }

    public Lugar get(long id) {
        Lugar lugar = null;
        List<Lugar> lista = get(Contrato.TableLugar._ID + " = ?", new String[]{id + ""});
        if(lista.size() > 0) {
            lugar = lista.get(0);
        }
        return lugar;
    }

    //alias
    public Lugar getLugar(long id) {
        return get(id);
    }

    public Cursor getCursor() {
        return getCursor(null, null);
    }

    public Cursor getCursor(String condicion, String[] parametros) {
        return bd.query(
                Contrato.TableLugar.TABLE,
                null,
                condicion,
                parametros,
                null,
                null,
                Contrato.TableLugar.NOMBRE + " asc");
    }

    public Cursor getCursor(long id) {
        return getCursor(Contrato.TableLugar._ID + " = ?", new String[]{id + ""});
    }

    //Update -> edit - save - update
    public int edit(Lugar lugar) {
        return bd.update(
                Contrato.TableLugar.TABLE,
                GestorLugar.get(lugar),
                Contrato.TableLugar._ID + " = ?",
                new String[]{lugar.getId() + ""});
    }

    //Delete -> delete - erase - remove
    public int remove(Lugar lugar) {
        return remove(lugar.getId());
    }

    public int remove(long id) {
        String condicion = Contrato.TableLugar._ID + " = ?";
        String[] argumentos = { id + "" };
        return bd.delete(Contrato.TableLugar.TABLE, condicion, argumentos);
    }

    //others

    public static Lugar get(Cursor c) {
        Lugar lugar = new Lugar();
        lugar.setId(c.getLong(c.getColumnIndex(Contrato.TableLugar._ID)));
        lugar.setNombre(c.getString(c.getColumnIndex(Contrato.TableLugar.NOMBRE)));
        lugar.setLatitud(c.getDouble(c.getColumnIndex(Contrato.TableLugar.LATITUD)));
        lugar.setLongitud(c.getDouble(c.getColumnIndex(Contrato.TableLugar.LONGITUD)));
        lugar.setLocalidad(c.getString(c.getColumnIndex(Contrato.TableLugar.LOCALIDAD)));
        lugar.setPais(c.getString(c.getColumnIndex(Contrato.TableLugar.PAIS)));
        lugar.setComentario(c.getString(c.getColumnIndex(Contrato.TableLugar.COMENTARIO)));
        lugar.setPuntos(c.getInt(c.getColumnIndex(Contrato.TableLugar.PUNTOS)));
        lugar.setFecha(c.getString(c.getColumnIndex(Contrato.TableLugar.FECHA)));
        return lugar;
    }

    //alias
    public static Lugar getLugar(Cursor c) {
        return GestorLugar.get(c);
    }

    private static ContentValues get(Lugar lugar) {
        ContentValues contentValues = new ContentValues();
        //contentValues.put(Contrato.TableLugar._ID, lugar.getId());
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

    //alias, millenials version
    private static ContentValues getContentValues(Lugar lugar) {
        return GestorLugar.get(lugar);
    }
}
