package com.example.ihm.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import bddMenu.Plat;
import tools.PlatAdapter;

public class RecapActivity extends AppCompatActivity{

    private ArrayList<Plat> orders;
    private double prixTotal = 0.0;

    PlatAdapter itemsAdapter;

    ListView listViewOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recap);
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale mLocale = new Locale("fr");
        if (config.locale.equals(mLocale)) {
            config.locale = mLocale;
            getBaseContext().getResources().updateConfiguration(config, null);
        } else {
            config.locale = new Locale("en");
            getBaseContext().getResources().updateConfiguration(config, null);
        }

        Intent it = getIntent();
        Bundle extras = getIntent().getExtras();
        orders = new ArrayList<Plat>();
        if(extras != null){
            for(int i = 0; i<extras.size()-1;i++){
                orders.add((Plat) it.getSerializableExtra("ITEM_" + i));
            }
            prixTotal = Double.valueOf(extras.getString("PRIX_TOTAL"));
        }

        ((TextView)findViewById(R.id.textViewPrix)).setText(""+prixTotal+"€");

        itemsAdapter =
                new PlatAdapter(this, orders);

        listViewOrder = (ListView) findViewById(R.id.listViewRecap);
        listViewOrder.setAdapter(itemsAdapter);
    }

    /** Switch back to main activity */
    public void backToMain(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        startActivity(intent);
    }

    public void confirmed(View view){
        for(Plat p : orders){
            cuisinierBoiteMail.addEntree(p);
        }

        final ProgressDialog progDailog;
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale mLocale = new Locale("fr");
        if (config.locale.equals(mLocale)) {
            config.locale = mLocale;
            getBaseContext().getResources().updateConfiguration(config, null);
            progDailog = ProgressDialog.show(this, "Commande validée!",
                    "Votre repas est en train d'être préparé.\nMerci de patienter", true);
        } else {
            config.locale = new Locale("en");
            getBaseContext().getResources().updateConfiguration(config, null);
            progDailog = ProgressDialog.show(this, "Order validated!",
                    "Your meal is being prepared.\nPlease wait", true);
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    // sleep the thread, whatever time you want.
                    Thread.sleep(5000);
                    progDailog.dismiss();
                    Intent intent = new Intent(RecapActivity.this, LoadingActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                }
            }
        }.start();
    }
}
