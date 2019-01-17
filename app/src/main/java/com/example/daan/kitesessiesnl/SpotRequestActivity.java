package com.example.daan.kitesessiesnl;

import android.content.Intent;
import android.graphics.Color;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_request);

        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#ff33b5e5"));
    }


    /**Method that directs users back to the map page when they've submitted their spot request.*/
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

        // Do a SpotPostRequest to put the spotinformation that the user added in the online database and give it a status 0 (not approved, so not shown on the map yet).
        SpotPostRequest x = new SpotPostRequest(this);
        x.postSpot(spot.getName(),spot.getType(),spot.getSurface(),spot.getDistance(),spot.getImageId(),spot.getDirectionId(),spot.getStatus(),spot.getLat(),spot.getLon());

        Intent intent2 = new Intent(this, MapsActivity.class);
        startActivity(intent2);

    }
}