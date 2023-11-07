package com.pmdm.practica22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.pmdm.practica22.R;

public class MedicionTemperatura extends AppCompatActivity {

    private EditText etNombre, etApellidos, etTemperatura, etPoblacion, etProvincia;
    private Button btnFinalizar;
    private RadioButton rbCelsius, rbFahrenheit;

    private String nombre, apellidos, temperatura, poblacion, provincia;

    private boolean celsius, fahrenheit;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicion_temperatura);

        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etTemperatura = findViewById(R.id.etTemperatura);
        etPoblacion = findViewById(R.id.etPoblacion);
        etProvincia = findViewById(R.id.etProvincia);
        btnFinalizar = findViewById(R.id.btnMenu);
        rbCelsius = findViewById(R.id.rbCelsius);
        rbFahrenheit = findViewById(R.id.rbFahrenheit);

        btnFinalizar.setOnClickListener(clFinalizar);
    }

    private final View.OnClickListener clFinalizar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //Data is retrieved
            nombre = String.valueOf(etNombre.getText()).trim();
            apellidos= String.valueOf(etApellidos.getText()).trim();
            temperatura = String.valueOf(etTemperatura.getText()).trim();
            poblacion = String.valueOf(etPoblacion.getText()).trim();
            provincia = String.valueOf(etProvincia.getText()).trim();
            celsius = rbCelsius.isChecked();
            fahrenheit = rbFahrenheit.isChecked();

            //In case there is a field not informed a Toast is displayed. Otherwise data is passed to form summary
            if (nombre.equals("")|| apellidos.equals("")|| temperatura.equals("")|| poblacion.equals("")|| provincia.equals("")||temperatura.equals("")) {
                Toast.makeText(MedicionTemperatura.this, "No se ha informado algun campo.Vuelva a intentarlo... ", Toast.LENGTH_LONG).show();

            }else{
                Bundle bundle = new Bundle();
                bundle.putString("nombre",nombre);
                bundle.putString("apellidos",apellidos);
                bundle.putString("temperatura",temperatura);
                bundle.putString("poblacion",poblacion);
                bundle.putString("provincia",provincia);
                bundle.putBoolean("celsius",celsius);
                bundle.putBoolean("fahrenheit",fahrenheit);
                Intent i = new Intent(MedicionTemperatura.this, ResumenFormulario.class);
                startActivity(i.putExtras(bundle));
            }
        }
    };
}