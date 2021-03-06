
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

package com.example.daan.kitesessiesnl;

import android.content.Intent;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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

/**The main activity in which the map with the spots is shown
 * and from where the user can reach all the activities of the app.*/
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, SpotGetRequest.Callback{

    private GoogleMap mMap;

    // Create a LatLngBounds that includes the Netherlands.
    private LatLngBounds NETHERLANDS = new LatLngBounds(
            new LatLng(51.803721015, 3.31497114423), new LatLng( 53.0104033474, 6.09205325687));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**Manipulates the map once available. This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.*/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Create a Spinner with the different Googlemap types so that the user can choose which maptype the users wants.
        Spinner mapTypes = findViewById(R.id.mapTypes);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(MapsActivity.this,R.layout.spinner_textview,getResources().getStringArray(R.array.mapsViews));
        myAdapter.setDropDownViewResource(R.layout.dropdown_item_textview);
        mapTypes.setAdapter(myAdapter);

        // Use the chooseMapType method to allow users to choose a map type.
        chooseMapType(mapTypes);

        // Create a ImageButton with an "addmarkericon" in it.
        ImageButton spotRequestButton = findViewById(R.id.spotRequest);
        spotRequestButton.setOnClickListener(new View.OnClickListener() {

            // If the users clicks this button it will see a pop up window with information about how to add a marker to the map.
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsActivity.this, LongClickInfoActivity.class));
            }
        });

        // Move the camera to the center of the Netherlands.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NETHERLANDS.getCenter(), 7));

        // Do a SpotGetRequest to get the spots from the API and to be able to add markers for them.
        SpotGetRequest x = new SpotGetRequest(this);
        x.getSpots(this);

        // When the user clicks long on the map he/she will be directed to the activity where he/she can do a SpotRequest.
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Bundle coordinates = new Bundle();
                coordinates.putParcelable("LatLng", latLng);
                Intent intent = new Intent(MapsActivity.this, SpotRequestActivity.class);
                intent.putExtras(coordinates);
                startActivity(intent);
            }
        });
    }

    /**Method that gets the spots from the API.*/
    @Override
    public void gotSpots(final ArrayList<Spot> spots) {

        ImageView loadingImage = findViewById(R.id.loadingSpots);
        loadingImage.setVisibility(View.GONE);

        TextView loadingImageTitle = findViewById(R.id.loadingSpotsTitle);
        loadingImageTitle.setVisibility(View.GONE);

        // For every spot in the spots ArrayList.
        for (Spot spot:spots){

            // If the spot status is equal to 1 (approved) add a marker for the spot on the map.
            if (spot.getStatus() == 1){
                mMap.addMarker(new MarkerOptions().position(new LatLng(spot.getLat(), spot.getLon())).title(spot.getName() + " >>"));
            }

        }

        // Use the makeMarkerInfoWindowClickable to make the markers clickable and send users to the SpotDetailsActivity.
        makeMarkerInfoWindowClickable(mMap, spots);
    }

    @Override
    public void gotSpotsError(String message) {
        Toast.makeText(this, "Kitespots kunnen niet geladen worden. U heeft mogelijk geen verbinding tot het internet.",
                Toast.LENGTH_LONG).show();
    }

    /**Method that makes it possible for the user to refresh the page, so that they can see if spots are added.*/
    public void onRefresh(View view) {
        Intent intent = getIntent();
        finish();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    /**Method that directs the users to an activity where they can see all the sessions from today.*/
    public void getAllSessions(View view) {
        Intent intent = new Intent(MapsActivity.this, CurrentSessionsActivity.class);
        startActivity(intent);
    }

    /**Method that directs the users to an activity where they can see all the spots with information and status.*/
    public void getAllSpots(View view) {
        Intent intent = new Intent(MapsActivity.this, SpotStatusActivity.class);
        startActivity(intent);
    }

    /**Method that allows users to choose a map type.*/
    public void chooseMapType(Spinner mapTypes){
        mapTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (id == 0){
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
                else if (id == 1){
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
                else if (id == 2){
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
                else if (id == 3){
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**Method that makes the info window of a marker clickable and sends the user to the
     * information of the marker (spot) when the user clicks on the info window of the marker.*/
    public void makeMarkerInfoWindowClickable(GoogleMap map, final ArrayList<Spot> spots) {
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                // Gets the title of the marker that the user clicked on.
                String title = marker.getTitle();

                // Trim the title so that the ' >>' is removed.
                String titleTrimmed = title.substring(0, title.length() - 3);

                // For every spot in the spots ArrayList.
                for (Spot spot : spots) {
                    // If the spot status is equal to 1 (approved) and the spot name is equal to the spot where the user clicked on, then direct the user to the SpotDetailsActivity and send spotinformation along with that direction.
                    if (spot.getName().equals(titleTrimmed) && spot.getStatus() == 1) {

                        Bundle coordinates = new Bundle();
                        coordinates.putParcelable("LatLng", marker.getPosition());

                        String spotType = spot.getType();
                        String spotSurface = spot.getSurface();
                        Integer spotDistance = spot.getDistance();
                        String spotImage = spot.getImageId();
                        Integer spotWindDirections = spot.getDirectionId();

                        Intent intent = new Intent(MapsActivity.this, SpotDetailsActivity.class);

                        intent.putExtras(coordinates);
                        intent.putExtra("Title", title);
                        intent.putExtra("Type", spotType);
                        intent.putExtra("Surface", spotSurface);
                        intent.putExtra("Distance", spotDistance.toString());
                        intent.putExtra("Image", spotImage);
                        intent.putExtra("Directions", spotWindDirections);

                        startActivity(intent);
                    }
                }
            }
        });
    }

}
