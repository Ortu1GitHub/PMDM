package com.pmdm.practica22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Temperatura extends AppCompatActivity {

    private String nombre, apellidos, temperatura, poblacion,provincia;
    private TextView tvNombre, tvApellidos, tvGrados, tvPoblacion, tvProvincia;

    private boolean celsius, fahrenheit;

    private Button btnMenu, btnColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);
        getParameters();
        setViewParameters();
        setParameters();
        btnColor.setEnabled(false);
        changeButtonColor(temperatura, celsius, fahrenheit);
        btnMenu.setOnClickListener(goToMenu);
    }

    private void getParameters(){
        nombre = getIntent().getExtras().getString("nombre");
        apellidos = getIntent().getExtras().getString("apellidos");
        temperatura = getIntent().getExtras().getString("temperatura");
        poblacion = getIntent().getExtras().getString("poblacion");
        provincia = getIntent().getExtras().getString("provincia");
        celsius = getIntent().getExtras().getBoolean("celsius");
        fahrenheit = getIntent().getExtras().getBoolean("fahrenheit");

    }

    private void setViewParameters(){
        tvNombre = findViewById(R.id.tvNombre);
        tvApellidos = findViewById(R.id.tvApellidos);
        tvGrados = findViewById(R.id.tvGrados);
        tvPoblacion = findViewById(R.id.tvPoblacion);
        tvProvincia = findViewById(R.id.tvProvincia);
        btnMenu = findViewById(R.id.btnMenu);
        btnColor = findViewById(R.id.btnColor);
    }
    private void setParameters(){
        tvNombre.setText(nombre);
        tvApellidos.setText(apellidos);
        tvGrados.setText(temperatura);
        tvPoblacion.setText(poblacion);
        tvProvincia.setText(provincia);

    }

    private void changeButtonColor(String temperatura, boolean celsius, boolean fahrenheit){
        int temp = Integer.valueOf(temperatura);
        if ((temp > 38 && celsius) || (temp > 100 && fahrenheit)) {
            btnColor.setBackgroundColor(Color.parseColor("#EC0000"));
        }
    }

    private final View.OnClickListener goToMenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                Intent intent = new Intent(Temperatura.this,Menu.class);
                startActivity(intent);
        }
    };
}