package com.pmdm.practica3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pmdm.practica3.R;

public class Conversor extends AppCompatActivity {

    private Button btnCalcular1, btnCalcular2;

    private EditText et1, et2, et3, et4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor);

        btnCalcular1 = findViewById(R.id.btnCalcular1);
        btnCalcular2 = findViewById(R.id.btnCalcular2);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);


        btnCalcular1.setOnClickListener(calculateCtoF);
        btnCalcular2.setOnClickListener(calculateFtoC);
    }

    private final View.OnClickListener calculateCtoF = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int celsius = Integer.parseInt(String.valueOf(et1.getText()).trim());
            //Conversion Celsius to Fahrenheit is done
            float aux = ((celsius * 9) / 5) + 32;
            et2.setText(String.valueOf(aux).trim());
            Toast.makeText(Conversor.this,"Conversion Celsius a Fahrenheit realizada...",Toast.LENGTH_LONG).show();
        }
    };

    private final View.OnClickListener calculateFtoC = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int fah = Integer.parseInt(String.valueOf(et3.getText()).trim());
            //Conversion Fahreheit to Celsius is done
            float aux = ((fah-32) * 5)/9;
            et4.setText(String.valueOf(aux).trim());
            Toast.makeText(Conversor.this,"Conversion Fahrenheit a Celsius realizada...",Toast.LENGTH_LONG).show();
        }
    };
}
