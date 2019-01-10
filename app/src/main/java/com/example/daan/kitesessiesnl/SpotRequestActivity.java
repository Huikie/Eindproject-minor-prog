package com.example.daan.kitesessiesnl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SpotRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_request);
    }
    /**Method that directs users back to the map page when they've submitted their spotrequest*/
    public void submitRequest(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
