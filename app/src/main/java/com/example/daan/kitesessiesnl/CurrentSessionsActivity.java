package com.example.daan.kitesessiesnl;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class CurrentSessionsActivity extends AppCompatActivity implements SessionRequest.Callback{

    /**Method that is used when the ArrayList of sessions is retrieved successfully.*/
    @Override
    public void gotSessions(ArrayList<Session> sessions){

        // Sort the sessions in alphabetic order based on the spot name.
        Collections.sort(sessions);

        // Using the created SessionAdapter to put the sessions nicely in a ListView.
        SessionAdapter2 itemsAdapter = new SessionAdapter2(this, R.layout.session2, sessions);
        ListView sessions_list = findViewById(R.id.currentSessionsList);
        sessions_list.setAdapter(itemsAdapter);

    }

    /**Method that shows the user an error message when the ArrayList of sessions isn't retrieved successfully.*/
    @Override
    public void gotSessionsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_sessions);

        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#ff33b5e5"));

        // Do a SessionRequest to get the sessions from the API, to be able to show all sessions from today.
        SessionRequest x = new SessionRequest(this);
        x.getSessions(this);

    }
}
