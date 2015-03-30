package com.bouami.exemple.copropriete;

import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bouami.exemple.copropriete.Models.Coproprietaire;


public class AffichageCopro extends FragmentActivity implements CoproprietairesFragment.OnFragmentInteractionListener {
    protected Fragment frag;
    private Cursor mcursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_copro);
        Intent intent = getIntent();
        Integer choix_accueil = intent.getIntExtra(MainActivity.ACCUEIL_CHOIX, 0);
        if (savedInstanceState == null) {
            if (choix_accueil==1) {
                frag = new CoproprietairesFragment();
            } else if (choix_accueil==2) {
                frag = new DepensesFragment();
            } else {
                frag = null;
            }

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_affichage, frag).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_affichage_copro, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
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

    @Override
    public void onFragmentInteraction(Cursor c) {
        this.mcursor = c;
        Remplir_Champs_Copro(c);
    }

    public void CreerCopro(View v) {
        final ContentValues values = new ContentValues();
        AutoCompleteTextView civicopro = (AutoCompleteTextView) findViewById(R.id.civilite);
        TextView nomcopro = (TextView) findViewById(R.id.nom);
        TextView prenomcopro = (TextView) findViewById(R.id.prenom);
        TextView mailcopro = (TextView) findViewById(R.id.mail);
        TextView appartcopro = (TextView) findViewById(R.id.appartement);
        String civi = civicopro.getText().toString();
        String nom = nomcopro.getText().toString();
        String prenom = prenomcopro.getText().toString();
        String mail = mailcopro.getText().toString();
        String appart = appartcopro.getText().toString();
        Coproprietaire lecopro = new Coproprietaire(civi,nom,prenom,mail,appart);
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_CIVILITE,civi);
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_NOM,nom);
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_PRENOM,prenom);
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_MAIL,mail);
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_APPART,appart);
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_CREATEDATE,lecopro.getCreer_le());
        Uri uri;
        uri = CoproParametres.parametre.contentresolver.insert(CoproParametres.parametre.CONTENT_URI_EN_COURS, values);
        if (uri!=null) {
            show("Le copropriétaire " + lecopro.toString() + " a été enregistré avec succès.");
            Vider_Champs_Copro();

        }
    }

    public void SupprimerCopro(View v) {
        Coproprietaire lecopro = new Coproprietaire(mcursor);
        Uri uri = Uri.parse("content://" + CoproParametres.parametre.AUTHORITY + "/" + CoproParametres.parametre.TABLE_COPROPRIETAIRES+"/"+lecopro.getId());
        Integer res = CoproParametres.parametre.contentresolver.delete(uri,null,null);
        if (res>0) {
            show("Le copropriétaire "+lecopro.toString()+" a été enregistré avec succès.");
            Vider_Champs_Copro();
        } else {
            show("Erreur lors de la suppression du copropriétaire "+lecopro.toString());
        }
    }

    public void ModifierCopro(View v) {
        Coproprietaire lecopro = new Coproprietaire(mcursor);
        final ContentValues values = new ContentValues();
        AutoCompleteTextView civicopro = (AutoCompleteTextView) findViewById(R.id.civilite);
        TextView nomcopro = (TextView) findViewById(R.id.nom);
        TextView prenomcopro = (TextView) findViewById(R.id.prenom);
        TextView mailcopro = (TextView) findViewById(R.id.mail);
        TextView appartcopro = (TextView) findViewById(R.id.appartement);
        String civi = civicopro.getText().toString();
        String nom = nomcopro.getText().toString();
        String prenom = prenomcopro.getText().toString();
        String mail = mailcopro.getText().toString();
        String appart = appartcopro.getText().toString();
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_CIVILITE,civi);
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_NOM,nom);
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_PRENOM,prenom);
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_MAIL,mail);
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_APPART,appart);
        values.put(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_CREATEDATE,lecopro.getCreer_le());
        Uri uri = Uri.parse("content://" + CoproParametres.parametre.AUTHORITY + "/" + CoproParametres.parametre.TABLE_COPROPRIETAIRES+"/"+lecopro.getId());
        Integer res = CoproParametres.parametre.contentresolver.update(uri, values,null,null);
        if (res>0) {
            show("Le copropriétaire "+lecopro.toString()+" a été mis à jour avec succès.");
            Vider_Champs_Copro();
        } else {
            show("Erreur lors de la mise à jour du copropriétaire "+lecopro.toString());
        }
    }

    private void Vider_Champs_Copro() {
        AutoCompleteTextView civicopro = (AutoCompleteTextView) findViewById(R.id.civilite);
        TextView nomcopro = (TextView) findViewById(R.id.nom);
        TextView prenomcopro = (TextView) findViewById(R.id.prenom);
        TextView mailcopro = (TextView) findViewById(R.id.mail);
        TextView appartcopro = (TextView) findViewById(R.id.appartement);
        civicopro.setText(null);
        nomcopro.setText(null);
        prenomcopro.setText(null);
        mailcopro.setText(null);
        appartcopro.setText(null);
        findViewById(R.id.btn_addcopro).setVisibility(View.VISIBLE);
        findViewById(R.id.btn_modificopro).setVisibility(View.INVISIBLE);
        findViewById(R.id.btn_deletecopro).setVisibility(View.INVISIBLE);
    }

    private void Remplir_Champs_Copro(Cursor c) {
        final String civicopro = c.getString(c.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_CIVILITE));
        final String nomcopro = c.getString(c.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_NOM));
        final String prenomcopro = c.getString(c.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_PRENOM));
        final String mailcopro = c.getString(c.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_MAIL));
        final String appartcopro = c.getString(c.getColumnIndexOrThrow(CoproParametres.parametre.COLUMN_COPROPRIETAIRE_APPART));
        final AutoCompleteTextView civilite = (AutoCompleteTextView) findViewById(R.id.civilite);
        final TextView nom = (TextView) findViewById(R.id.nom);
        final TextView prenom = (TextView) findViewById(R.id.prenom);
        final TextView mail = (TextView) findViewById(R.id.mail);
        final TextView appart = (TextView) findViewById(R.id.appartement);
        civilite.setText(civicopro);
        nom.setText(nomcopro);
        prenom.setText(prenomcopro);
        mail.setText(mailcopro);
        appart.setText(appartcopro);
        findViewById(R.id.btn_addcopro).setVisibility(View.INVISIBLE);
        findViewById(R.id.btn_modificopro).setVisibility(View.VISIBLE);
        findViewById(R.id.btn_deletecopro).setVisibility(View.VISIBLE);
    }

    private void show(String txt) {
        Toast.makeText(getBaseContext(), String.valueOf(txt), Toast.LENGTH_LONG).show();
    }
}
