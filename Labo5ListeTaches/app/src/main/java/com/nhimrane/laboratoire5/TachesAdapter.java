package com.nhimrane.laboratoire5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TachesAdapter extends ArrayAdapter<Tache> {

    private List<Tache> taches;
    private Context contexte;
    private int viewResourceId;
    private Resources ressources;
    public TachesAdapter(@NonNull Context context, int resource, @NonNull List<Tache> taches) {
        super(context, resource, taches);

        this.contexte = context;
        this.viewResourceId = resource;
        this.ressources = contexte.getResources();
        this.taches = taches;
    }
    @SuppressLint("NewApi")
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) contexte.getSystemService(Context.
                    LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(this.viewResourceId, parent, false);
        }

        final Tache tache = this.taches.get(position);

        if (tache != null) {
            String etat = tache.getEtat();

            final TextView tvNom = (TextView) view.findViewById(R.id.tvNomTache);

            tvNom.setText(tache.getNom());

            switch (etat) {
                case "initial" :
                    tvNom.setTextColor(Color.RED);
                    break;
                case "démarrée" :
                    tvNom.setTextColor(Color.rgb(255,165,0));
                    break;
                case "terminée" :
                    tvNom.setTextColor(Color.GREEN);
                    break;
            }
        }
        return view;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
