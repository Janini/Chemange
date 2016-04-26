package tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ihm.myapplication.R;

import java.util.List;

import bddMenu.Plat;

/**
 * Created by Tayl on 11/04/2016.
 */
public class PlatAdapter extends ArrayAdapter<Plat> {
    public PlatAdapter(Context context, List<Plat> plats) {
        super(context, 0, plats);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Plat plat = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.affichage_plats, parent, false);
        }
        // Lookup view for data population
        TextView nomPlat = (TextView) convertView.findViewById(R.id.nomPlat);
        TextView prixPlat = (TextView) convertView.findViewById(R.id.prixPlat);
        // Populate the data into the template view using the data object
        nomPlat.setText(plat.getNom());
        prixPlat.setText(plat.getPrix());
        // Return the completed view to render on screen
        return convertView;
    }
}
