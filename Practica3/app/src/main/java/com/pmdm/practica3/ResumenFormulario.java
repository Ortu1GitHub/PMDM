package com.pmdm.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResumenFormulario extends AppCompatActivity {

    public String id, temperature;
    private TextView tvNombre, tvApellidos, tvGrados, tvPoblacion, tvProvincia;

    private boolean isCelsiusSelected, isFahrenheitSelected;

    private Button btnMenu, btnColor;

    Patient patient, obtainedPatient = new Patient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);

        // Id is obtained from intent
        id = getIntent().getExtras().getString("clave");

        tvNombre = findViewById(R.id.tvNombre);
        tvApellidos = findViewById(R.id.tvApellidos);
        tvGrados = findViewById(R.id.tvTemp);
        tvPoblacion = findViewById(R.id.tvPoblacion);
        tvProvincia = findViewById(R.id.tvProvincia);
        btnMenu = findViewById(R.id.btnMenu);
        btnColor = findViewById(R.id.btnColor);


        // Data retrieved from WS...
        getSummaryData(new OnDataReceivedCallback() {
            @Override
            public void onDataReceived(Patient obtainedPatient) {
                // Actualizar el paciente obtenido
                patient = obtainedPatient;

                // Data configured in form summary
                tvNombre.setText(patient.getName());
                tvApellidos.setText(patient.getSurname());
                tvGrados.setText(patient.getTemperature());
                tvPoblacion.setText(patient.getCity());
                tvProvincia.setText(patient.getProvince());

                if (Integer.parseInt(patient.getFormat()) == 1) {
                    isCelsiusSelected = true;
                }

                if (Integer.parseInt(patient.getFormat()) == 2) {
                    isFahrenheitSelected = true;
                }

                // Button enabled with its color
                temperature = patient.getTemperature();
                checkTemperatureResult(temperature, isCelsiusSelected, isFahrenheitSelected);
            }
        });

        //OnClickListerner established after getSummaryData
        btnMenu.setOnClickListener(goToLogin);
    }

    private void checkTemperatureResult(String temperature, boolean isCelsiusSelected, boolean isFahrenheitSelected) {
        int aux = Integer.valueOf(temperature);
        if ((aux > 38 && isCelsiusSelected) || (aux > 100 && isFahrenheitSelected)) {
            btnColor.setBackgroundColor(Color.parseColor("RED"));
        } else {
            btnColor.setBackgroundColor(Color.parseColor("GREEN"));
        }
    }

    private final View.OnClickListener goToLogin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(ResumenFormulario.this, Menu.class);
            startActivity(i);
            finish();
        }
    };

    private void getSummaryData(OnDataReceivedCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.getUrlListId + "?id=" + id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            Log.d("RESPUESTA CONS:", response);

                            //Patient patient = null;
                            if (success) {
                                Toast.makeText(getApplicationContext(), "Consulta con éxito", Toast.LENGTH_SHORT).show();

                                // If sucess object list is obtained
                                JSONArray jsonArray = jsonResponse.getJSONArray("temp");
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                patient = new Patient();

                                patient.setName(jsonObject.getString("nombre"));
                                patient.setSurname(jsonObject.getString("apellidos"));
                                patient.setTemperature(jsonObject.getString("temperatura"));
                                patient.setCity(jsonObject.getString("ciudad"));
                                patient.setProvince(jsonObject.getString("provincia"));
                                patient.setFormat(jsonObject.getString("format"));

                                if (patient != null) {
                                    //Callback with patient list updated
                                    callback.onDataReceived(patient);
                                }

                            } else {
                                String error = jsonResponse.getString("error");
                                Toast.makeText(getApplicationContext(), "ERROR: " + error, Toast.LENGTH_SHORT).show();
                            }

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
                params.put("id", id);

                return params;
            }
        };

        // Request added to queue...
        queue.add(stringRequest);
    }

    // Callback to manage validation
    public interface OnDataReceivedCallback {
        void onDataReceived(Patient obtainedPatient);
    }
}
