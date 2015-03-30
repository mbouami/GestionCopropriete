package com.bouami.exemple.copropriete.HelperProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bouami.exemple.copropriete.CoproParametres;

/**
 * Created by Mohammed on 28/03/2015.
 */
public class CoproBaseHelper extends SQLiteOpenHelper {

    public CoproBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public CoproBaseHelper(Context context) {
        super(context, CoproParametres.parametre.DATABASE_NAME, null, CoproParametres.parametre.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CoproParametres.parametre.SQL_CREATE_TABLE_COPROPRIETAIRES);
        db.execSQL(CoproParametres.parametre.SQL_CREATE_TABLE_COTISATIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CoproParametres.parametre.SQL_DELETE_TABLE_COPROPRIETAIRES);
        db.execSQL(CoproParametres.parametre.SQL_DELETE_TABLE_COTISATIONS);
        onCreate(db);
    }
}
