package com.bouami.exemple.copropriete.HelperProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.bouami.exemple.copropriete.CoproParametres;

import java.util.HashMap;

/**
 * Created by Mohammed on 28/03/2015.
 */
public class CoproModelProvider extends ContentProvider {

    private CoproBaseHelper coprodatabasehelper;
    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        coprodatabasehelper = new CoproBaseHelper(getContext());
        database = coprodatabasehelper.getWritableDatabase();
        if(database == null)
            return false;
        else
            return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // Check if the caller has requested a column which does not exists
        //checkColumns(projection);

        int uriType = CoproParametres.parametre.uriMatcher.match(uri);
        queryBuilder.setTables(CoproParametres.parametre.TABLE_EN_COURS);
        switch (uriType) {
            case CoproParametres.parametre._tbl:
                if (!TextUtils.isEmpty(sortOrder)) sortOrder = sortOrder + " ASC";
                break;
            case CoproParametres.parametre._tbl_ID:
                queryBuilder.appendWhere(CoproParametres.parametre.COLUMN_ENTRY_ID + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Adresse inconnue: " + uri);
        }
        Cursor cursor = queryBuilder.query(database, projection, selection,selectionArgs, null, null, sortOrder);

        // Make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (CoproParametres.parametre.uriMatcher.match(uri)){
            // Get all friend-birthday records
            case CoproParametres.parametre._tbl:
                return "vnd.android.cursor.dir/vnd.example.coproprietaires";
            // Get a particular friend
            case CoproParametres.parametre._tbl_ID:
                return "vnd.android.cursor.item/vnd.example.coproprietaires";
            default:
                throw new IllegalArgumentException("URI non supportée: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long row = database.insert(CoproParametres.parametre.TABLE_EN_COURS, "", values);
        if(row > 0) {
            Uri newUri = ContentUris.withAppendedId(CoproParametres.parametre.CONTENT_URI_EN_COURS, row);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("Fail to add a new record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        switch (CoproParametres.parametre.uriMatcher.match(uri)){
            case CoproParametres.parametre._tbl:
                // delete all the records of the table
                count = database.delete(CoproParametres.parametre.TABLE_EN_COURS, selection, selectionArgs);
                break;
            case CoproParametres.parametre._tbl_ID:
                String id = uri.getLastPathSegment();	//gets the id
                count = database.delete( CoproParametres.parametre.TABLE_EN_COURS, CoproParametres.parametre.COLUMN_ENTRY_ID +  " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;

        switch (CoproParametres.parametre.uriMatcher.match(uri)){
            case CoproParametres.parametre._tbl:
                count = database.update(CoproParametres.parametre.TABLE_COPROPRIETAIRES, values, selection, selectionArgs);
                break;
            case CoproParametres.parametre._tbl_ID:
                count = database.update(CoproParametres.parametre.TABLE_COPROPRIETAIRES, values, CoproParametres.parametre.COLUMN_ENTRY_ID +
                        " = " + uri.getLastPathSegment() +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri );
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
