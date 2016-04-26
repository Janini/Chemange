package com.example.ihm.myapplication;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import bddMenu.Plat;

;

/**
 * Created by Janice on 10/04/2016.
 */
public class cuisinierBoiteMail {
    private static ArrayList<HashMap<String, String>> entrees = new ArrayList<HashMap<String, String>>();
    private static ArrayList<HashMap<String, String>> plats = new ArrayList<HashMap<String, String>>();
    private static ArrayList<HashMap<String, String>> desserts = new ArrayList<HashMap<String, String>>();
    private static ArrayList<HashMap<String, String>> boissons = new ArrayList<HashMap<String, String>>();



    /*******************************
     * CUISINIER
     ************************************/

    public static ArrayList<HashMap<String, String>> getEntrees() {
        return entrees;
    }

    public static ArrayList<HashMap<String, String>> getPlats() {
        return plats;
    }

    public static ArrayList<HashMap<String, String>> getDesserts() {
        return desserts;
    }
    public static ArrayList<HashMap<String, String>> getBoissons() {
        return boissons;
    }




    public static void deleteEntree(int index) {
        entrees.remove(index);
    }

    public static void deletePlat(int index) {
        plats.remove(index);
    }

    public static void deleteDessert(int index) {
        desserts.remove(index);
    }

    public static void deleteBoisson(int index) {
        boissons.remove(index);
    }



    public static void addEntree(Plat entree) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("nom", entree.getNom() + ", x" + entree.getQuantite() + ", " + entree.getInfo());
        map.put("date", new SimpleDateFormat("dd:MMMM HH:mm a").format(Calendar.getInstance().getTime()));
        if (entree.getCategorie().equals("entrée"))
            entrees.add(map);
        if (entree.getCategorie().equals("plat"))
            plats.add(map);
        if (entree.getCategorie().equals("dessert"))
            desserts.add(map);
        if (entree.getCategorie().equals("boisson"))
            boissons.add(map);

    }
}