package com.pmdm.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OldMenu extends AppCompatActivity {

    private Button btnMedicion, btnConversor, btnConfiguracion, btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oldmenu);

        btnMedicion = findViewById(R.id.btnMedicion);
        btnConversor = findViewById(R.id.btnConversor);
        btnConfiguracion = findViewById(R.id.btnConfiguracion);
        btnCerrarSesion = findViewById(R.id.btnCerrar);

        btnMedicion.setOnClickListener(checkTemperature);
        btnConversor.setOnClickListener(checkConversion);
        btnConfiguracion.setOnClickListener(checkConfiguration);
        btnCerrarSesion.setOnClickListener(checkCloseSession);
    }

   private final View.OnClickListener checkTemperature = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(OldMenu.this, FormularioMedicion.class);
            startActivity(i);
        }
    };

    private final View.OnClickListener checkConversion = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i= new Intent(OldMenu.this, Conversor.class);
            startActivity(i);
        }
    };
    private final View.OnClickListener checkConfiguration = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i= new Intent(OldMenu.this, Configuracion.class);
            startActivity(i);
        }
    };
    private final View.OnClickListener checkCloseSession = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i= new Intent(OldMenu.this, LoginActivity.class);
            startActivity(i);
        }
    };
}