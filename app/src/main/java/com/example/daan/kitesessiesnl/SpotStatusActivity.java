
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

package com.example.daan.kitesessiesnl;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SpotStatusActivity extends AppCompatActivity implements SpotGetRequest.Callback {

    @Override
    public void gotSpots(ArrayList<Spot> spots) {

        // Sort the spots in alphabetic order based on the spot name.
        Collections.sort(spots);

        // Using the created SessionAdapter to put the sessions nicely in a ListView.
        SpotAdapter itemsAdapter = new SpotAdapter(this, R.layout.spot, spots);
        ListView spotList = findViewById(R.id.spotstatusList);
        spotList.setAdapter(itemsAdapter);
    }

    @Override
    public void gotSpotsError(String message) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_status);

        // Do a SpotGetRequest to be able to show the user all the spots (pending ones and ones that are in the map).
        SpotGetRequest x = new SpotGetRequest(this);
        x.getSpots(this);
    }
}
