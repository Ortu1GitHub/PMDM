package com.pmdm.practica3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Obtener el objeto Patient para esta posición
        Patient patient = getItem(position);

        // Reutilizar la vista si ya existe, de lo contrario, inflarla
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_patient, parent, false);
        }

        // Obtener la referencia al TextView en el layout
        TextView textView1 = convertView.findViewById(android.R.id.text1);

        TextView textView2 = convertView.findViewById(android.R.id.text2);


        // Muestra los datos del paciente
        if (patient != null) {
            textView1.setText(patient.getName() + " " + patient.getSurname());
            textView2.setText(patient.getCity() + " " + patient.getProvince() + " Temperatura : " + patient.getTemperature());
        }

        return convertView;

    }

}

