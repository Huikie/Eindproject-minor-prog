
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

package com.example.daan.kitesessiesnl;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**Activity in which the user can start a session on a certain spot.*/
public class CreateSessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);

        // Get the name from the spot where the user wants to start a session.
        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");

        TextView sessionTitle = findViewById(R.id.sessionTitle);
        sessionTitle.setText(title);

        EditText startTime = findViewById(R.id.startTime);
        EditText stopTime = findViewById(R.id.stopTime);

        // Use the chooseTime method defined below to let users be able to pick a start and stop time with a nice time picker.
        chooseTime(startTime);
        chooseTime(stopTime);

        // Create a Spinner with the number of kites someone can take, so that the user can choose how many kites he/she wants to take and can put in the size(s).
        Spinner numberKites = findViewById(R.id.numbKiteSizes);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this,R.layout.spinner_textview,getResources().getStringArray(R.array.numbKiteSizes));
        myAdapter.setDropDownViewResource(R.layout.dropdown_item_textview);
        numberKites.setAdapter(myAdapter);

        // Create an OnItemSelectedListener for the created Spinnner.
        numberKites.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                EditText sizeOne = findViewById(R.id.firstSize);
                EditText sizeTwo = findViewById(R.id.secondSize);
                EditText sizeThree = findViewById(R.id.thirdSize);
                EditText sizeFour = findViewById(R.id.fourthSize);
                EditText sizeFive = findViewById(R.id.fifthSize);

                // When numbKites chosen is equal to 1 set only the first EditText to VISIBLE.
                if(id == 0){
                    sizeOne.setVisibility(View.VISIBLE);
                    sizeTwo.setVisibility(View.GONE);
                    sizeThree.setVisibility(View.GONE);
                    sizeFour.setVisibility(View.GONE);
                    sizeFive.setVisibility(View.GONE);

                }

                // When numbKites chosen is equal to 2 set the first & second EditText to VISIBLE.
                else if(id == 1){
                    sizeOne.setVisibility(View.VISIBLE);
                    sizeTwo.setVisibility(View.VISIBLE);
                    sizeThree.setVisibility(View.GONE);
                    sizeFour.setVisibility(View.GONE);
                    sizeFive.setVisibility(View.GONE);
                }

                // When numbKites chosen is equal to 3 set the first, second & third EditText to VISIBLE.
                else if(id == 2){
                    sizeOne.setVisibility(View.VISIBLE);
                    sizeTwo.setVisibility(View.VISIBLE);
                    sizeThree.setVisibility(View.VISIBLE);
                    sizeFour.setVisibility(View.GONE);
                    sizeFive.setVisibility(View.GONE);
                }

                // When numbKites chosen is equal to 4 set the first, second, third & fourth EditText to VISIBLE.
                else if(id == 3){
                    sizeOne.setVisibility(View.VISIBLE);
                    sizeTwo.setVisibility(View.VISIBLE);
                    sizeThree.setVisibility(View.VISIBLE);
                    sizeFour.setVisibility(View.VISIBLE);
                    sizeFive.setVisibility(View.GONE);
                }

                // When numbKites chosen is equal to 5 set all the EditTexts to VISIBLE.
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

    /**Method that takes an EditText and when click on the EditText the method shows a TimePickerDialog
     * and if the user chooses a time the time will be displayed in the EditText.
     * Source: https://www.codingdemos.com/android-timepicker-edittext/*/
    public void chooseTime(final EditText editText){
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateSessionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        editText.setText(String.format("%02d:%02d", hourOfDay, minutes));
                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }
        });
    }

    /**Method that allows users start a session with their information.*/
    public void startSession(View view){

        EditText sizeOne = findViewById(R.id.firstSize);
        EditText sizeTwo = findViewById(R.id.secondSize);
        EditText sizeThree = findViewById(R.id.thirdSize);
        EditText sizeFour = findViewById(R.id.fourthSize);
        EditText sizeFive = findViewById(R.id.fifthSize);

        // Create an ArrayList of the 5 EditTexts above.
        ArrayList<EditText> al = new ArrayList<>();
        al.add(sizeOne);
        al.add(sizeTwo);
        al.add(sizeThree);
        al.add(sizeFour);
        al.add(sizeFive);

        // Build up a string of the input of the users in the EditText(s) by looping through the input.
        StringBuilder sb = new StringBuilder();
        for(EditText size:al){
            if(size.getVisibility() == View.VISIBLE){
                sb.append(size.getText().toString()+"m ");
            }
        }

        TextView spotTitle = findViewById(R.id.sessionTitle);
        String spotTitleTxt = spotTitle.getText().toString();

        EditText name = findViewById(R.id.name);
        String nameTxt = name.getText().toString();

        EditText startTime = findViewById(R.id.startTime);
        EditText stopTime = findViewById(R.id.stopTime);

        String startTimeTxt = startTime.getText().toString();
        String stopTimeTxt = stopTime.getText().toString();

        // Create a timestamp to keep track on what day the user started his session.
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

        // Create a timestamp to keep track exactly when the user started his session.
        String timeStampExact = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(Calendar.getInstance().getTime());

        // Post session information of a user to my online database.
        SessionPostRequest x = new SessionPostRequest(this);
        x.postSession(nameTxt, sb.toString(), startTimeTxt + " - " + stopTimeTxt, spotTitleTxt, timeStamp, timeStampExact);

        // Heads the user back to the map activity after he/she started a session.
        try{Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);}catch(Exception e){Log.d("bug", "bugg");};

    }
}
