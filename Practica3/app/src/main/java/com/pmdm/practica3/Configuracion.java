package com.pmdm.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class Configuracion extends AppCompatActivity {

    private Button btnGuardar;

    private Switch swC, swF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        swC = findViewById(R.id.swC);
        swF = findViewById(R.id.swF);
        btnGuardar = findViewById(R.id.btnGuardar);

        swC.setOnClickListener(displayCelsiusEnabled);
        swF.setOnClickListener(displayFahrenheitEnabled);
        btnGuardar.setOnClickListener(volverMenu);
    }

    private final View.OnClickListener displayCelsiusEnabled = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (swC.isChecked()) {
                Toast.makeText(Configuracion.this, "Celsius configurado...", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Configuracion.this, "Celsius desconfigurado...", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private final View.OnClickListener displayFahrenheitEnabled = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (swF.isChecked()) {
                Toast.makeText(Configuracion.this, "Fahrenheit configurado...", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Configuracion.this, "Fahrenheit desconfigurado...", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private final View.OnClickListener volverMenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(Configuracion.this, LoginActivity.class);
            startActivity(i);
        }
    };
}