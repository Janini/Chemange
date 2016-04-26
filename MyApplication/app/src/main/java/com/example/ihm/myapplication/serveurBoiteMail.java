package com.example.ihm.myapplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

;


public class serveurBoiteMail {
    //APPELS DE CLIENTS
    private static ArrayList<HashMap<String, String>> alertes = new ArrayList<HashMap<String, String>>();
    //COMMANDES A RECUPERER EN CUISINE
    private static ArrayList<HashMap<String, String>> commandes = new ArrayList<HashMap<String, String>>();


    /*******************************
     * SERVEUR
     ************************************/

    public static ArrayList<HashMap<String, String>> getAlertes() {
        return alertes;
    }

    public static ArrayList<HashMap<String, String>> getCommandes() {
        return commandes;
    }


    public static void deleteAlerte(int index) {
        alertes.remove(index);
    }

    public static void deleteCommande(int index) {
        commandes.remove(index);
    }


    /*******************************
     * CLIENT (commande)
     ***********************************/


    public static void addAlerte() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("titre", "APPEL - TABLE " + Integer.valueOf(new Random().nextInt(10) + 1));
        map.put("date", new SimpleDateFormat("dd:MMMM HH:mm a").format(Calendar.getInstance().getTime()));
        alertes.add(map);
    }

    public static void addCommandes() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("titre", "RECUPERER COMMANDE " + Integer.valueOf(new Random().nextInt(5) + 1));
        map.put("date", new SimpleDateFormat("dd:MMMM HH:mm a").format(Calendar.getInstance().getTime()));
        commandes.add(map);
    }
}
