package com.example.daan.kitesessiesnl;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, WeatherRequest.Callback {

    WeatherInfo weatherinfoApp;

    @Override
    public void gotWeatherInfo(WeatherInfo weatherInfo) {
        weatherinfoApp = weatherInfo;
        Toast.makeText(this, weatherInfo.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void gotWeatherInfoError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private GoogleMap mMap;
    // Create a LatLngBounds that includes the Netherlands.
    private LatLngBounds NETHERLANDS = new LatLngBounds(
            new LatLng(51.803721015, 3.31497114423), new LatLng( 53.0104033474, 6.09205325687));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Constrain the camera target to the Netherlands bounds.
        mMap.setLatLngBoundsForCameraTarget(NETHERLANDS);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NETHERLANDS.getCenter(), 7));
        setMapLongClick(mMap);
    }
    private void setMapLongClick(final GoogleMap map){
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                WeatherRequest x = new WeatherRequest(MapsActivity.this);
                x.getWeatherInfo(MapsActivity.this, latLng.latitude, latLng.longitude);

                String snippet = String.format(Locale.getDefault(),
                        "Lat: %1$.5f, Long: %2$.5f",
                        latLng.latitude,
                        latLng.longitude);

                map.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("hans")
                        .snippet(snippet));
            }
        });
    }
    public void onZoom(View view){
        if(view.getId() == R.id.zoom_In){
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
        if(view.getId() == R.id.zoom_Out && mMap.getCameraPosition().zoom > 7.8 && mMap.getCameraPosition().zoom != 7){
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }
}
