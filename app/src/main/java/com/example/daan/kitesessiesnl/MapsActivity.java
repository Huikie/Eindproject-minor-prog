package com.example.daan.kitesessiesnl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, SpotGetRequest.Callback{

    private GoogleMap mMap;

    // Create a LatLngBounds that includes the Netherlands.
    private LatLngBounds NETHERLANDS = new LatLngBounds(
            new LatLng(51.803721015, 3.31497114423), new LatLng( 53.0104033474, 6.09205325687));

    /**Method that gets the spots from the API.*/
    @Override
    public void gotSpots(final ArrayList<Spot> spots) {
        try{
            for(Spot spot:spots){

                // If the spot status is equal to 1 add a marker for the spot on the map.
                if(spot.getStatus() == 1) {
                    mMap.addMarker(new MarkerOptions().position(new LatLng(spot.getLat(), spot.getLon())).title(spot.getName()));
                    }
                }
        }catch(Exception e){
            Toast.makeText(this, e.toString(),
                    Toast.LENGTH_LONG).show();
        }

        // Makes the info window of a marker clickable and sends the user to the information of the marker (spot) when the user clicks on the info window of the marker.
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String title = marker.getTitle();
                for (Spot spot : spots) {
                    if (spot.getName().equals(title) && spot.getStatus() == 1) {
                        Bundle coordinates = new Bundle();
                        coordinates.putParcelable("LatLng", marker.getPosition());


                        String spot_type = spot.getType();
                        String spot_surface = spot.getSurface();
                        Integer spot_distance = spot.getDistance();
                        Integer spot_image = spot.getImageId();
                        Integer spot_wind_directions = spot.getDirectionId();

                        Intent intent = new Intent(MapsActivity.this, SpotDetailsActivity.class);
                        intent.putExtras(coordinates);
                        intent.putExtra("Title", title);
                        intent.putExtra("Type", spot_type);
                        intent.putExtra("Surface", spot_surface);
                        intent.putExtra("Distance", spot_distance.toString());
                        intent.putExtra("Image", spot_image);
                        intent.putExtra("Directions", spot_wind_directions);

                        startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public void gotSpotsError(String message) {
        Toast.makeText(this, message,
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

            // Getting the right reference to all drawables.
//            int camperduin_image = getResources().getIdentifier("camperduin", "drawable", getPackageName());
//            int camperduin_directions = getResources().getIdentifier("camperduin_richtingen", "drawable", getPackageName());
//
//            // Hard coded spots.
//            Spot Camperduin = new Spot("Camperduin", "Zee & Lagune", "Zand", 500, camperduin_image, camperduin_directions, 52.7249, 4.6512);
//            Spot Wijk_aan_Zee = new Spot("Wijk aan Zee", "Zee & Lagune", "Zand", 500, camperduin_image, camperduin_directions, 51.7249, 3.6512);
//
//
//
//            List<Spot> spotList = new ArrayList<>();
//            spotList.add(Camperduin);
//            spotList.add(Wijk_aan_Zee);

            //Post spots to an online database.
//            for (Spot spot : spotList) {
//                SpotPostRequest x = new SpotPostRequest(this);
//                x.postSpot(spot.getName(), spot.getType(), spot.getSurface(), spot.getDistance(), Camperduin.getImageId(), Camperduin.getDirectionId(), Camperduin.getLon(), Camperduin.getLat());
//                Log.d("spotinfo",""+Camperduin.getImageId()+" "+ Camperduin.getDirectionId());
//            }

//        }
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

//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(52.6344,5.1221))
//                .title("Schellinkhout"));

        SpotGetRequest x = new SpotGetRequest(this);
        x.getSpots(this);
//
//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(51.766667,3.86))
//                .title("Brouwersdam"));
//
//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(52.2433, 4.4347))
//                .title("Noordwijk aan Zee"));
//
//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(52.958, 4.7603))
//                .title("Den Helder"));
//
//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(52.7249, 4.6512))
//                .title("Camperduin"));
//

//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(52.1062, 4.2753))
//                .title("Scheveningen"));
//
//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(53.4035, 6.2141))
//                .title("Lauwersoog"));
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
//    /**
//     * Checks if the user is opening the app for the first time.
//     * Note that this method should be placed inside an activity and it can be called multiple times.
//     * @return boolean
//     * Source: http://www.andreabaccega.com/blog/2012/04/12/android-how-to-execute-some-code-only-on-first-time-the-application-is-launched/
//     */
//    private boolean isFirstTime() {
//        if (firstTime == null) {
//            SharedPreferences mPreferences = this.getSharedPreferences("first_time", Context.MODE_PRIVATE);
//            firstTime = mPreferences.getBoolean("firstTime", true);
//            if (firstTime) {
//                SharedPreferences.Editor editor = mPreferences.edit();
//                editor.putBoolean("firstTime", false);
//                editor.commit();
//            }
//        }
//        return firstTime;
//    }
}
