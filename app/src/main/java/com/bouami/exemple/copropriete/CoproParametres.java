package com.bouami.exemple.copropriete;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by Mohammed on 28/03/2015.
 */
public class CoproParametres {
    public CoproParametres() {

    }
    public static abstract class parametre {
        public static final int DATABASE_VERSION = 1;
        public static ContentResolver contentresolver = null;
        public static final String DATABASE_NAME = "gestioncoproprietaires.db";
        public static final String TEXT_TYPE = " TEXT";
        public static final String INTEGER_TYPE = " INTEGER";
        public static final String REAL_TYPE = " REAL";
        public static final String DATETIME_TYPE = " DATETIME";
        public static final String BOOLEAN_TYPE = " BOOLEAN";
        public static final String COMMA_SEP = ",";
        public static final String TABLE_COPROPRIETAIRES = "coproprietaires";
        public static final String TABLE_COTISATIONS = "cotisations";
        public static String TABLE_EN_COURS = null;
        public static final String COLUMN_NULLABLE = null;
        public static final String COLUMN_ENTRY_ID = "_id";
        public static final String COLUMN_COPROPRIETAIRE_CIVILITE = "civilite";
        public static final String COLUMN_COPROPRIETAIRE_NOM = "nom";
        public static final String COLUMN_COPROPRIETAIRE_PRENOM = "prenom";
        public static final String COLUMN_COPROPRIETAIRE_MAIL = "mail";
        public static final String COLUMN_COPROPRIETAIRE_APPART = "appart";
        public static final String COLUMN_COPROPRIETAIRE_CREATEDATE= "creer_le";
        public static final String COLUMN_COTISATIONS_SOMME = "somme";
        public static final String COLUMN_COTISATIONS_KEY_COPROPRIETAIRE = "coproprietaire_id";
        public static final String COLUMN_COTISATIONS_DONNEELE= "donner_le";
        public static final String[] COPROPRIETAIRE_PROJECTIONS = {
                COLUMN_ENTRY_ID,
                COLUMN_COPROPRIETAIRE_CIVILITE,
                COLUMN_COPROPRIETAIRE_NOM,
                COLUMN_COPROPRIETAIRE_PRENOM,
                COLUMN_COPROPRIETAIRE_MAIL,
                COLUMN_COPROPRIETAIRE_APPART,
                COLUMN_COPROPRIETAIRE_CREATEDATE};
        public static final String[] COTISATIONS_PROJECTIONS = {
                COLUMN_ENTRY_ID,
                COLUMN_COTISATIONS_KEY_COPROPRIETAIRE,
                COLUMN_COTISATIONS_SOMME,
                COLUMN_COTISATIONS_DONNEELE};
        public static Cursor mCursor = null;
        public static Cursor mCursor_Cotisation = null;
        public static final String SQL_DELETE_TABLE_COPROPRIETAIRES ="DROP TABLE IF EXISTS " + TABLE_COPROPRIETAIRES;
        public static final String SQL_DELETE_TABLE_COTISATIONS ="DROP TABLE IF EXISTS " + TABLE_COTISATIONS;
        public static final String SQL_CREATE_TABLE_COPROPRIETAIRES =
                "CREATE TABLE " + TABLE_COPROPRIETAIRES + " (" +
                        COLUMN_ENTRY_ID + " INTEGER PRIMARY KEY," +
                        COLUMN_COPROPRIETAIRE_CIVILITE + TEXT_TYPE + COMMA_SEP +
                        COLUMN_COPROPRIETAIRE_NOM + TEXT_TYPE + COMMA_SEP +
                        COLUMN_COPROPRIETAIRE_PRENOM + TEXT_TYPE + COMMA_SEP +
                        COLUMN_COPROPRIETAIRE_MAIL + TEXT_TYPE + COMMA_SEP +
                        COLUMN_COPROPRIETAIRE_APPART + TEXT_TYPE + COMMA_SEP +
                        COLUMN_COPROPRIETAIRE_CREATEDATE + DATETIME_TYPE +
                        " )";
        public static final String SQL_CREATE_TABLE_COTISATIONS =
                "CREATE TABLE " + TABLE_COTISATIONS + " (" +
                        COLUMN_ENTRY_ID + " INTEGER PRIMARY KEY," +
                        COLUMN_COTISATIONS_KEY_COPROPRIETAIRE + INTEGER_TYPE + COMMA_SEP +
                        COLUMN_COTISATIONS_SOMME + REAL_TYPE + COMMA_SEP +
                        COLUMN_COTISATIONS_DONNEELE + DATETIME_TYPE +
                        " )";
        public static final String AUTHORITY = "com.bouami.exemple.copropriete";
        public static final int _tbl = 1;
        public static final int _tbl_ID = 2;
        public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        static {
            uriMatcher.addURI(AUTHORITY, TABLE_COPROPRIETAIRES, _tbl);
            uriMatcher.addURI(AUTHORITY, TABLE_COPROPRIETAIRES + "/#", _tbl_ID);
            uriMatcher.addURI(AUTHORITY, TABLE_COTISATIONS, _tbl);
            uriMatcher.addURI(AUTHORITY, TABLE_COTISATIONS + "/#", _tbl_ID);
        }
        public static final Uri CONTENT_URI_COPROPRIETAIRES = Uri.parse("content://" + AUTHORITY + "/" + TABLE_COPROPRIETAIRES);
        public static final Uri CONTENT_URI_COTISATIONS = Uri.parse("content://" + AUTHORITY + "/" + TABLE_COTISATIONS);
        public static Uri CONTENT_URI_EN_COURS = null;
    }


}
