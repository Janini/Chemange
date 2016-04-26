package com.example.ihm.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.Locale;

import bddMenu.Plat;
import bddMenu.PlatDAO;
import bddUser.User;
import bddUser.UserDAO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale mLocale = new Locale("fr");
        if (config.locale.equals(mLocale)) {
            config.locale = mLocale;
            getBaseContext().getResources().updateConfiguration(config, null);
            ImageButton imgB;
            imgB = (ImageButton) findViewById(R.id.imageButtonFR);
            imgB.setImageResource(R.mipmap.frflag);
            imgB = (ImageButton) findViewById(R.id.imageButtonUK);
            imgB.setImageResource(R.mipmap.ukflag_bw);
        } else {
            config.locale = new Locale("en");
            getBaseContext().getResources().updateConfiguration(config, null);
            ImageButton imgB;
            imgB = (ImageButton) findViewById(R.id.imageButtonFR);
            imgB.setImageResource(R.mipmap.frflag_bw);
            imgB = (ImageButton) findViewById(R.id.imageButtonUK);
            imgB.setImageResource(R.mipmap.ukflag);
        }

        Plat menu1 = new Plat("menu","Menu du jour","18.00","1","Oeufs durs, Hachis Parmentier, Boule de glaçe et café","photo");
        Plat menu2 = new Plat("menu","Menu enfant","11.00","1","Steak haché, Frites, Jus d'orange","photo");

        Plat entree1 = new Plat("entrée","Oeuf Dur","2.00","1","","photo");
        Plat entree2 = new Plat("entrée","Crudités","2.50","1","","photo");
        Plat entree3 = new Plat("entrée","Taboulé","3.00","1","","photo");

        Plat plat1 = new Plat("plat","PizzAnanas","12.50","1","","photo");
        Plat plat2 = new Plat("plat","PizzAnchois","11.50","1","","photo");
        Plat plat3 = new Plat("plat","PizzArtichaut","9.20","1","","photo");
        Plat plat4 = new Plat("plat","PizzAvocat","9.20","1","","photo");

        Plat dessert1 = new Plat("dessert","Tarte aux pommes","6.00","1","Pommes","photo");
        Plat dessert2 = new Plat("dessert","Crème Brulée","5.50","1","","photo");
        Plat dessert3 = new Plat("dessert","Fondant au chocolat","5.50","1","","photo");

        Plat boisson1 = new Plat("boisson","Piña Colada","9.50","1","Ananas, Noix de coco","photo");
        Plat boisson2 = new Plat("boisson","Coca Cola","1.50","1","Sucre","photo");
        Plat boisson3 = new Plat("boisson","Jus d'orange","2.00","1","Sans orange","photo");

        PlatDAO bdMenu = PlatDAO.getInstance(this);
        //bddMenu.DatabaseHandler bd = new bddMenu.DatabaseHandler(this);
        //bd.onUpgrade(bdMenu.getDb(),1,2);
        bdMenu.open();
        bdMenu.deleteAll();
        bdMenu.add(menu1);
        bdMenu.add(menu2);
        bdMenu.add(entree1);
        bdMenu.add(entree2);
        bdMenu.add(entree3);
        bdMenu.add(plat1);
        bdMenu.add(plat2);
        bdMenu.add(plat3);
        bdMenu.add(dessert1);
        bdMenu.add(dessert2);
        bdMenu.add(dessert3);
        bdMenu.add(boisson1);
        bdMenu.add(boisson2);
        bdMenu.add(boisson3);
        bdMenu.close();

        User u = new User("client1","123","client");
        User u1 = new User("serveur1","123","serveur");
        User u2 = new User("cuisinier1","123","cuisinier");
        UserDAO bdUser = UserDAO.getInstance(this);
        //bddUser.DatabaseHandler bd2 = new bddUser.DatabaseHandler(this);
        //bd2.onUpgrade(bdUser.getDb(), 1, 1);
        bdUser.open();
        bdUser.add(u);
        bdUser.add(u1);
        bdUser.add(u2);
        bdUser.close();
    }



    /** Switch to login activity */
    public void login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void order(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }

    public void switchFR(View view){
        Locale mLocale = new Locale("fr");
        Locale.setDefault(mLocale);
        Configuration config = getBaseContext().getResources().getConfiguration();
        if (!config.locale.equals(mLocale))
        {
            config.locale = mLocale;
            getBaseContext().getResources().updateConfiguration(config, null);
            ImageButton imgB;
            imgB = (ImageButton)findViewById(R.id.imageButtonFR);
            imgB.setImageResource(R.mipmap.frflag);
            imgB = (ImageButton)findViewById(R.id.imageButtonUK);
            imgB.setImageResource(R.mipmap.ukflag_bw);
            recreate();
        }
    }

    public void switchEN(View view){
        Locale mLocale = new Locale("en");
        Locale.setDefault(mLocale);
        Configuration config = getBaseContext().getResources().getConfiguration();
        if (!config.locale.equals(mLocale))
        {
            config.locale = mLocale;
            getBaseContext().getResources().updateConfiguration(config, null);
            ImageButton imgB;
            imgB = (ImageButton)findViewById(R.id.imageButtonFR);
            imgB.setImageResource(R.mipmap.frflag_bw);
            imgB = (ImageButton)findViewById(R.id.imageButtonUK);
            imgB.setImageResource(R.mipmap.ukflag);
            recreate();
        }
    }
}