package com.pmdm.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormularioMedicion extends AppCompatActivity {
    private EditText etNombre, etApellidos, etTemperatura, etPoblacion, etProvincia;
    private Button btnFinalizar;
    private RadioButton rbCelsius, rbFahrenheit;

    private String nombre, apellidos, temperatura, poblacion, provincia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_medicion);

        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etTemperatura = findViewById(R.id.etTemperatura);
        etPoblacion = findViewById(R.id.etPoblacion);
        etProvincia = findViewById(R.id.etProvincia);
        btnFinalizar = findViewById(R.id.btnMenu);
        rbCelsius = findViewById(R.id.rbC);
        rbFahrenheit = findViewById(R.id.rbF);

        btnFinalizar.setOnClickListener(clFinalizar);
    }

    private final View.OnClickListener clFinalizar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            // Retrieve data after checking for empty fields
            nombre = String.valueOf(etNombre.getText()).trim();
            apellidos = String.valueOf(etApellidos.getText()).trim();
            temperatura = String.valueOf(etTemperatura.getText()).trim();
            poblacion = String.valueOf(etPoblacion.getText()).trim();
            provincia = String.valueOf(etProvincia.getText()).trim();

            if (nombre.equals("") || apellidos.equals("") || temperatura.equals("") || poblacion.equals("") || provincia.equals("")) {
                Toast.makeText(FormularioMedicion.this, "No se ha informado algún campo. Vuelva a intentarlo...", Toast.LENGTH_LONG).show();
            } else {
                // Data is retrieved from WS
                String format = rbCelsius.isChecked() ? "1" : "2";

                addNewPatientWS(nombre, apellidos, temperatura, poblacion, provincia, format, new ValidationCallback() {
                    @Override
                    public void onValidationResult(String id) {
                        if (id != null) {
                            // New register saved successfully
                            Intent i = new Intent(FormularioMedicion.this, ResumenFormulario.class);
                            i.putExtra("clave", id);
                            startActivity(i);
                        }
                    }
                });
            }
        }
    };

    private void addNewPatientWS(String nombre, String apellidos, String temperatura, String poblacion, String provincia, String format, ValidationCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.postUrlNewPatient,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            String id = null;
                            if (success) {
                                id = jsonResponse.getString("temp");
                                Toast.makeText(getApplicationContext(), "Medición guardada con éxito", Toast.LENGTH_SHORT).show();
                            } else {
                                String error = jsonResponse.getString("error");
                                Toast.makeText(getApplicationContext(), "ERROR: " + error, Toast.LENGTH_SHORT).show();
                            }
                            // Callback is called to provide id value...
                            callback.onValidationResult(id);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR: ", error.toString());
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nombre", nombre);
                params.put("apellidos", apellidos);
                params.put("temperatura", temperatura);
                params.put("ciudad", poblacion);
                params.put("provincia", provincia);
                params.put("format", format);
                return params;
            }
        };

        // Request added to queue...
        queue.add(stringRequest);
    }

    public interface ValidationCallback {
        void onValidationResult(String id);
    }
}