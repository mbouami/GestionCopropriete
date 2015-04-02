package com.bouami.exemple.copropriete.Adapters;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bouami.exemple.copropriete.CoproParametres;
import com.bouami.exemple.copropriete.Models.Cotisation;
import com.bouami.exemple.copropriete.R;

/**
 * Created by Mohammed on 28/03/2015.
 */
public class CotisationsAdapter extends SimpleCursorAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private int layout;
    private Double totalversement = (Double) Double.valueOf(0);

    public CotisationsAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        CoproParametres.parametre.TABLE_EN_COURS = CoproParametres.parametre.TABLE_COTISATIONS;
        CoproParametres.parametre.CONTENT_URI_EN_COURS = CoproParametres.parametre.CONTENT_URI_COTISATIONS;
        this.mInflater = LayoutInflater.from (context);
        this.layout = layout;
        this.mContext = context;
    }

    static class ViewHolder {
        protected TextView cotisation;
        protected CheckBox checkbox;
        protected TextView dateversement;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mInflater.inflate(layout, parent, false);
        TextView cotisation = (TextView) view.findViewById(R.id.cotisation);
        TextView dateversement = (TextView) view.findViewById(R.id.dateversement);
        CheckBox choix = (CheckBox) view.findViewById(R.id.choix);

        ViewHolder holder = new ViewHolder();
        holder.cotisation = cotisation;
        holder.dateversement = dateversement;
        holder.checkbox = choix;
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final ViewHolder holder = (ViewHolder) view.getTag();
        final TextView tversement = (TextView) ((Activity)mContext).findViewById(R.id.totalversement);
        final Cotisation lacotisation = new Cotisation(cursor);
        setViewText(holder.cotisation,lacotisation.getSomme().toString());
        setViewText(holder.dateversement,lacotisation.getDonnee_le());
        holder.checkbox.setChecked(holder.checkbox.isChecked());
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    totalversement += Double.parseDouble(holder.cotisation.getText().toString());
                } else {
                    totalversement -= Double.parseDouble(holder.cotisation.getText().toString());
                }
                tversement.setText("Total des versements : "+totalversement.toString());
            }
        });

    }

    @Override
    public Object getItem (int position)
    {
        return super.getItem (position);
    }
}
