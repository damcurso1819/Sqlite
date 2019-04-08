package org.izv.aad.sqlite;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static org.izv.aad.sqlite.MainActivity.TAG;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    //using the recyclerview
    //https://github.com/codepath/android_guides/wiki/Using-the-RecyclerViev

    public interface OnItemClickListener {
        void onClick(ViewHolder holder, Lugar lugar);
    }

    private Cursor cursor;
    private OnItemClickListener oyente;

    public Adaptador(OnItemClickListener oyente, Cursor cursor) {
        this.cursor = cursor;
        this.oyente = oyente;
    }

    @Override
    public int getItemCount() {
        int c = 0;
        if (cursor != null){
            c = cursor.getCount();
        }
        Log.v(TAG, "count: " + c);
        return c;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        cursor.moveToPosition(i);
        Lugar lugar = GestorLugar.get(cursor);
        viewHolder.tvNombre.setText(lugar.getNombre());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, null, false);
        return new ViewHolder(v);
    }

    public void swapCursor(Cursor nuevoCursor) {
        if (nuevoCursor != null) {
            cursor = nuevoCursor;
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvNombre;
        public ImageView ivFoto;

        public ViewHolder(View v) {
            super(v);
            tvNombre = v.findViewById(R.id.tvNombre);
            ivFoto = v.findViewById(R.id.ivFoto);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.v(TAG, "onClick");
            Lugar lugar = null;
            long id = 0;
            if (cursor != null) {
                if (cursor.moveToPosition(getAdapterPosition())) {
                    lugar = GestorLugar.get(cursor);
                    id = lugar.getId();
                    Log.v(TAG, id + " " + lugar.toString());
                } else {
                    id = -1;
                }
            } else {
                id = -1;
            }
            oyente.onClick(this, lugar);
        }
    }
}