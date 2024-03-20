package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    boolean gameActive = true;
    // Valeur de JoueurActif : 0 -> X | 1 -> O
    int JoueurActif = 0;
    int[] EtatDuJeu = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // État du jeu :
    //    0 -> X
    //    1 -> O
    //    2 -> Nul

    // Toutes les positions gagnates dans un array 2D
    int[][] PositionsGagnantes = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // les lignes
                                 {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // les colones
                                 {0, 4, 8}, {2, 4, 6}};            // les diagonales
    public static int compteur = 0;

    TextView txtAffichage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAffichage = findViewById(R.id.etat);
    }

    // les click des 9 cases
    public void onClick(View view) {

        ImageButton img = (ImageButton) view;
        // J'ai tagué tous les boutons dans XML : 0,1,..,8
        int tappedImage = Integer.parseInt(img.getTag().toString());

        // On appelle la méthode ReinitialiserJeu si quelqu'un gagne
        // ou si les cases sont pleines
        if (!gameActive) {
            ReinitialiserLeJeu(view);
            compteur = 0;
        }

        // if the tapped image is empty
        if (EtatDuJeu[tappedImage] == 2) {
            compteur++;

            // dernière case jouée
            if (compteur == 9) {
                gameActive = false; // réinitialiser le jeux
            }

            EtatDuJeu[tappedImage] = JoueurActif; // mémoriser la position

            img.setTranslationY(-1000f); // cela donnera un effet de mouvement à l'image

            // changer le joueur courant de 0 à 1 ou 1 à 0
            if (JoueurActif == 0) {
                // afficher l'image png X
                img.setImageResource(R.drawable.player_x);
                JoueurActif = 1;

                // Metttre à jour l'affichage
                txtAffichage.setText(getResources().getString(R.string.joueur_o_gagne_appuyer_jouer));
            } else {
                // afficher l'image png O
                img.setImageResource(R.drawable.player_o);
                JoueurActif = 0;

                // Metttre à jour l'affichage
                txtAffichage.setText(getResources().getString(R.string.joueur_x_gagne_appuyer_jouer));
            }
            img.animate().translationYBy(1000f).setDuration(300); // Le mouvement de l'image sur l'axe des Y
        }
        int flag = 0;

        if (compteur > 4) { // Vérifiez si un joueur a gagné si le compteur est > 4
                            // car au moins 5 taps sont nécessaires pour déclarer un gagnant.
            for (int[] winPosition : PositionsGagnantes) {
                if (EtatDuJeu[winPosition[0]] == EtatDuJeu[winPosition[1]] &&
                        EtatDuJeu[winPosition[1]] == EtatDuJeu[winPosition[2]] &&
                        EtatDuJeu[winPosition[0]] != 2) {
                    flag = 1;

                    // Quelqu'un a gagné! Afficher le gagnant
                    String winnerStr;

                    // On doit appeler la méthode pour réinitialiser le jeu
                    gameActive = false;
                    if (EtatDuJeu[winPosition[0]] == 0) {
                        winnerStr = getResources().getString(R.string.joueur_x_gagne);
                    } else {
                        winnerStr = getResources().getString(R.string.joueur_o_gagne);
                    }
                    // Mettre à jour l'affichage pour l'annonce du gagnant
                    txtAffichage.setText(winnerStr);
                }
            }
            // définir le statut si le match est nul
            if (compteur == 9 && flag == 0) {
                txtAffichage.setText(getResources().getString(R.string.match_nul));
            }
        }
    }

    public void ReinitialiserLeJeu(View view) {
        gameActive = true;
        JoueurActif = 0;
        // on remet toutes les positions à 2 (nul)
        Arrays.fill(EtatDuJeu, 2);
        // faire disparaitre toutes les images
        ((ImageButton) findViewById(R.id.button00)).setImageResource(0);
        ((ImageButton) findViewById(R.id.button01)).setImageResource(0);
        ((ImageButton) findViewById(R.id.button02)).setImageResource(0);
        ((ImageButton) findViewById(R.id.button10)).setImageResource(0);
        ((ImageButton) findViewById(R.id.button11)).setImageResource(0);
        ((ImageButton) findViewById(R.id.button12)).setImageResource(0);
        ((ImageButton) findViewById(R.id.button20)).setImageResource(0);
        ((ImageButton) findViewById(R.id.button21)).setImageResource(0);
        ((ImageButton) findViewById(R.id.button22)).setImageResource(0);

        txtAffichage.setText(getResources().getString(R.string.le_tour_de_x));
    }


    // Pour relancer la partie
    public void onClickReJouer(View view) {
        ReinitialiserLeJeu(view);
    }
}