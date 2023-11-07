package com.example.myapplication21;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {

    public int num_clicks;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Retrieving data from Activity1
        Bundle extras=getIntent().getExtras();
        num_clicks=extras.getInt("numero_clicks");

        //Configuring data to new Fragment2
        Bundle dataBundle2 = new Bundle();
        dataBundle2.putInt("numero_clicks", num_clicks);

        Fragment2 fragment2 = new Fragment2();
        fragment2.setArguments(dataBundle2);

        //Fragment2 is replaced with new value of numclicks. Sqame action as in MainActivity in Portrait mode
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView2,fragment2);
        transaction.commit();
    }


}