package com.example.daan.kitesessiesnl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class SpotRequestActivity extends AppCompatActivity {

    LatLng coordinatess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_request);
//        TextView spotLat = findViewById(R.id.spotLat);
//        TextView spotLon = findViewById(R.id.spotLon);
//        spotLon.setText(""+coordinates.longitude);
//        spotLat.setText(""+coordinates.latitude);

//        try{
//            coordinatess = coordinates;
//            Log.d("coordinaten", coordinatess.toString());
//
//        }catch(Exception e){Log.d("bug", e.toString());}

    }

    /**
     * Method that directs users back to the map page when they've submitted their spot request
     */
    public void submitRequest(View view) {

        Intent intent1 = getIntent();
        LatLng coordinates = intent1.getParcelableExtra("LatLng");

        EditText spotName = findViewById(R.id.spotName);
        EditText spotType = findViewById(R.id.spotType);
        EditText spotSurface = findViewById(R.id.spotSurface);
        EditText spotDistance = findViewById(R.id.spotDistance);

        String name = spotName.getText().toString();
        String type = spotType.getText().toString();
        String surface = spotSurface.getText().toString();
        Integer distance = parseInt(spotDistance.getText().toString());

        Spot spot = new Spot(name,type,surface,distance,0,0,0,coordinates.latitude,coordinates.longitude);

        SpotPostRequest x = new SpotPostRequest(this);
        x.postSpot(spot.getName(),spot.getType(),spot.getSurface(),spot.getDistance(),spot.getImageId(),spot.getDirectionId(),spot.getStatus(),spot.getLat(),spot.getLon());

        Intent intent2 = new Intent(this, MapsActivity.class);
        startActivity(intent2);



    }
}