package com.pmdm.practica22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pmdm.practica22.R;

public class MainActivity extends AppCompatActivity {

    private EditText etUser, etPassword;

    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUser = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(checkCredentials);

    }

    private final View.OnClickListener checkCredentials = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String user = String.valueOf(etUser.getText()).trim();
            String password = String.valueOf(etPassword.getText()).trim();

            //If credentials are valid go to Menu activity
            if (validateCredentials(user,password)){
                Intent i = new Intent(MainActivity.this,Menu.class);
                startActivity(i);
            }

        }

        private boolean validateCredentials(String user, String password){
            boolean isValid;
            if (user.equals("") || password.equals("")){
                Toast.makeText(MainActivity.this,"No se ha informado algun campo.Vuelva a intentarlo... ", Toast.LENGTH_LONG).show();
                isValid=false;
                return (isValid);
            }

            if (!user.equals("admin") || !password.equals("admin") ) {
                Toast.makeText(MainActivity.this,"Credenciales incorrectas. Vuelva a intentarlo...",Toast.LENGTH_LONG).show();
                isValid=false;
                return (isValid);

            }else{
                isValid=true;
                return (isValid);
            }
        }
    };


}