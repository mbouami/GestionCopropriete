package com.bouami.exemple.copropriete.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bouami.exemple.copropriete.CoproParametres;

/**
 * Created by Mohammed on 28/03/2015.
 */
public class CoproprietairesAdapter extends SimpleCursorAdapter {
    private LayoutInflater mInflater;
    private int layout;

    public CoproprietairesAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        CoproParametres.parametre.TABLE_EN_COURS = CoproParametres.parametre.TABLE_COPROPRIETAIRES;
        CoproParametres.parametre.CONTENT_URI_EN_COURS = CoproParametres.parametre.CONTENT_URI_COPROPRIETAIRES;
        this.mInflater = LayoutInflater.from (context);
        this.layout = layout;
    }

    static class ViewHolder {
        protected TextView nom;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mInflater.inflate(layout, parent, false);
        TextView nom = (TextView) view.findViewById(android.R.id.text1);
        ViewHolder holder = new ViewHolder();
        holder.nom = nom;
        view.setTag(holder);
        return view;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final ViewHolder holder = (ViewHolder) view.getTag();
        String NomCopro =
                cursor.getString(cursor.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_CIVILITE))+" "
                        + cursor.getString(cursor.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_NOM))+" "
                        + cursor.getString(cursor.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_PRENOM));
        setViewText(holder.nom, NomCopro);
    }

    @Override
    public Object getItem (int position)
    {
        return super.getItem (position);
    }
}
