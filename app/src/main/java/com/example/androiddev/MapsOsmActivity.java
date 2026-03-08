package com.example.androiddev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.androiddev.databinding.ActivityMapsOsmBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsOsmActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsOsmBinding binding;

    // Villes du Maroc
    private final LatLng TANGER = new LatLng(35.7595, -5.8340);
    private final LatLng RABAT = new LatLng(34.0209, -6.8416);
    private final LatLng CASABLANCA = new LatLng(33.5731, -7.5898);
    private final LatLng AGADIR = new LatLng(30.4278, -9.5981);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsOsmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);


        afficherVilles();


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(RABAT, 10f));


        // mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


         tracerTrajectoire(RABAT, CASABLANCA);
    }

    private void afficherVilles() {
        mMap.addMarker(new MarkerOptions()
                        .position(TANGER)
                        .title("Tanger")
                // .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_tanger))
        );

        mMap.addMarker(new MarkerOptions()
                        .position(RABAT)
                        .title("Rabat")
                // .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_rabat))
        );

        mMap.addMarker(new MarkerOptions()
                        .position(CASABLANCA)
                        .title("Casablanca")
                // .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_casablanca))
        );

        mMap.addMarker(new MarkerOptions()
                        .position(AGADIR)
                        .title("Agadir")
                // .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_agadir))
        );
    }

    // Question 9 : zoom sur Rabat
    private void zoomSurRabat(float zoom) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(RABAT, zoom));
    }

    // "Aller plus loin" question 1 : tracer une trajectoire entre deux points
    private void tracerTrajectoire(LatLng depart, LatLng arrivee) {
        mMap.addMarker(new MarkerOptions().position(depart).title("Départ"));
        mMap.addMarker(new MarkerOptions().position(arrivee).title("Arrivée"));

        mMap.addPolyline(new PolylineOptions()
                .add(depart, arrivee)
                .width(8f)
                .geodesic(true));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(depart, 6f));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_defaut) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            return super.onOptionsItemSelected(item);
        }

        if (id == R.id.menu_satellite) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }
}