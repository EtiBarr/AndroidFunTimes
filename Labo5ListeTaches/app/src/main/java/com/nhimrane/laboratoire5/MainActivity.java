package com.nhimrane.laboratoire5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
// AdapterView.OnItemClickListener: nous permet de cliquer sur chaque item (voir onItemClick plus bas)
    private List<Tache> lesTaches;
    private TachesDao dao;
    private ListView lvTaches;
    private TachesAdapter adaptateur;
    private Button btnAjouterTache;
    private TextView tvNbTaches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lvTaches), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvTaches = findViewById(R.id.lvTaches);
        btnAjouterTache = findViewById(R.id.btnAjouterTache);
        tvNbTaches = findViewById(R.id.tvNombreTaches);

        btnAjouterTache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iAjoutTache = new Intent(v.getContext(), AjoutTacheActivity.class);
                startActivity(iAjoutTache);
            }
        });

        // quand on clique sur un item de la liste
        lvTaches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent iEditTache = new Intent(getApplicationContext(), EditTacheActivity.class);
                Tache tacheSelectionnee = (Tache)parent.getAdapter().getItem(position);
                String nom = tacheSelectionnee.getNom();
                String description = tacheSelectionnee.getDescription();
                iEditTache.putExtra("NOM_TACHE", nom);
                iEditTache.putExtra("TACHE_DESCRIPTION", description);
                startActivity(iEditTache);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dao = DaoSingleton.getInstance();
        lesTaches = dao.getTaches();
        adaptateur = new TachesAdapter(this,R.layout.tache_layout, lesTaches);
        lvTaches.setAdapter(adaptateur);
        tvNbTaches.setText(dao.getTaches().size() + " taches");
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//    }
}