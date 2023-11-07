package com.pmdm.practica22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.pmdm.practica22.R;

public class Configuracion extends AppCompatActivity {

    private Button btnGuardar;

    private Switch swC,swF;

    private boolean celsius, fahrenheit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        swC =  findViewById(R.id.swCelsius);
        swF =  findViewById(R.id.swFahrenheit);
        btnGuardar = findViewById(R.id.btnGuardar);

        swC.setOnClickListener(displayCelsiusEnabled);
        swF.setOnClickListener(displayFahrenheitEnabled);
        btnGuardar.setOnClickListener(volverMenu);
    }

    private final View.OnClickListener displayCelsiusEnabled = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(swC.isChecked()){
                Toast.makeText(Configuracion.this,"Celsius configurado...",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(Configuracion.this,"Celsius desconfigurado...",Toast.LENGTH_SHORT).show();
            }
        }
    };

    private final View.OnClickListener displayFahrenheitEnabled = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(swF.isChecked()){
                Toast.makeText(Configuracion.this,"Fahrenheit configurado...",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(Configuracion.this,"Fahrenheit desconfigurado...",Toast.LENGTH_SHORT).show();
            }
        }
    };

    private final View.OnClickListener volverMenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(Configuracion.this, MainActivity.class);
            startActivity(i);
        }
    };
}