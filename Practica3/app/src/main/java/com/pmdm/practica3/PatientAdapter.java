package com.pmdm.practica3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class PatientAdapter extends ArrayAdapter<Patient> {

    public PatientAdapter(Context context, List<Patient> patients) {
        super(context, 0, patients);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Obtener el objeto Patient para esta posición
        Patient patient = getItem(position);

        // Reutilizar la vista si ya existe, de lo contrario, inflarla
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        // Obtener la referencia al TextView en el layout
        TextView textView = convertView.findViewById(android.R.id.text1);

        // Establecer el texto del TextView con el nombre del paciente
        if (patient != null) {
            textView.setText(patient.toString());
        }

        return convertView;
    }
}

