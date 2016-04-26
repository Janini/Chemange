package com.example.ihm.myapplication;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import bddUser.UserDAO;

;

/**
 * Created by 3201055 on 05/04/16.
 */
public class serveurMainActivity extends Activity {

    private EditText input;
    private UserDAO dao;
    private ListView listAlertes, listCommandes, listFini;
    private ImageButton update;
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serveur_main);
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale mLocale = new Locale("fr");
        if (config.locale.equals(mLocale)) {
            config.locale = mLocale;
            getBaseContext().getResources().updateConfiguration(config, null);
        } else {
            config.locale = new Locale("en");
            getBaseContext().getResources().updateConfiguration(config, null);
        }
        dao = UserDAO.getInstance(this);



        //AFFICHAGE DU NOM DU SERVEUR
        TextView tv = (TextView) findViewById(R.id.serveur);
        Intent i = getIntent();
        name = i.getStringExtra("name");
        tv.setText("Serveur "+name);
        tv.setTextSize(30);







        //RECUPERE LES APPELS DES CLIENTS ET LES COMMANDES A RECUPERER
        update = (ImageButton) findViewById(R.id.updatelist);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //MAJ DU CHAMP ALERTE
                listAlertes = (ListView) findViewById(R.id.listalertes);
                listCommandes = (ListView) findViewById(R.id.listcommandes);


                ArrayList<HashMap<String, String>> listItem = serveurBoiteMail.getAlertes();
                ArrayList<HashMap<String, String>> listItem2 = serveurBoiteMail.getCommandes();


                final SimpleAdapter mSchedule = new SimpleAdapter (serveurMainActivity.this, listItem, R.layout.items_listview,
                        new String[] {"titre", "date"}, new int[] {R.id.titre, R.id.date});
                final SimpleAdapter mSchedule2 = new SimpleAdapter (serveurMainActivity.this, listItem2, R.layout.items_listview,
                        new String[] {"titre", "date"}, new int[] {R.id.titre, R.id.date});


                //ON LIE L'ADAPTER AUX LISTVIEW
                listAlertes.setAdapter(mSchedule);
                listCommandes.setAdapter(mSchedule2);


                //LISTENER SUR LES ALERTES
                listAlertes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                        //MAP CONTIENT "TITRE" et "DATE"
                        //HashMap<String, String> map = (HashMap<String, String>) listAlertes.getItemAtPosition(position);

                        //BOITE DE DIALOGUE
                        AlertDialog.Builder adb = new AlertDialog.Builder(serveurMainActivity.this);

                        adb.setMessage("J'arrive !");
                        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                                serveurBoiteMail.deleteAlerte(position);
                                listAlertes.setAdapter(mSchedule);
                            }
                        });
                        adb.show();
                    }

                });

                //LISTENER SUR LES COMMANDES
                listCommandes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                        //MAP CONTIENT "TITRE" et "DATE"
                        //HashMap<String, String> map = (HashMap<String, String>) listCommandes.getItemAtPosition(position);

                        //BOITE DE DIALOGUE
                        AlertDialog.Builder adb = new AlertDialog.Builder(serveurMainActivity.this);

                        adb.setMessage("Commande récupérée");
                        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                                serveurBoiteMail.deleteCommande(position);
                                listCommandes.setAdapter(mSchedule2);
                            }
                        });
                        adb.show();
                    }

                });

                final AlertDialog.Builder dialog = new AlertDialog.Builder(serveurMainActivity.this).setMessage("Chargement...");
                final AlertDialog alert = dialog.create();
                alert.show();

                final Handler handler  = new Handler();
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (alert.isShowing()) {
                            alert.dismiss();
                        }
                    }
                };

                alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        handler.removeCallbacks(runnable);
                    }
                });

                handler.postDelayed(runnable, 2000);


            }

        });

        new Handler().postDelayed(new Runnable() {
            public void run() {
                update.performClick();

            }
        }, 1);




        //AFFICHE LE MENU
        Button menu = (Button) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder menudialog = new AlertDialog.Builder(serveurMainActivity.this);
                LayoutInflater lay = LayoutInflater.from(serveurMainActivity.this);
                final View view = lay.inflate(R.layout.serveur_menu, null);
                menudialog.setView(view);

                menudialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {

                    }
                });
                menudialog.show();
            }
        });



        Button deco = (Button) findViewById(R.id.button4);
        deco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(serveurMainActivity.this);

                adb.setMessage("Souhaitez-vous vous déconnecter ?");
                adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {
                        Intent i = new Intent(serveurMainActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                });
                adb.show();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }


    private Dialog toast() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Supprimer un client");
        builder.setMessage("Identifiant : ");

        // Use an EditText view to get user input.
        input = new EditText(this);
        ListView itemList = new ListView(this);
        input.setId(0);
        builder.setView(input);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                final String value = input.getText().toString();
                new AlertDialog.Builder(serveurMainActivity.this)
                        .setTitle("Confirmation")
                        .setMessage("Souhaitez-vous supprimer "+value+ " ? ")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //OUVERTURE BDD
                                dao.open();
                                //SUPPRESSION DU CLIENT DANS LA BD
                                if (dao.deleteClient(input.getText().toString()) > 0) {
                                    final AlertDialog.Builder menudialog = new AlertDialog.Builder(serveurMainActivity.this);
                                    menudialog.setMessage(value+" a bien été supprimé.");
                                    menudialog.setNeutralButton("OK", null);
                                    menudialog.show();
                                } else {
                                    final AlertDialog.Builder menudialog = new AlertDialog.Builder(serveurMainActivity.this);
                                    menudialog.setMessage("Impossible de supprimer "+value+".");
                                    menudialog.setNeutralButton("OK",null);
                                    menudialog.show();
                                }

                                dao.close();
                            }})
                        .setNegativeButton("Non", null).show();
                return;
            }
        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        return builder.create();
    }



}