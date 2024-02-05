package com.pmdm.practica3;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class Mapa extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap myClientMap;
    private MapView mapView;

    private ArrayList<String> listaCiudades;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        // Inicializar el mapa
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        // Se recupera la ciudad pasada en el intent
        city = getIntent().getExtras().getString("city");
        //Si es null se trata de un al de ciudades, si no es una sola ciudad
        if (city != NULL) {
            listaCiudades = getIntent().getStringArrayListExtra("lista_direcciones");
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myClientMap = googleMap;

        if (city != null) {
            // Mostrar ubicación de la ciudad única
            showCityLocation(city);
        } else if (listaCiudades != null) {
            // Mostrar ubicaciones de todas las ciudades en la lista
            showCitiesLocations();
        }
    }

    private void showCityLocation(String cityName) {
        List<Address> addressList = null;

        Geocoder geocoder = new Geocoder(Mapa.this, Locale.getDefault());

        try {
            addressList = geocoder.getFromLocationName(cityName, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addressList != null && !addressList.isEmpty()) {
            Address address = addressList.get(0);
            LatLng cityLocation = new LatLng(address.getLatitude(), address.getLongitude());
            myClientMap.addMarker(new MarkerOptions().position(cityLocation).title(cityName));
            myClientMap.moveCamera(CameraUpdateFactory.newLatLng(cityLocation));
            myClientMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cityLocation, 10));
        }
    }

    private void showCitiesLocations() {
        Geocoder geocoder = new Geocoder(Mapa.this, Locale.getDefault());

        for (String cityName : listaCiudades) {
            try {
                List<Address> addressList = geocoder.getFromLocationName(cityName, 1);
                if (addressList != null && !addressList.isEmpty()) {
                    Address address = addressList.get(0);
                    LatLng cityLocation = new LatLng(address.getLatitude(), address.getLongitude());
                    myClientMap.addMarker(new MarkerOptions().position(cityLocation).title(cityName));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
