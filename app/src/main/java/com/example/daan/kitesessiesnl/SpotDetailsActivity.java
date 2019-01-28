
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

package com.example.daan.kitesessiesnl;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

/**Activity in which the user can see the details of a certain spot
 * and from there go to the page where the user can start a session on this spot.*/
public class SpotDetailsActivity extends AppCompatActivity implements WeatherRequest.Callback, SessionRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_details);

        // Get information received from the marker that is clicked on.
        Intent intent = getIntent();
        LatLng coordinates = intent.getParcelableExtra("LatLng");
        String title = intent.getStringExtra("Title");
        String type = intent.getStringExtra("Type");
        String surface = intent.getStringExtra("Surface");
        String distance = intent.getStringExtra("Distance");
        String imageId = intent.getStringExtra("Image");

        // Decode the Base64 String and create the image in the spotdetailsactivity ImageView.
        try{
            int flags = Base64.NO_WRAP | Base64.URL_SAFE;
            byte[] imageIdDecoded = Base64.decode(imageId,flags);

            Bitmap bmp = BitmapFactory.decodeByteArray(imageIdDecoded, 0, imageIdDecoded.length);

            ImageView spotImage = findViewById(R.id.spotDImage);
            spotImage.setImageBitmap(Bitmap.createScaledBitmap(bmp, bmp.getWidth(),
                    bmp.getHeight(), false));}

        catch(Exception e){
            Log.d("bug", e.toString());
        }

        TextView spotdetailsTitle = findViewById(R.id.spotDetailsTitle);
        spotdetailsTitle.setText(title);

        TextView spotdetailsType = findViewById(R.id.type);
        spotdetailsType.setText(Html.fromHtml("<b>Spot type:</b> "+type));

        TextView spotdetailsSurface = findViewById(R.id.surface);
        spotdetailsSurface.setText(Html.fromHtml("<b>Spot ondergrond:</b> "+surface));

        TextView spotdetailsDistance = findViewById(R.id.distance);
        spotdetailsDistance.setText(Html.fromHtml("<b>Spot afstand:</b> "+distance + "m"+"<br>"));

        // Do a WeatherRequest to get the current weatherinfo for the location of the marker that the user clicked on.
        WeatherRequest x = new WeatherRequest(this);
        x.getWeatherInfo(this, coordinates.latitude, coordinates.longitude);

        // Do a SessionRequest to be able to show the user all the sessions that are started today.
        SessionRequest x2 = new SessionRequest(this);
        x2.getSessions(this);

    }

    /**Method that makes sure that only the sessions are displayed from the spot that is clicked on by the user.*/
    @Override
    public void gotSessions(ArrayList<Session> sessions) {

        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");

        ArrayList<Session> s2 = new ArrayList<>();

        // Create a timestamp of today's date to determine which sessions to show.
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

        for (Session session: sessions) {
            if(session.getSpot().equals(title) && session.getDate().equals(timeStamp)){

                s2.add(session);

                /** This method defines a way to sort the list of sessions alphabetically based on the date.
                 * Source: https://stackoverflow.com/questions/16192244/java-comparators-for-a-class-in-another-class.*/
                Comparator<Session> lastOnesFirst = new Comparator<Session>() {
                    @Override
                    public int compare(Session session, Session otherSession) {
                        return otherSession.getExactDate().compareToIgnoreCase(session.getExactDate());
                    }
                };

                // Sort the sessions in alphabetic order based on the spot name.
                Collections.sort(s2, lastOnesFirst);

                // Using the created SessionAdapter to put the spot's sessions nicely in a ListView.
                SessionAdapter itemsAdapter = new SessionAdapter(this, R.layout.session, s2);
                ListView sessionList = findViewById(R.id.kitersList);
                sessionList.setAdapter(itemsAdapter);
            }
        }

        // If there are no sessions yet today.
        if(s2.size() == 0){

            // Then create a dialog that tells the user that there aren't any sessions yet today.
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.popup_no_session);
            TextView message = dialog.findViewById(R.id.noSessionsMessage);
            message.setText(getString(R.string.messageOnNoSessionSpot));
            dialog.show();
        }
    }

    /**The sessions aren't received correctly, an error message will be shown.*/
    @Override
    public void gotSessionsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**This method receives the weatherinfo if the request has been made successfully.*/
    @Override
    public void gotWeatherInfo(WeatherInfo weatherInfo) {

        TextView windspeed = findViewById(R.id.windspeed);
        TextView winddegrees = findViewById(R.id.winddegrees);
        TextView temperature = findViewById(R.id.temperature);

        // Convert m/s to knots & round.
        Double knots = weatherInfo.getSpeed() * 1.94375;
        Double roundKnots = Double.valueOf(new DecimalFormat("#.#").format(
                knots));

        // Convert Kelvin to Celsius & round.
        Double celsius = weatherInfo.getTemperature() - 273.15;
        Double roundCelsius = Double.valueOf(new DecimalFormat("#.#").format(
                celsius));

        windspeed.setText(Html.fromHtml("<b>Actuele windsnelheid:</b> "+roundKnots.toString() + " " + "knopen"));
        temperature.setText(Html.fromHtml("<b>Temperatuur:</b> "+roundCelsius.toString() + " " + "℃"));

        // Determined the wind direction in letters based on the degree received from the API.
        // Source: https://uni.edu/storm/Wind%20Direction%20slide.pdf
        if (weatherInfo.getDegrees() <= 360 && weatherInfo.getDegrees() >= 350 || weatherInfo.getDegrees() <= 10) {
            winddegrees.setText(Html.fromHtml("<b>Windrichting:</b> " +"N" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")"));
        } else if (weatherInfo.getDegrees() <= 30 && weatherInfo.getDegrees() >= 20) {
            winddegrees.setText(Html.fromHtml("<b>Windrichting:</b> " +"NNO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")"));
        } else if (weatherInfo.getDegrees() <= 50 && weatherInfo.getDegrees() >= 40) {
            winddegrees.setText(Html.fromHtml("<b>Windrichting:</b> " +"NO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")"));
        } else if (weatherInfo.getDegrees() <= 70 && weatherInfo.getDegrees() >= 60) {
            winddegrees.setText(Html.fromHtml("<b>Windrichting:</b> " +"ONO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")"));
        } else if (weatherInfo.getDegrees() <= 100 && weatherInfo.getDegrees() >= 80) {
            winddegrees.setText(Html.fromHtml("<b>Windrichting:</b> " +"O" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")"));
        } else if (weatherInfo.getDegrees() <= 120 && weatherInfo.getDegrees() >= 110) {
            winddegrees.setText(Html.fromHtml("<b>Windrichting:</b> " +"OZO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")"));
        } else if (weatherInfo.getDegrees() <= 140 && weatherInfo.getDegrees() >= 130) {
            winddegrees.setText(Html.fromHtml("<b>Windrichting:</b> " +"ZO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")"));
        } else if (weatherInfo.getDegrees() <= 160 && weatherInfo.getDegrees() >= 150) {
            winddegrees.setText(Html.fromHtml("<b>Windrichting:</b> " +"ZZO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")"));
        } else if (weatherInfo.getDegrees() <= 190 && weatherInfo.getDegrees() >= 170) {
            winddegrees.setText(Html.fromHtml("<b>Windrichting:</b> " +"Z" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")"));
        } else if (weatherInfo.getDegrees() <= 210 && weatherInfo.getDegrees() >= 200) {
            winddegrees.setText(Html.fromHtml("<b>Windrichting:</b> " +"ZZW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")"));
        } else if (weatherInfo.getDegrees() <= 230 && weatherInfo.getDegrees() >= 220) {
            winddegrees.setText(Html.fromHtml("<b>Windrichting:</b> " +"ZW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")"));
        } else if (weatherInfo.getDegrees() <= 250 && weatherInfo.getDegrees() >= 240) {
            winddegrees.setText(Html.fromHtml("<b>Windrichting:</b> " +"WZW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")"));
        } else if (weatherInfo.getDegrees() <= 280 && weatherInfo.getDegrees() >= 260) {
            winddegrees.setText(Html.fromHtml("<b>Windrichting:</b> " +"W" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")"));
        } else if (weatherInfo.getDegrees() <= 300 && weatherInfo.getDegrees() >= 290) {
            winddegrees.setText(Html.fromHtml("<b>Windrichting:</b> " +"WNW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")"));
        } else if (weatherInfo.getDegrees() <= 320 && weatherInfo.getDegrees() >= 310) {
            winddegrees.setText(Html.fromHtml("<b>Windrichting:</b> " +"NW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")"));
        } else if (weatherInfo.getDegrees() <= 340 && weatherInfo.getDegrees() >= 330) {
            winddegrees.setText(Html.fromHtml("<b>Windrichting:</b> " +"NNW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")"));
        } else {
            winddegrees.setText(weatherInfo.getDegrees().toString());
        }
    }

    /**The weatherinfo isn't received correctly, an error message will be shown.*/
    @Override
    public void gotWeatherInfoError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**Method that directs users from the spot details page to the page where they can create a session.*/
    public void createSession(View view) {

        Intent intent1 = getIntent();
        String title = intent1.getStringExtra("Title");

        Intent intent2 = new Intent(this, CreateSessionActivity.class);
        intent2.putExtra("Title", title);

        startActivity(intent2);
    }
}
