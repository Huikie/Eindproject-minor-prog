
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

package com.example.daan.kitesessiesnl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**Activity in which the user can see the details of the spots that are currently in the database*/
public class SpotStatusActivity extends AppCompatActivity implements SpotGetRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_status);

        // Do a SpotGetRequest to be able to show the user all the spots (pending ones and ones that are in the map).
        SpotGetRequest x = new SpotGetRequest(this);
        x.getSpots(this);
    }

    @Override
    public void gotSpots(ArrayList<Spot> spots) {

        ImageView loadingImage = findViewById(R.id.loadingSpots);
        loadingImage.setVisibility(View.GONE);

        TextView loadingImageTitle = findViewById(R.id.loadingSpotsTitle);
        loadingImageTitle.setVisibility(View.GONE);

        // Sort the spots in alphabetic order based on the spot name.
        Collections.sort(spots);

        // Using the created SessionAdapter to put the sessions nicely in a ListView.
        SpotAdapter itemsAdapter = new SpotAdapter(this, R.layout.spot, spots);
        ListView spotList = findViewById(R.id.spotstatusList);
        spotList.setAdapter(itemsAdapter);
    }

    @Override
    public void gotSpotsError(String message) {
        Toast.makeText(this, "Kitespots kunnen niet geladen worden. U heeft mogelijk geen verbinding tot het internet.",
                Toast.LENGTH_LONG).show();
    }
}
