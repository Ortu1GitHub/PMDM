package com.pmdm.practica3;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Mapa extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap myClientMap;
    private MapView mapView;

    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        // Inicializar el mapa
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        // Se recupera la dirección y provincia pasadas en el intent
        city = getIntent().getExtras().getString("city");
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        List<Address> addressList = null;

        if (city != null) {
            Geocoder geocoder = new Geocoder(Mapa.this);

            try {
                addressList = geocoder.getFromLocationName(city, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Address address = addressList.get(0);
        myClientMap = googleMap;
        LatLng cityLocations = new LatLng(address.getLatitude(), address.getLongitude());
        myClientMap.addMarker(new MarkerOptions().position(cityLocations).title(city));
        myClientMap.moveCamera(CameraUpdateFactory.newLatLng(cityLocations));
        myClientMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cityLocations, 10));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
