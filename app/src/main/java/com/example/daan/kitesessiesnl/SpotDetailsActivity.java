package com.example.daan.kitesessiesnl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;
import java.util.concurrent.ExecutionException;

public class SpotDetailsActivity extends AppCompatActivity implements WeatherRequest.Callback {

    @Override
    public void gotWeatherInfo(WeatherInfo weatherInfo) {
        Log.d("weatherInfo2", weatherInfo.getSpeed().toString());
        TextView windspeed = findViewById(R.id.windspeed);
        TextView winddegrees = findViewById(R.id.winddegrees);
        TextView temperature = findViewById(R.id.temperature);

        // Convert m/s to knots
        Double knots = weatherInfo.getSpeed() * 1.94375;
        Double roundKnots = Double.valueOf(new DecimalFormat("#.#").format(
                knots));

        // Convert Kelvin to Celsius
        Double celsius = weatherInfo.getTemperature() - 273.15;
        Double roundCelsius = Double.valueOf(new DecimalFormat("#.#").format(
                celsius));

        windspeed.setText(roundKnots.toString() + " " + "knopen");
        temperature.setText(roundCelsius.toString() + " " + "℃");

        // Test
//        Integer i = -1;
//        if(i <= 20 && i > 5 || i < 0){
//            Log.d("Voldoet", "Dit voldoet");
//        }

        // Determinate the winddirection in letters based on the degree.
        // Source: https://uni.edu/storm/Wind%20Direction%20slide.pdf
        if(weatherInfo.getDegrees() <= 360 && weatherInfo.getDegrees() >= 350 || weatherInfo.getDegrees() <= 10){
            winddegrees.setText("N" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        }
        else if(weatherInfo.getDegrees() <= 340 && weatherInfo.getDegrees() >= 330){
            winddegrees.setText("NNW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        }
        else if(weatherInfo.getDegrees() <= 30 && weatherInfo.getDegrees() >= 20){
            winddegrees.setText("NNO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        }
        else if(weatherInfo.getDegrees() <= 50 && weatherInfo.getDegrees() >= 40){
            winddegrees.setText("NO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        }
        else if(weatherInfo.getDegrees() <= 70 && weatherInfo.getDegrees() >= 60){
            winddegrees.setText("ONO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        }
        else if(weatherInfo.getDegrees() <= 100 && weatherInfo.getDegrees() >= 80){
            winddegrees.setText("O" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        }
        else if(weatherInfo.getDegrees() <= 120 && weatherInfo.getDegrees() >= 110){
            winddegrees.setText("OZO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        }
        else if(weatherInfo.getDegrees() <= 140 && weatherInfo.getDegrees() >= 130){
            winddegrees.setText("ZO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        }
        else if(weatherInfo.getDegrees() <= 160 && weatherInfo.getDegrees() >= 150){
            winddegrees.setText("ZZO" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        }
        else if(weatherInfo.getDegrees() <= 190 && weatherInfo.getDegrees() >= 170){
            winddegrees.setText("Z" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        }
        else if(weatherInfo.getDegrees() <= 210 && weatherInfo.getDegrees() >= 200){
            winddegrees.setText("ZZW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        }
        else if(weatherInfo.getDegrees() <= 230 && weatherInfo.getDegrees() >= 220){
            winddegrees.setText("ZW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        }
        else if(weatherInfo.getDegrees() <= 250 && weatherInfo.getDegrees() >= 240){
            winddegrees.setText("WZW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        }
        else if(weatherInfo.getDegrees() <= 280 && weatherInfo.getDegrees() >= 260){
            winddegrees.setText("W" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        }
        else if(weatherInfo.getDegrees() <= 300 && weatherInfo.getDegrees() >= 290){
            winddegrees.setText("WNW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        }
        else if(weatherInfo.getDegrees() <= 320 && weatherInfo.getDegrees() >= 310){
            winddegrees.setText("NW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        }
        else if(weatherInfo.getDegrees() <= 340 && weatherInfo.getDegrees() >= 330){
            winddegrees.setText("NNW" + " " + "(" + weatherInfo.getDegrees().toString() + " " + "º" + ")");
        }
        else{
            winddegrees.setText(weatherInfo.getDegrees().toString());
        }
    }

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

        TextView spotdetailsTitle = findViewById(R.id.spotdetails_Title);
        spotdetailsTitle.setText(title);

        WeatherRequest x = new WeatherRequest(this);
        try {
            x.getWeatherInfo(this, coordinates.latitude, coordinates.longitude);
        }
        catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
        public void createSession(View view){

    }
    }
