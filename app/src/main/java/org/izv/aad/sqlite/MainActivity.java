package org.izv.aad.sqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity
        /*implements Adaptador.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor>*/ {

    //Save data using SQLite
    //https://developer.android.com/training/data-storage/sqlite.html

    //performing long-running tasks on the main thread is always a concern
    //with Android development
    //even though SQLite data access is typically fast, the database
    //does reside in a file on the disk and should not be accessed
    //from the main thread

    //classes: CursorLoader and LoaderManager
    //https://developer.android.com/guide/components/loaders

    //https://developer.android.com/samples

    public static final String TAG = MainActivity.class.getSimpleName();

    private static final int ID = 1;

    private Adaptador adaptador;
    private Button button;
    private Cursor cursor;
    private GestorLugar gestor;
    private RecyclerView recyclerview;
    private RecyclerView.LayoutManager lmVista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        /*adaptador = new Adaptador(this, null);
        getLoaderManager().initLoader(ID, null, (android.app.LoaderManager.LoaderCallbacks<Object>) this);*/
    }

    private void init() {
        this.recyclerview = findViewById(R.id.recyclerview);
        lmVista = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(lmVista);
        this.button = findViewById(R.id.button);

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lugar lugar = new Lugar();
                lugar.setFecha("20190410");
                lugar.setComentario("comentario");
                lugar.setPais("España");
                lugar.setLocalidad("Granada");
                lugar.setLongitud(3.1);
                lugar.setLatitud(1.3);
                lugar.setNombre("nombre lugar " + Math.random());
                Random r = new Random();
                lugar.setPuntos(r.nextInt(5));
                long id = MainActivity.this.gestor.add(lugar);
                Log.v(TAG, "id: " + id);
                adaptador.swapCursor(gestor.getCursor());
            }
        });

        this.gestor = new GestorLugar(this);
        this.cursor = this.gestor.getCursor();

        Adaptador.OnItemClickListener oyente = new Adaptador.OnItemClickListener() {
            @Override
            public void onClick(Adaptador.ViewHolder holder, Lugar lugar) {
                Log.v(TAG, "click on: " + lugar.toString());
            }
        };

        this.adaptador = new Adaptador(oyente, this.cursor);
        this.recyclerview.setAdapter(this.adaptador);
    }

    /*@NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle bundle) {
        if(id==ID){
            return new CursorLoader(this, URI, null, null, null, null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        if(loader.getId() == ID){
            adaptador.swapCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        if(loader.getId() == ID){
            adaptador.swapCursor(null);
        }
    }

    @Override
    public void onClick(Adaptador.ViewHolder holder, Lugar lugar) {

    }*/
}