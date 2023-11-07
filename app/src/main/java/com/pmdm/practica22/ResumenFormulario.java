package com.pmdm.practica22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pmdm.practica22.R;

public class ResumenFormulario extends AppCompatActivity {

    private String nombre, apellidos, temperatura, poblacion,provincia;
    private TextView tvNombre, tvApellidos, tvGrados, tvPoblacion, tvProvincia;

    private boolean celsius, fahrenheit;

    private Button btnMenu, btnColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);

        //Values are obtained from intent
        nombre = getIntent().getExtras().getString("nombre");
        apellidos = getIntent().getExtras().getString("apellidos");
        temperatura = getIntent().getExtras().getString("temperatura");
        poblacion = getIntent().getExtras().getString("poblacion");
        provincia = getIntent().getExtras().getString("provincia");
        celsius = getIntent().getExtras().getBoolean("celsius");
        fahrenheit = getIntent().getExtras().getBoolean("fahrenheit");

        tvNombre = findViewById(R.id.tvNombre);
        tvApellidos = findViewById(R.id.tvApellidos);
        tvGrados = findViewById(R.id.tvGrados);
        tvPoblacion = findViewById(R.id.tvPoblacion);
        tvProvincia = findViewById(R.id.tvProvincia);
        btnMenu = findViewById(R.id.btnMenu);
        btnColor = findViewById(R.id.btnColor);

        //Data configured in form summary
        tvNombre.setText(nombre);
        tvApellidos.setText(apellidos);
        tvGrados.setText(temperatura);
        tvPoblacion.setText(poblacion);
        tvProvincia.setText(provincia);

        //Button enabled with its color
        checkTemperature(temperatura, celsius, fahrenheit);
        btnMenu.setOnClickListener(goToMenu);
    }

    private void checkTemperature(String temperatura, boolean celsius, boolean fahrenheit){
        int aux = Integer.valueOf(temperatura);
        if ((aux > 38 && celsius) || (aux > 100 && fahrenheit)) {
            btnColor.setBackgroundColor(Color.parseColor("RED"));
        }else{
            btnColor.setBackgroundColor(Color.parseColor("GREEN"));
        }
    }
    private final View.OnClickListener goToMenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                Intent i = new Intent(ResumenFormulario.this,Menu.class);
                startActivity(i);
        }
    };
}