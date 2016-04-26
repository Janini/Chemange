package com.example.ihm.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;

import java.util.Locale;

/**
 * Created by Tayl on 07/04/2016.
 */
public class LoadingActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        AlertDialog.Builder adb = new AlertDialog.Builder(LoadingActivity.this);

        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale mLocale = new Locale("fr");
        if (config.locale.equals(mLocale)) {
            config.locale = mLocale;
            getBaseContext().getResources().updateConfiguration(config, null);
            adb.setMessage("Votre commande est prète et arrivera d'ici peu!");
        } else {
            config.locale = new Locale("en");
            getBaseContext().getResources().updateConfiguration(config, null);
            adb.setMessage("Your order is ready and will be here soon!");
        }

        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dlg, int sumthin) {
                Intent i = new Intent(LoadingActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        });
        adb.show();
    }
}
