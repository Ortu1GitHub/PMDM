package com.example.myapplication21;

import static com.example.myapplication21.MainActivity.numeroClicks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Fragment1 extends Fragment {

    private DatosContador datosContador;
    private Button btn1;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v1=inflater.inflate(R.layout.fragment_1, container, false);

        btn1 = (Button) v1.findViewById(R.id.btnAgregar);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroClicks++;
                //Call to interface
                Activity estaActividad=getActivity();
                ((DatosContador) estaActividad).enviarDatosContador(numeroClicks);
            }
        });

       return v1;
    }

}
