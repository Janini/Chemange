package com.example.ihm.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class cuisinierMainActivity extends Activity {
    private ImageButton update;
    private ListView listEntrees, listPlats, listDesserts, listBoissons;
    private String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuisinier_main);
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale mLocale = new Locale("fr");
        if (config.locale.equals(mLocale)) {
            config.locale = mLocale;
            getBaseContext().getResources().updateConfiguration(config, null);
        } else {
            config.locale = new Locale("en");
            getBaseContext().getResources().updateConfiguration(config, null);
        }



        TextView tv = (TextView) findViewById(R.id.cuisiniername);
        Intent i = getIntent();
        name = i.getStringExtra("name");
        tv.setText("Cuisinier "+name);


        update = (ImageButton) findViewById(R.id.updatelist2);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listEntrees = (ListView) findViewById(R.id.listEntrees);
                listPlats = (ListView) findViewById(R.id.listPlats);
                listDesserts = (ListView) findViewById(R.id.listDesserts);
                listBoissons = (ListView) findViewById(R.id.listBoissons);


                ArrayList<HashMap<String, String>> listItem = com.example.ihm.myapplication.cuisinierBoiteMail.getEntrees();
                ArrayList<HashMap<String, String>> listItem2 = com.example.ihm.myapplication.cuisinierBoiteMail.getPlats();
                ArrayList<HashMap<String, String>> listItem3 = com.example.ihm.myapplication.cuisinierBoiteMail.getDesserts();
                ArrayList<HashMap<String, String>> listItem4 = com.example.ihm.myapplication.cuisinierBoiteMail.getBoissons();


                final SimpleAdapter mSchedule = new SimpleAdapter (cuisinierMainActivity.this, listItem, R.layout.items_listview,
                        new String[] {"nom", "date"}, new int[] {R.id.titre, R.id.date});
                final SimpleAdapter mSchedule2 = new SimpleAdapter (cuisinierMainActivity.this, listItem2, R.layout.items_listview,
                        new String[] {"nom", "date"}, new int[] {R.id.titre, R.id.date});
                final SimpleAdapter mSchedule3 = new SimpleAdapter (cuisinierMainActivity.this, listItem3, R.layout.items_listview,
                        new String[] {"nom", "date"}, new int[] {R.id.titre, R.id.date});
                final SimpleAdapter mSchedule4 = new SimpleAdapter (cuisinierMainActivity.this, listItem4, R.layout.items_listview,
                        new String[] {"nom", "date"}, new int[] {R.id.titre, R.id.date});


                //ON LIE L'ADAPTER AUX LISTVIEW
                listEntrees.setAdapter(mSchedule);
                listPlats.setAdapter(mSchedule2);
                listDesserts.setAdapter(mSchedule3);
                listBoissons.setAdapter(mSchedule4);


                listEntrees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                        //MAP CONTIENT "TITRE" et "DATE"
                        //HashMap<String, String> map = (HashMap<String, String>) listAlertes.getItemAtPosition(position);

                        //BOITE DE DIALOGUE
                        AlertDialog.Builder adb = new AlertDialog.Builder(cuisinierMainActivity.this);

                        adb.setMessage("Entrée prête !");
                        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                                com.example.ihm.myapplication.cuisinierBoiteMail.deleteEntree(position);
                                listEntrees.setAdapter(mSchedule);
                                com.example.ihm.myapplication.serveurBoiteMail.addCommandes();
                            }
                        });
                        adb.show();
                    }

                });

                listPlats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                        //MAP CONTIENT "TITRE" et "DATE"
                        //HashMap<String, String> map = (HashMap<String, String>) listAlertes.getItemAtPosition(position);

                        //BOITE DE DIALOGUE
                        AlertDialog.Builder adb = new AlertDialog.Builder(cuisinierMainActivity.this);

                        adb.setMessage("Plat prêt !");
                        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                                com.example.ihm.myapplication.cuisinierBoiteMail.deletePlat(position);
                                listPlats.setAdapter(mSchedule2);
                                com.example.ihm.myapplication.serveurBoiteMail.addCommandes();

                            }
                        });
                        adb.show();
                    }
                });

                listDesserts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                        //MAP CONTIENT "TITRE" et "DATE"
                        //HashMap<String, String> map = (HashMap<String, String>) listAlertes.getItemAtPosition(position);

                        //BOITE DE DIALOGUE
                        AlertDialog.Builder adb = new AlertDialog.Builder(cuisinierMainActivity.this);

                        adb.setMessage("Dessert prêt !");
                        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                                com.example.ihm.myapplication.cuisinierBoiteMail.deleteDessert(position);
                                listDesserts.setAdapter(mSchedule3);
                                com.example.ihm.myapplication.serveurBoiteMail.addCommandes();
                            }
                        });
                        adb.show();
                    }
                });


                listBoissons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                        //MAP CONTIENT "TITRE" et "DATE"
                        //HashMap<String, String> map = (HashMap<String, String>) listAlertes.getItemAtPosition(position);

                        //BOITE DE DIALOGUE
                        AlertDialog.Builder adb = new AlertDialog.Builder(cuisinierMainActivity.this);

                        adb.setMessage("Boisson prête !");
                        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                                com.example.ihm.myapplication.cuisinierBoiteMail.deleteBoisson(position);
                                listBoissons.setAdapter(mSchedule4);
                                com.example.ihm.myapplication.serveurBoiteMail.addCommandes();
                            }
                        });
                        adb.show();
                    }
                });

                final AlertDialog.Builder dialog = new AlertDialog.Builder(cuisinierMainActivity.this).setMessage("Chargement...");
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

        Button deco = (Button) findViewById(R.id.button6);
        deco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(cuisinierMainActivity.this);

                adb.setMessage("Souhaitez-vous vous déconnecter ?");
                adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {
                       Intent i = new Intent(cuisinierMainActivity.this, MainActivity.class);
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
}