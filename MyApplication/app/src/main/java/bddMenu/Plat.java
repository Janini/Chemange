package bddMenu;

import java.io.Serializable;

//REPRESENTE UN PLAT DANS LA BASE DE DONNEES
public class Plat implements Serializable {
    private long idPlat;
    //(entrée, plat principal, dessert, boisson)
    private String categorie;
    private String nom;
    private String prix;
    private String quantite;
    //Ingrédients + informations complémentaires
    private String info;
    //Path vers R.drawable
    private String photo;

    public Plat(String categorie, String nom, String prix, String quantite, String info, String photo) {
        super();
        this.categorie = categorie;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.info = info;
        this.photo = photo;
    }

    public Plat(int idPlat, String categorie, String nom, String prix, String quantite, String info, String photo) {
        super();
        this.idPlat = idPlat;
        this.categorie = categorie;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.info = info;
        this.photo = photo;
    }

    public Plat(){}

    public long getId() {
        return idPlat;
    }

    public void setId(long idPlat) {
        this.idPlat = idPlat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getQuantite() { return quantite; }

    public void setQuantite(String quantite){
        this.quantite = quantite;
    }

    public String toString(){
        return "idPlat = "+idPlat+", categorie = " + categorie + ", nom = "+nom+", pass = "+prix+", quantite = "+quantite+", info = " + info + ", photo = "+photo+"\n";
    }

}

