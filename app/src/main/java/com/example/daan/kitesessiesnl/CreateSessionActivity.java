package com.example.daan.kitesessiesnl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class CreateSessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);

        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");

        TextView sessionTitle = findViewById(R.id.sessionTitle);
        sessionTitle.setText(title);
    }
    /**Method that lets users start a session with their information.*/
    public void startSession(View view){

        TextView spotTitle = findViewById(R.id.sessionTitle);
        String spotTitle_txt = spotTitle.getText().toString();

        EditText name = findViewById(R.id.name);
        String name_txt = name.getText().toString();

        EditText kite = findViewById(R.id.kitesize);
        String kite_txt = kite.getText().toString();

        EditText time = findViewById(R.id.timespan);
        String time_txt = time.getText().toString();

        // Post session information of a user to an online database.
        SessionPostRequest x = new SessionPostRequest(this);
        x.postSession(name_txt, kite_txt, time_txt, spotTitle_txt);

        // Heads the user back to the map activity after he/she started a session.
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

    }
}
