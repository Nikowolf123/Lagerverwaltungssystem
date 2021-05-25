package org.openjfx;


import java.util.Date;

public class Products {

    public int id;
    public String name;
    public String kategorie;
    public Date ablaufdatum;
    public double preis;

    public Products(int id, String name, String kategorie, Date ablaufdatum, double preis) {
        this.id=id;
        this.name=name;
        this.kategorie=kategorie;
        this.ablaufdatum=ablaufdatum;
        this.preis = preis;
    }

    //ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Kategorie
    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    //Ablaufdatum
    public Date getAblaufdatum() {
        return ablaufdatum;
    }

    public void setAblaufdatum(Date ablaufdatum) {
        this.ablaufdatum = ablaufdatum;
    }

    //Preis
    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }
}