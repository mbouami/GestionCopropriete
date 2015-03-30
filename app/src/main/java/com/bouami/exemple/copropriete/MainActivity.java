package com.bouami.exemple.copropriete;

import android.app.Activity;
import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.bouami.exemple.copropriete.Models.Coproprietaire;
import com.bouami.exemple.copropriete.Models.Cotisation;


public class MainActivity extends Activity {
    public final static String ACCUEIL_CHOIX = "";
    private Activity activite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activite = this;
        CoproParametres.parametre.contentresolver = getContentResolver();
        setContentView(R.layout.activity_main);
        final Button btn_copro = (Button) findViewById(R.id.btn_copro);
        final Button btn_depenses = (Button) findViewById(R.id.btn_depenses);
        btn_copro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activite, AffichageCopro.class);
                intent.putExtra(ACCUEIL_CHOIX, 1);
                startActivity(intent);
            }
        });

        btn_depenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(activite, AffichageCopro.class);
//                intent.putExtra(ACCUEIL_CHOIX, 2);
//                startActivity(intent);
                CreerCopro(v);
            }
        });
    }

    public void CreerCopro(View view) {
/*        ContentValues values = new ContentValues();
//        String civi = ((AutoCompleteTextView) findViewById(R.id.civilite)).getText().toString();
//        String nom = ((EditText) findViewById(R.id.nom)).getText().toString();
//        String prenom = ((EditText) findViewById(R.id.prenom)).getText().toString();
//        String mail = ((EditText) findViewById(R.id.mail)).getText().toString();
//        String appart = ((EditText) findViewById(R.id.appart)).getText().toString();
        String civi = "Monsier";
        String nom = "Nom3";
        String prenom = "Prenom3";
        String mail = "nom3@free.fr";
        String appart = "E1Appart3";
        Coproprietaire lecopro = new Coproprietaire(civi,nom,prenom,mail,appart);
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_CIVILITE,civi);
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_NOM,nom);
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_PRENOM,prenom);
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_MAIL,mail);
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_APPART,appart);
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_CREATEDATE,lecopro.getCreer_le());
        CoproParametres.parametre.TABLE_EN_COURS = CoproParametres.parametre.TABLE_COPROPRIETAIRES;
        CoproParametres.parametre.CONTENT_URI_EN_COURS = CoproParametres.parametre.CONTENT_URI_COPROPRIETAIRES;
        Uri uri = getContentResolver().insert(CoproParametres.parametre.CONTENT_URI_EN_COURS, values);
        Toast.makeText(getBaseContext(),
                "le copropriétaire : " + lecopro.toString() + " a été inséré le "+lecopro.getCreer_le()+"---"+uri.getLastPathSegment()+"---"+uri.getEncodedUserInfo() , Toast.LENGTH_LONG).show();
        ContentValues valuescot = new ContentValues();
        lecopro.setId(Integer.valueOf(uri.getLastPathSegment()));
        Cotisation cotis = new Cotisation(lecopro,Double.valueOf("1250"));
        valuescot.put(CoproParametres.parametre.COLUMN_COTISATIONS_KEY_COPROPRIETAIRE,cotis.getLecoproprietaire().getId());
        valuescot.put(CoproParametres.parametre.COLUMN_COTISATIONS_SOMME,cotis.getSomme());
        valuescot.put(CoproParametres.parametre.COLUMN_COTISATIONS_DONNEELE,cotis.getDonnee_le());
        CoproParametres.parametre.TABLE_EN_COURS = CoproParametres.parametre.TABLE_COTISATIONS;
        CoproParametres.parametre.CONTENT_URI_EN_COURS = CoproParametres.parametre.CONTENT_URI_COTISATIONS;
        Uri uri2 = getContentResolver().insert(CoproParametres.parametre.CONTENT_URI_EN_COURS, valuescot);
        Toast.makeText(getBaseContext(),
                "la cotisation du  : " + cotis.getLecoproprietaire().getId() + " a été inséré avec "+ cotis.getSomme() , Toast.LENGTH_LONG).show();*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void show(String txt) {
        Toast.makeText(getBaseContext(), String.valueOf(txt), Toast.LENGTH_LONG).show();
    }
}
