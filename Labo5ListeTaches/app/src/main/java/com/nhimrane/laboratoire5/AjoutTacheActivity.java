package com.nhimrane.laboratoire5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AjoutTacheActivity extends AppCompatActivity {
    private Button btnOk, btnAnnuler;
    private EditText txtNom, txtDesc;
    private Spinner spEtat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ajout_tache);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnOk = findViewById(R.id.btnOKAjoutTache);
        btnAnnuler = findViewById(R.id.btnAnnulerAjoutTache);
        txtNom = findViewById(R.id.txtNomTache);
        txtDesc = findViewById(R.id.txtDescriptionTache);
        spEtat = findViewById(R.id.spEtatTache);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = txtNom.getText().toString();
                if (!"".equals(nom.trim())) { //nom de tache obligatoire pour l'ajout de tache
                    Tache t = new Tache();
                    t.setNom(nom);
                    t.setDescription(txtDesc.getText().toString());
                    t.setEtat(spEtat.getSelectedItem().toString());
                    if (DaoSingleton.getInstance().Ajouter(t)) {
                        setResult(RESULT_OK);
                    } else {
                        setResult(RESULT_CANCELED);
                    }
                    finish();
                }
            }
        });

        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}