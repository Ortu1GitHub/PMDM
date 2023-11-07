package com.pmdm.practica22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pmdm.practica22.R;

public class Menu extends AppCompatActivity {

    private Button btnMedicion, btnConversor, btnConfiguracion, btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnMedicion = findViewById(R.id.btnMedicionTemperatura);
        btnConversor = findViewById(R.id.btnConversor);
        btnConfiguracion = findViewById(R.id.btnConfiguracion);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        btnMedicion.setOnClickListener(checkTemperature);
        btnConversor.setOnClickListener(checkConversion);
        btnConfiguracion.setOnClickListener(checkConfiguration);
        btnCerrarSesion.setOnClickListener(checkCloseSession);
    }

   private final View.OnClickListener checkTemperature = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(Menu.this, MedicionTemperatura.class);
            startActivity(i);
        }
    };

    private final View.OnClickListener checkConversion = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i= new Intent(Menu.this, Conversor.class);
            startActivity(i);
        }
    };
    private final View.OnClickListener checkConfiguration = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i= new Intent(Menu.this, Configuracion.class);
            startActivity(i);
        }
    };
    private final View.OnClickListener checkCloseSession = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i= new Intent(Menu.this, MainActivity.class);
            startActivity(i);
        }
    };
}