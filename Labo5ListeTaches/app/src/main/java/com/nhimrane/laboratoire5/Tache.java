package com.nhimrane.laboratoire5;

public class Tache {
    private String nom, description, etat;
    public Tache() {
    }
    public Tache(String nom, String description, String etat) {
        this.nom = nom;
        this.description = description;
        this.etat = etat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
