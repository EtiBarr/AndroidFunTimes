package com.nhimrane.laboratoire5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TachesDao {
    private List<Tache> taches = new ArrayList<>();
    public TachesDao() {
        Tache t;
        String[] noms = {"Carte de transport", "Préparer l'intra", "Réunion de groupe",
                         "Trouver un stage", "Répondre aux courriels", " Devoir de physique",
                         "Examen de mathématiques", "Préparer le scrum de demain", "Réparer le vélo",
                         "Commencer le projet", "Présentation en histoire", "Faire les courses", "Examen final",
                        "Magasiner un nouveau ordi"};
        String[] descriptions = {"Renouveler la carte de transport", "Préparer l'intra", "Préparer la réunion de groupe",
                "Trouver un stage pour cet été", "Répondre aux courriels de la semaine de relâche ", "Rendre le devoir de physique",
                "Réviser le 3e chapitre pour l'examen de mathématiques", "Préparer le scrum de demain", "Réparer le vélo",
                "Commencer le projet Web/ServerJson", "Préparer la présentation en histoire", "Faire les courses cette fin de semaine",
                "Revoir les chapitres 4 à 7", "BestBuy, Costco, Ordinateurs mondial"};



        int x;
        for (int i=0;i< noms.length;i++) {
            t = new Tache();
            t.setNom(noms[i]);
            t.setDescription(descriptions[i]);
            Random random = new Random();
            x = random.nextInt(3);
            switch(x) {
                case 0 :
                    t.setEtat("initial"); break;
                case 1 :
                    t.setEtat("démarrée");break;
                case 2 :
                    t.setEtat("terminée");break;
            }
            taches.add(t);
        }
    }

    public List<String> getNomsDesTaches() {
        List<String> lesNoms = new ArrayList<>();
        for (Tache t:taches) {
            lesNoms.add(t.getNom());
        }
        return lesNoms;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public Tache getTacheParNom(String nom) {
        for (Tache t:taches) {
            if (nom.equals(t.getNom()))
                return t;
        }
        return null;
    }

    public boolean Ajouter(Tache tache) {
        Tache tacheDeMemeNom = getTacheParNom(tache.getNom());
        if (tacheDeMemeNom!=null)
            return false;
        return taches.add(tache);
    }

    public boolean Modifier(Tache tache) {
        boolean modifiee = false;
        for (Tache t: taches) {
            if (t.getNom().equals(tache.getNom())) {
                t.setEtat(tache.getEtat());
                t.setDescription(tache.getDescription());
                modifiee = true;
            }
        }
        return modifiee;
    }
}