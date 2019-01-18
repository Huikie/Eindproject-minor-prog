package com.example.daan.kitesessiesnl;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateSessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);

        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#ff33b5e5"));

        // Get the name from the spot where the user wants to start a session.
        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");

        TextView sessionTitle = findViewById(R.id.sessionTitle);
        sessionTitle.setText(title);

        EditText chooseTime1 = findViewById(R.id.startTime);
        EditText chooseTime2 = findViewById(R.id.stopTime);

        // Make the two EditTexts clickable and show timepicker if clicked by the user.
        chooseTime(chooseTime1);
        chooseTime(chooseTime2);



        // Create a Spinner with the different Googlemap types so that the user can choose which maptype the users wants.
        Spinner numberKites = findViewById(R.id.numbKiteSizes);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.numbKiteSizes));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberKites.setAdapter(myAdapter);

        numberKites.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                EditText sizeOne = findViewById(R.id.firstSize);
                EditText sizeTwo = findViewById(R.id.secondSize);
                EditText sizeThree = findViewById(R.id.thirdSize);
                EditText sizeFour = findViewById(R.id.fourthSize);
                EditText sizeFive = findViewById(R.id.fifthSize);

                if(id == 0){
                    sizeOne.setVisibility(View.VISIBLE);
                    sizeTwo.setVisibility(View.GONE);
                    sizeThree.setVisibility(View.GONE);
                    sizeFour.setVisibility(View.GONE);
                    sizeFive.setVisibility(View.GONE);

                }
                else if(id == 1){
                    sizeOne.setVisibility(View.VISIBLE);
                    sizeTwo.setVisibility(View.VISIBLE);
                    sizeThree.setVisibility(View.GONE);
                    sizeFour.setVisibility(View.GONE);
                    sizeFive.setVisibility(View.GONE);
                }
                else if(id == 2){
                    sizeOne.setVisibility(View.VISIBLE);
                    sizeTwo.setVisibility(View.VISIBLE);
                    sizeThree.setVisibility(View.VISIBLE);
                    sizeFour.setVisibility(View.GONE);
                    sizeFive.setVisibility(View.GONE);
                }
                else if(id == 3){
                    sizeOne.setVisibility(View.VISIBLE);
                    sizeTwo.setVisibility(View.VISIBLE);
                    sizeThree.setVisibility(View.VISIBLE);
                    sizeFour.setVisibility(View.VISIBLE);
                    sizeFive.setVisibility(View.GONE);
                }
                else{
                    sizeOne.setVisibility(View.VISIBLE);
                    sizeTwo.setVisibility(View.VISIBLE);
                    sizeThree.setVisibility(View.VISIBLE);
                    sizeFour.setVisibility(View.VISIBLE);
                    sizeFive.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**Method that lets users start a session with their information.*/
    public void startSession(View view){

        EditText sizeOne = findViewById(R.id.firstSize);
        EditText sizeTwo = findViewById(R.id.secondSize);
        EditText sizeThree = findViewById(R.id.thirdSize);
        EditText sizeFour = findViewById(R.id.fourthSize);
        EditText sizeFive = findViewById(R.id.fifthSize);

        ArrayList<EditText> al = new ArrayList<>();
        al.add(sizeOne);
        al.add(sizeTwo);
        al.add(sizeThree);
        al.add(sizeFour);
        al.add(sizeFive);

        StringBuilder sb = new StringBuilder();
        for(EditText size:al){
            if(size.getVisibility() == View.VISIBLE){
                sb.append(size.getText().toString()+"m ");
            }
        }

        TextView spotTitle = findViewById(R.id.sessionTitle);
        String spotTitle_txt = spotTitle.getText().toString();

        EditText name = findViewById(R.id.name);
        String name_txt = name.getText().toString();

        EditText chooseTime1 = findViewById(R.id.startTime);
        EditText chooseTime2 = findViewById(R.id.stopTime);

        String startTimeTxt = chooseTime1.getText().toString();
        String stopTimeTxt = chooseTime2.getText().toString();

        // Create a timestamp to keep track when the user started his session.
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

        // Post session information of a user to an online database.
        SessionPostRequest x = new SessionPostRequest(this);
        x.postSession(name_txt, sb.toString(), startTimeTxt + " - " + stopTimeTxt, spotTitle_txt, timeStamp);

        // Heads the user back to the map activity after he/she started a session.
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

    }

    /**Method that takes an EditText and when click on the EditText the method shows a TimePickerDialog
     * and if the user chooses a time the time will be displayed in the EditText.
     * Source: https://www.codingdemos.com/android-timepicker-edittext/*/
    public void chooseTime(final EditText txt){
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateSessionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        txt.setText(String.format("%02d:%02d", hourOfDay, minutes));
                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }
        });
    }
}
