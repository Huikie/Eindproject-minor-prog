package com.example.daan.kitesessiesnl;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

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



        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(52.6344,5.1221))
                .title("Schellinkhout"));

                //.snippet(snippet));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(52.8614, 5.4602))
                .title("Mirns"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.766667,3.86))
                .title("Brouwersdam"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(52.2433, 4.4347))
                .title("Noordwijk aan Zee"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(52.958, 4.7603))
                .title("Den Helder"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(52.7249, 4.6512))
                .title("Camperduin"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(52.4939, 4.5937))
                .title("Wijk aan Zee"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(52.1062, 4.2753))
                .title("Scheveningen"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.4035, 6.2141))
                .title("Lauwersoog"));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Bundle coordinates = new Bundle();
                coordinates.putParcelable("LatLng", marker.getPosition());

                String title = marker.getTitle();

                Intent intent = new Intent(MapsActivity.this, SpotDetailsActivity.class);
                intent.putExtras(coordinates);
                intent.putExtra("Title",title);

                startActivity(intent);
            }
        });
    }

    /**Method that makes it possible for users to zoom in/out on the map using two buttons.*/
    public void onZoom(View view){

        // Zoom in
        if(view.getId() == R.id.zoom_In){
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }

        // Zoom out (only if zoom level > 7.8 (so that the map keeps restricted to the Dutch boundaries) & zoom level is not equal to 7 (begin situation))
        if(view.getId() == R.id.zoom_Out && mMap.getCameraPosition().zoom > 7.8 && mMap.getCameraPosition().zoom != 7){
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }

    /**Method that directs the user from the map to the spotrequest page if the users click on the spotrequest button.*/
    public void spotRequest(View view){
        Intent intent = new Intent(MapsActivity.this, SpotRequestActivity.class);
        startActivity(intent);
    }
}
