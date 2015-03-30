package com.bouami.exemple.copropriete;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;


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
                GestionDepenses(v);
            }
        });
    }

    public void GestionDepenses(View view) {

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
}
