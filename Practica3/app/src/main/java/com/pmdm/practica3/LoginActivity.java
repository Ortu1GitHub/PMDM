package com.pmdm.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity {

    private EditText etUser, etPassword;
    private CheckBox cbRemember;

    private Button btnEntrar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        etUser = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);
        btnEntrar = findViewById(R.id.btnEntrar);
        cbRemember = findViewById(R.id.cbRemember);
        btnEntrar.setOnClickListener(checkCredentials);
        loadUserName();
    }

    private final View.OnClickListener checkCredentials = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String user = String.valueOf(etUser.getText()).trim();
            String password = String.valueOf(etPassword.getText()).trim();

            //Manage remember checkbox. In case it is checked user values must be saved , otherwise null
            if (cbRemember.isChecked()) {
                saveUserName(user);
            } else {
                saveUserName(null);
            }

            //If credentials are valid go to Menu activity
            validateCredentialsWS(user, password, new ValidationCallback() {
                @Override
                public void onValidationResult(boolean success) {
                    if (success) {
                        Intent i = new Intent(LoginActivity.this, Menu.class);
                        startActivity(i);
                    }
                }
            });
        }
    };

    //Callback instance is added to the WS request to manage asynchronous response
    private void validateCredentialsWS(String user, String password, ValidationCallback callback) {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.postUrlLogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Toast.makeText(getApplicationContext(), "Entrando al sistema...", Toast.LENGTH_SHORT).show();
                            } else {
                                String error = jsonResponse.getString("error");
                                Toast.makeText(getApplicationContext(), "ERROR: " + error, Toast.LENGTH_SHORT).show();
                            }

                            //Callback is called to provide success value...
                            callback.onValidationResult(success);
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
                params.put("user", user);
                params.put("passwd", password);
                return params;
            }
        };

        //Request added to queue...
        queue.add(stringRequest);
    }

    //Callback to manage validation
    public interface ValidationCallback {
        void onValidationResult(boolean success);
    }

    private void saveUserName(String user) {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.preferences_file, MODE_PRIVATE);
        //Editor generated to save data
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.USER_KEY, user);
        editor.apply();
    }

    private void loadUserName() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.preferences_file, MODE_PRIVATE);
        String user = sharedPreferences.getString(Constants.USER_KEY, null);
        etUser.setText(user);
        if (user != null) {
            //User is remembered so checkbox must be checked
            cbRemember.setChecked(true);
        }
    }
}