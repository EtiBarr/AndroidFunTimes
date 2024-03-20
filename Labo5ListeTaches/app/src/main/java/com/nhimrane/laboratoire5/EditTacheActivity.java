package com.nhimrane.laboratoire5;

import android.content.Intent;
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

public class EditTacheActivity extends AppCompatActivity {

    private EditText txtNom, txtDescription;
    private Spinner spEtatTache;
    private Button btnOk, btnAnnuler;
    private Tache tache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_tache);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNom = findViewById(R.id.etNomTache);
        txtDescription = findViewById(R.id.etDescriptionTache);
        spEtatTache = findViewById(R.id.spEtatTacheEdit);
        btnOk = findViewById(R.id.btnOK);
        btnAnnuler = findViewById(R.id.btnAnnuler);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tache.setDescription(txtDescription.getText().toString());
                tache.setEtat(spEtatTache.getSelectedItem().toString());
                DaoSingleton.getInstance().Modifier(tache);
                setResult(RESULT_OK);
                finish();
            }
        });

        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        Intent intent = getIntent();
        String nomTache = intent.getStringExtra("NOM_TACHE");
        TachesDao dao = DaoSingleton.getInstance();
        tache = dao.getTacheParNom(nomTache);
        txtNom.setText(tache.getNom());
        txtDescription.setText(tache.getDescription());
        for (int i=0;i<spEtatTache.getCount();i++)
            if (tache.getEtat().equals(spEtatTache.getItemAtPosition(i))) {
                spEtatTache.setSelection(i);
                break;
            }
    }
}