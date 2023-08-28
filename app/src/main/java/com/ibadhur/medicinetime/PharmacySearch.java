package com.ibadhur.medicinetime;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ibadhur.medicinetime.databinding.ActivityPharmacySearchBinding;

import java.util.List;

public class PharmacySearch extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityPharmacySearchBinding binding;
    List<Address> ListGeoCoder;
    private static final int LOCATION_PERMISSION_CODE = 101;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_search);

        boolean showNearbyPharmacies = getIntent().getBooleanExtra("showNearbyPharmacies", false);

        if (showNearbyPharmacies) {
            // Show nearby pharmacies on Google Maps
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=pharmacy");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                Toast.makeText(PharmacySearch.this, "Google Maps not installed", Toast.LENGTH_SHORT).show();
            }
        } else {
            binding = ActivityPharmacySearchBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            if(isLocationPermissionGranted()) {
                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                assert mapFragment != null;
                mapFragment.getMapAsync(this);
            } else {
                //Request Location Permission
                requestLocationPermission();
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //request runtime permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
    }

    //Check the location PERMISSION_GRANTED or not
    private boolean isLocationPermissionGranted(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            return false;
        }
    }

    //request location permission
    private void requestLocationPermission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION_CODE);
    }


}