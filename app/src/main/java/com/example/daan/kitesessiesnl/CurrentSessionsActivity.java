package com.example.daan.kitesessiesnl;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class CurrentSessionsActivity extends AppCompatActivity implements SessionRequest.Callback{

    /**Method that is used when the ArrayList of sessions is retrieved successfully.*/
    @Override
    public void gotSessions(ArrayList<Session> sessions){

        ArrayList<Session> todaySessions = new ArrayList<>();

        // Create a timestamp of today's date to determine which sessions to show.
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

        // For every session in the sessions ArrayList.
        for(Session session: sessions){

            // If the date of the started sessions is equal to the today's date add this session to a new array and put it in the currentSessionList.
            if(session.getDate().equals(timeStamp)){

                todaySessions.add(session);

                // Sort the sessions in alphabetic order based on the spot name.
                Collections.sort(todaySessions);

                // Using the created SessionAdapter to put the sessions nicely in a ListView.
                SessionAdapter2 itemsAdapter = new SessionAdapter2(this, R.layout.session2, todaySessions);
                ListView sessionsList = findViewById(R.id.currentSessionsList);
                sessionsList.setAdapter(itemsAdapter);
            }
        }

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

        // Do a SessionRequest to get the sessions from the API, to be able to show all sessions from today.
        SessionRequest x = new SessionRequest(this);
        x.getSessions(this);

    }
}
