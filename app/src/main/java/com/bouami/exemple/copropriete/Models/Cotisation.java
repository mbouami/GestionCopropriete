package com.bouami.exemple.copropriete.Models;

import android.database.Cursor;

import com.bouami.exemple.copropriete.CoproParametres;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Mohammed on 28/03/2015.
 */
public class Cotisation {
    int id;
    Coproprietaire lecoproprietaire;
    Double somme;
    String donnee_le;

    @Override
    public String toString() {
        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return somme + " (" + myFormat.format(fromUser.parse(donnee_le)) + " )";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Cotisation(Cursor c) {
        this.id = Integer.parseInt(c.getString(c.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_ENTRY_ID)));
/*        this.lecoproprietaire = c.getString(c.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_COTISATIONS_KEY_COPROPRIETAIRE));*/
        this.somme = Double.valueOf(c.getString(c.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_COTISATIONS_SOMME)));
        this.donnee_le = c.getString(c.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_COTISATIONS_DONNEELE));
    }

    public Cotisation(int id, Coproprietaire lecoproprietaire, Double somme, String donnee_le) {
        this.id = id;
        this.lecoproprietaire = lecoproprietaire;
        this.somme = somme;
        this.donnee_le = donnee_le;
    }

    public Cotisation(Coproprietaire lecoproprietaire, Double somme) {
        this.lecoproprietaire = lecoproprietaire;
        this.somme = somme;
        this.donnee_le = getDateTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coproprietaire getLecoproprietaire() {
        return lecoproprietaire;
    }

    public void setLecoproprietaire(Coproprietaire lecoproprietaire) {
        this.lecoproprietaire = lecoproprietaire;
    }

    public Double getSomme() {
        return somme;
    }

    public void setSomme(Double somme) {
        this.somme = somme;
    }

    public String getDonnee_le() {
        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return myFormat.format(fromUser.parse(donnee_le));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
/*        return donnee_le;*/
    }

    public void setDonnee_le(String donnee_le) {
        this.donnee_le = donnee_le;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
