package com.example.daan.kitesessiesnl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class SpotRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_request);
    }
    /**Method that directs users back to the map page when they've submitted their spot request*/
    public void submitRequest(View view){

        EditText spotName = findViewById(R.id.spotName);
        EditText spotType = findViewById(R.id.spotType);
        EditText spotSurface = findViewById(R.id.spotSurface);
        EditText spotDistance = findViewById(R.id.spotDistance);
        EditText spotLat = findViewById(R.id.spotLat);
        EditText spotLon = findViewById(R.id.spotLon);

        String name = spotName.getText().toString();
        String type = spotType.getText().toString();
        String surface = spotSurface.getText().toString();
        Integer distance = parseInt(spotDistance.getText().toString());
        Double lat = parseDouble(spotLat.getText().toString());
        Double lon = parseDouble(spotLon.getText().toString());

        Spot spot = new Spot(name,type,surface,distance,0,0,0,lat,lon);

        SpotPostRequest x = new SpotPostRequest(this);
        x.postSpot(spot.getName(),spot.getType(),spot.getSurface(),spot.getDistance(),spot.getImageId(),spot.getDirectionId(),spot.getStatus(),spot.getLat(),spot.getLon());

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
