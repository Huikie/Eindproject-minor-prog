package com.example.daan.kitesessiesnl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
    public void startSession(View view){
        Intent intent = new Intent(this, SpotDetailsActivity.class);
        startActivity(intent);
    }
}
