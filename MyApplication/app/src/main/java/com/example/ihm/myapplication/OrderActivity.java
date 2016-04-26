package com.example.ihm.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import bddMenu.Plat;
import bddMenu.PlatDAO;
import tools.PlatAdapter;

public class OrderActivity extends AppCompatActivity{

    private double prixTotal;

    private List<Plat> menus;
    private List<Plat> entrees;
    private List<Plat> plats;
    private List<Plat> desserts;
    private List<Plat> boissons;

    private List<Plat> orders;

    PlatAdapter itemsAdapter;
    PlatAdapter itemsAdapter2;

    ListView listViewMenu;
    ListView listViewOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale mLocale = new Locale("fr");
        if (config.locale.equals(mLocale)) {
            config.locale = mLocale;
            getBaseContext().getResources().updateConfiguration(config, null);
        } else {
            config.locale = new Locale("en");
            getBaseContext().getResources().updateConfiguration(config, null);
        }

        PlatDAO bdMenu = PlatDAO.getInstance(this);
        bdMenu.open();

        prixTotal = 0.0;
        orders = new ArrayList<Plat>();
        menus = bdMenu.selectAll("menu");
        entrees = bdMenu.selectAll("entrée");
        plats = bdMenu.selectAll("plat");
        desserts = bdMenu.selectAll("dessert");
        boissons = bdMenu.selectAll("boisson");

        itemsAdapter =
                new PlatAdapter(this, menus);

        listViewMenu = (ListView) findViewById(R.id.listViewMenu);
        listViewMenu.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Plat item = (Plat)listViewMenu.getItemAtPosition(position);

                orders.add(item);
                prixTotal+=Double.valueOf(item.getPrix());
                ((TextView)findViewById(R.id.textViewPrix)).setText("Prix total: "+prixTotal+"€");
                itemsAdapter2.notifyDataSetChanged();
            }
        });
        listViewMenu.setAdapter(itemsAdapter);

        itemsAdapter2 =
                new PlatAdapter(this, orders);

        listViewOrder = (ListView) findViewById(R.id.listViewOrders);
        listViewOrder.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Plat item = (Plat)listViewOrder.getItemAtPosition(position);

                orders.remove(item);
                prixTotal-=Double.valueOf(item.getPrix());
                ((TextView)findViewById(R.id.textViewPrix)).setText("Prix total: "+prixTotal+"€");
                itemsAdapter2.notifyDataSetChanged();
            }
        });
        listViewOrder.setAdapter(itemsAdapter2);
    }

    public void switchCategory(View v){
        ListView listView = ((ListView) findViewById(R.id.listViewMenu));
        listView.setAdapter(null);
        PlatAdapter itemsAdapter;
        switch(v.getId()){
            case R.id.buttonmenus:
                itemsAdapter =
                        new PlatAdapter(this, menus);

                break;
            case R.id.buttonentrees:
                itemsAdapter =
                        new PlatAdapter(this, entrees);

                break;
            case R.id.buttonplats:
                itemsAdapter =
                        new PlatAdapter(this, plats);

                break;
            case R.id.buttondesserts:
                itemsAdapter =
                        new PlatAdapter(this, desserts);

                break;
            case R.id.buttonboissons:
                itemsAdapter =
                        new PlatAdapter(this, boissons);

                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }
        listView.setAdapter(itemsAdapter);
    }

    /** Switch back to main activity */
    public void backToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void order(View view){
        Intent intent = new Intent(this, RecapActivity.class);
        for(int i = 0; i<orders.size(); i++){
            intent.putExtra("ITEM_" + i, orders.get(i));
        }
        intent.putExtra("PRIX_TOTAL", "" + prixTotal);
        startActivity(intent);
    }

    public void callWaiter(View view){
        serveurBoiteMail.addAlerte();
    }
}
