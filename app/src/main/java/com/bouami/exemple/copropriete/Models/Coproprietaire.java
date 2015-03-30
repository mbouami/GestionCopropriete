package com.bouami.exemple.copropriete.Models;

import android.database.Cursor;

import com.bouami.exemple.copropriete.CoproParametres;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Mohammed on 28/03/2015.
 */
public class Coproprietaire {

    int id;
    String civilite;
    String nom;
    String prenom;
    String mail;
    String appart;
    String creer_le;

    @Override
    public String toString() {
        return civilite + " " + nom + " " + prenom;
    }

    public Coproprietaire(Cursor c) {
        this.id = Integer.parseInt(c.getString(c.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_ENTRY_ID)));
        this.civilite = c.getString(c.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_CIVILITE));
        this.nom = c.getString(c.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_NOM));
        this.prenom = c.getString(c.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_PRENOM));
        this.mail = c.getString(c.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_MAIL));
        this.appart = c.getString(c.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_APPART));
        this.creer_le = c.getString(c.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_CREATEDATE));
    }

    public Coproprietaire(int id, String civilite, String nom, String prenom, String mail, String appart, String creer_le) {
        this.id = id;
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.appart = appart;
        this.creer_le = creer_le;
    }

    public Coproprietaire(String civilite, String nom, String prenom, String mail, String appart) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.appart = appart;
        this.creer_le = getDateTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAppart() {
        return appart;
    }

    public void setAppart(String appart) {
        this.appart = appart;
    }

    public String getCreer_le() {
        return creer_le;
    }

    public void setCreer_le(String creer_le) {
        this.creer_le = creer_le;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
