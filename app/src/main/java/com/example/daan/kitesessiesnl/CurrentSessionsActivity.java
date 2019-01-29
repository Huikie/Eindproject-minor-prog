package com.example.daan.kitesessiesnl;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

/**Activity in which the user can see the sessions that are planned today.*/
public class CurrentSessionsActivity extends AppCompatActivity implements SessionRequest.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_sessions);

        // Do a SessionRequest to get the sessions from the API, to be able to show all sessions from today.
        SessionRequest x = new SessionRequest(this);
        x.getSessions(this);

    }

    /**Method that is used when the ArrayList of sessions is retrieved successfully.*/
    @Override
    public void gotSessions(final ArrayList<Session> sessions) {

        final ArrayList<Session> todaySessions = new ArrayList<>();

        // Create a timestamp of today's date to determine which sessions to show.
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

        // For every session in the sessions ArrayList.
        for(Session session: sessions) {

            // If the date of the started sessions is equal to the today's date add this session to a new array and put it in the currentSessionList.
            if (session.getDate().equals(timeStamp)) {
                    todaySessions.add(session);

                // This method defines a way to sort the list of sessions alphabetically based on the spot name and start time.
                // Source: https://stackoverflow.com/questions/16192244/java-comparators-for-a-class-in-another-class
                // Source2: https://stackoverflow.com/questions/4805606/how-to-sort-by-two-fields-in-java
                Comparator<Session> alphabeticSpot = new Comparator<Session>() {
                    @Override
                    public int compare(Session session, Session otherSession) {

                        // Spots in alphabetic order on spot name.
                        int compareSpot = session.getSpot().compareToIgnoreCase(otherSession.getSpot());

                        if(compareSpot != 0){
                            return compareSpot;
                        }

                        // Most current sessions first.
                        return otherSession.getExactDate().compareTo(session.getExactDate());
                    }
                };

                    // Sort the sessions in alphabetic order based on the spot name and after that on the most current sessions first.
                    Collections.sort(todaySessions, alphabeticSpot);

                    // Using the created SessionAdapter to put the sessions nicely in a ListView.
                    SessionAdapter2 itemsAdapter = new SessionAdapter2(this, R.layout.session2, todaySessions);
                    ListView sessionsList = findViewById(R.id.currentSessionsList);
                    sessionsList.setAdapter(itemsAdapter);
                }
            }

            // If there are no sessions yet today.
            if(todaySessions.size() == 0){

                // Then create a dialog that tells the user that there aren't any sessions yet today.
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.popup_no_session);
                TextView message = dialog.findViewById(R.id.noSessionsMessage);
                message.setText(getString(R.string.messageOnNoSession));
                dialog.show();
            }
        }

    /**Method that shows the user an error message when the ArrayList of sessions isn't retrieved successfully.*/
    @Override
    public void gotSessionsError(String message) {
        Toast.makeText(this, "Sessies kunnen niet geladen worden. U heeft mogelijk geen verbinding tot het internet.",
                Toast.LENGTH_LONG).show();
    }

}
