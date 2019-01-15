package com.example.daan.kitesessiesnl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SpotDetailsActivity extends AppCompatActivity implements WeatherRequest.Callback, SessionRequest.Callback {

    /**Method that makes sure that only the sessions are displayed from the spot that is clicked on by the user.*/
    @Override
    public void gotSessions(ArrayList<Session> sessions) {
        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");
        ArrayList<Session> s2 = new ArrayList<>();
        for (Session session: sessions) {
            if(session.getSpot().equals(title)){
                s2.add(new Session(session.getName(),session.getKite(),session.getTime(),session.getSpot()));

                // Using the created SessionAdapter to put the spot's sessions nicely in a ListView.
                SessionAdapter itemsAdapter = new SessionAdapter(this, R.layout.session, s2);
                ListView session_list = findViewById(R.id.kitersList);
                session_list.setAdapter(itemsAdapter);
            }
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

        windspeed.setText(roundKnots.toString() + " " + "knopen");
        temperature.setText(roundCelsius.toString() + " " + "℃");

        // Determined the wind direction in letters based on the degree received from the API.
        // Source: https://uni.edu/storm/Wind%20Direction%20slide.pdf
        if (weatherInfo.getDegrees() <= 360 && weatherInfo.getDegrees() >= 350 || weatherInfo.getDegrees() <= 10) {
            winddegrees.setText("N" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        } else if (weatherInfo.getDegrees() <= 340 && weatherInfo.getDegrees() >= 330) {
            winddegrees.setText("NNW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        } else if (weatherInfo.getDegrees() <= 30 && weatherInfo.getDegrees() >= 20) {
            winddegrees.setText("NNO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        } else if (weatherInfo.getDegrees() <= 50 && weatherInfo.getDegrees() >= 40) {
            winddegrees.setText("NO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        } else if (weatherInfo.getDegrees() <= 70 && weatherInfo.getDegrees() >= 60) {
            winddegrees.setText("ONO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        } else if (weatherInfo.getDegrees() <= 100 && weatherInfo.getDegrees() >= 80) {
            winddegrees.setText("O" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        } else if (weatherInfo.getDegrees() <= 120 && weatherInfo.getDegrees() >= 110) {
            winddegrees.setText("OZO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        } else if (weatherInfo.getDegrees() <= 140 && weatherInfo.getDegrees() >= 130) {
            winddegrees.setText("ZO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        } else if (weatherInfo.getDegrees() <= 160 && weatherInfo.getDegrees() >= 150) {
            winddegrees.setText("ZZO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        } else if (weatherInfo.getDegrees() <= 190 && weatherInfo.getDegrees() >= 170) {
            winddegrees.setText("Z" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        } else if (weatherInfo.getDegrees() <= 210 && weatherInfo.getDegrees() >= 200) {
            winddegrees.setText("ZZW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        } else if (weatherInfo.getDegrees() <= 230 && weatherInfo.getDegrees() >= 220) {
            winddegrees.setText("ZW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        } else if (weatherInfo.getDegrees() <= 250 && weatherInfo.getDegrees() >= 240) {
            winddegrees.setText("WZW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        } else if (weatherInfo.getDegrees() <= 280 && weatherInfo.getDegrees() >= 260) {
            winddegrees.setText("W" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        } else if (weatherInfo.getDegrees() <= 300 && weatherInfo.getDegrees() >= 290) {
            winddegrees.setText("WNW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        } else if (weatherInfo.getDegrees() <= 320 && weatherInfo.getDegrees() >= 310) {
            winddegrees.setText("NW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        } else if (weatherInfo.getDegrees() <= 340 && weatherInfo.getDegrees() >= 330) {
            winddegrees.setText("NNW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        } else {
            winddegrees.setText(weatherInfo.getDegrees().toString());
        }
    }

    /**The weatherinfo isn't received correctly, an error message will be shown.*/
    @Override
    public void gotWeatherInfoError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_details);

        Intent intent = getIntent();
        LatLng coordinates = intent.getParcelableExtra("LatLng");
        String title = intent.getStringExtra("Title");
        String type = intent.getStringExtra("Type");
        String surface = intent.getStringExtra("Surface");
        String distance = intent.getStringExtra("Distance");

        TextView spotdetailsTitle = findViewById(R.id.spotdetails_Title);
        spotdetailsTitle.setText(title);

        TextView spotdetailsType = findViewById(R.id.type);
        spotdetailsType.setText(type);

        TextView spotdetailsSurface = findViewById(R.id.surface);
        spotdetailsSurface.setText(surface);

        TextView spotdetailsDistance = findViewById(R.id.distance);
        spotdetailsDistance.setText(distance);


        WeatherRequest x = new WeatherRequest(this);
        try {
            x.getWeatherInfo(this, coordinates.latitude, coordinates.longitude);
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

        SessionRequest x2 = new SessionRequest(this);
        x2.getSessions(this);
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
