package com.pmdm.practica3;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {
    List<Patient> patientList = new ArrayList<Patient>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity_old);

        //Adapter is generated
        final PatientAdapter adapter = new PatientAdapter(this, patientList);

        ListView lvPatientData = findViewById(R.id.lvPatientData);

        //Adapter is set to listview
        lvPatientData.setAdapter(adapter);

        // Set item click listener to open Map activity
        lvPatientData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked patient
                Patient selectedPatient = patientList.get(position);

                // Open Mapa activity, passing data if needed
                Intent intent = new Intent(Menu.this, Mapa.class);
                // Pass city to Map activity
                intent.putExtra("city", selectedPatient.getCity());
                startActivity(intent);
            }
        });

        //Reference to bottomNavigation is obtained
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // Patient list is obtained
        obtainPatientListWS(new OnDataReceivedCallback() {
            @Override
            public void onDataReceived(List<Patient> updatedPatientList) {
                //patientList is updated
                patientList = updatedPatientList;

                //Adapter is updated with patentList updated
                adapter.clear();
                adapter.addAll(patientList);

                //Notify adapter data has changed
                adapter.notifyDataSetChanged();
            }
        });
    }

    private List<Patient> obtainPatientListWS(final OnDataReceivedCallback callback) {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.postUrlList,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {

                                //Object list is retrieved in case request was successful
                                JSONArray jsonArray = jsonResponse.getJSONArray("temp");

                                List<Patient> patientList = new ArrayList<Patient>();

                                //Obejct list is checked
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject patientObject = jsonArray.getJSONObject(i);

                                    //Data for a single object is obtained
                                    Patient patient = new Patient();
                                    patient.setId(patientObject.getString("id"));
                                    patient.setName(patientObject.getString("nombre"));
                                    patient.setSurname(patientObject.getString("apellidos"));
                                    patient.setTemperature(patientObject.getString("temperatura"));
                                    patient.setFormat(patientObject.getString("format"));
                                    patient.setCity(patientObject.getString("ciudad"));
                                    patient.setProvince(patientObject.getString("provincia"));

                                    //A new patient is added
                                    patientList.add(patient);
                                }

                                // Call with patentList updated
                                callback.onDataReceived(patientList);
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
        };

        //Request added to queue
        queue.add(stringRequest);
        return (patientList);
    }

    // Callback interface to manage asynchronous data
    public interface OnDataReceivedCallback {
        void onDataReceived(List<Patient> updatedPatientList);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    //Every activity is generated when clicking icons related

                    if (item.getItemId() == R.id.menu_configuracion) {
                        Intent i = new Intent(Menu.this, Configuracion.class);
                        startActivity(i);
                        return true;
                    } else if (item.getItemId() == R.id.menu_conversor) {
                        Intent k = new Intent(Menu.this, Conversor.class);
                        startActivity(k);
                        return true;
                    } else if (item.getItemId() == R.id.menu_medicion) {
                        Intent j = new Intent(Menu.this, FormularioMedicion.class);
                        startActivity(j);
                        return true;
                    } else if (item.getItemId() == R.id.menu_cerrar) {
                        Intent l = new Intent(Menu.this, LoginActivity.class);
                        startActivity(l);
                        finish();
                        return true;
                    }
                    return false;
                }
            };
}
