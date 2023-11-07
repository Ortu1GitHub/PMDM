package com.example.myapplication21;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DatosContador{

    //numeroClicks is global so its value is conisdered in both Portrait and Landscape modes
    public static int numeroClicks=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void enviarDatosContador(int dato) {
        //Orientation is obtained
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent i = new Intent(this, MainActivity2.class);
            Toast.makeText(MainActivity.this,"CONTADOR: "+dato+"",Toast.LENGTH_LONG).show();
            i.putExtra("numero_clicks", dato);
            startActivity(i);
        }else{
            //Mode landscape
            Bundle dataBundle = new Bundle();
            dataBundle.putInt("numero_clicks", dato);
            Toast.makeText(MainActivity.this,"CONTADOR: "+dato+"",Toast.LENGTH_LONG).show();

            Fragment2 fragment2 = new Fragment2();
            fragment2.setArguments(dataBundle);

            //Fragment2 is replaced with new value of numclicks. Same action is performed in Activity2 in mode Portrait
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainerView3,fragment2);
            transaction.commit();
        }
    }

}
