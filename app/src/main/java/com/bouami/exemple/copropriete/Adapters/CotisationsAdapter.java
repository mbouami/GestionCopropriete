package com.bouami.exemple.copropriete.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bouami.exemple.copropriete.CoproParametres;

/**
 * Created by Mohammed on 28/03/2015.
 */
public class CotisationsAdapter extends SimpleCursorAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private int layout;

    public CotisationsAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        CoproParametres.parametre.TABLE_EN_COURS = CoproParametres.parametre.TABLE_COTISATIONS;
        CoproParametres.parametre.CONTENT_URI_EN_COURS = CoproParametres.parametre.CONTENT_URI_COTISATIONS;
        this.mInflater = LayoutInflater.from (context);
        this.layout = layout;
        this.mContext = context;
    }

    static class ViewHolder {
        protected TextView versement;
        protected CheckBox checkbox;
        protected String dateversement;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }

    @Override
    public Object getItem (int position)
    {
        return super.getItem (position);
    }
}
