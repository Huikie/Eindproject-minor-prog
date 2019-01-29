
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

package com.example.daan.kitesessiesnl;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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

        // Trim the title so that the ' >>' is removed.
        String titleTrimmed = title.substring(0, title.length() - 3);

        sessionTitle.setText(titleTrimmed);

        // Create a Spinner with the number of kites someone can take, so that the user
        // can choose how many kites he/she wants to take and can put in the size(s).
        Spinner numberKites = findViewById(R.id.numbKiteSizes);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this,R.layout.spinner_textview,getResources().getStringArray(R.array.numbKiteSizes));
        myAdapter.setDropDownViewResource(R.layout.dropdown_item_textview);
        numberKites.setAdapter(myAdapter);

        // Use the editTextActivator method to show necessary EditTexts when the user chooses kitesize(s).
        editTextActivator(numberKites);

        TextView startTime = findViewById(R.id.startTime);
        TextView stopTime = findViewById(R.id.stopTime);

        // Use the chooseTime method to let users be able to pick a start and stop time with a nice time picker.
        chooseTime(startTime);
        chooseTime(stopTime);

    }

    /**Method that takes an EditText and when click on the EditText the method shows a TimePickerDialog
     * and if the user chooses a time the time will be displayed in the EditText.
     * Source: https://www.codingdemos.com/android-timepicker-edittext/*/
    public void chooseTime(final TextView time) {
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateSessionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        time.setText(String.format("%02d:%02d", hourOfDay, minutes));

                        TextInputLayout startTimeLayout = findViewById(R.id.startTimeLayout);
                        TextInputLayout stopTimeLayout = findViewById(R.id.stopTimeLayout);

                        TextView startTime = findViewById(R.id.startTime);
                        TextView stopTime = findViewById(R.id.stopTime);

                        // When the user picks a start time remove the error message if there was one.
                        if(!startTime.getText().equals("Begintijd")){
                            startTimeLayout.setError(null);
                        }

                        // When the user picks an end time remove the error message if there was one.
                        if(!stopTime.getText().equals("Eindtijd")){
                            stopTimeLayout.setError(null);
                        }

                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }
        });
    }

    /**Method that makes the amount of EditTexts visible that is equal to the amount of kites the user selects.*/
    public void editTextActivator(Spinner numberKites) {
        numberKites.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                EditText sizeOne = findViewById(R.id.firstSize);
                EditText sizeTwo = findViewById(R.id.secondSize);
                EditText sizeThree = findViewById(R.id.thirdSize);

                // When numbKites chosen is equal to 1 set only the first EditText to VISIBLE.
                if(id == 0){
                    sizeOne.setVisibility(View.VISIBLE);
                    sizeTwo.setVisibility(View.GONE);
                    sizeThree.setVisibility(View.GONE);
                }

                // When numbKites chosen is equal to 2 set the first & second EditText to VISIBLE.
                else if(id == 1){
                    sizeOne.setVisibility(View.VISIBLE);
                    sizeTwo.setVisibility(View.VISIBLE);
                    sizeThree.setVisibility(View.GONE);
                }

                // When numbKites chosen is equal to 3 set the first, second & third EditText to VISIBLE.
                else if(id == 2){
                    sizeOne.setVisibility(View.VISIBLE);
                    sizeTwo.setVisibility(View.VISIBLE);
                    sizeThree.setVisibility(View.VISIBLE);
                }
            }

            // Is for this Spinner never the case, but needs to be in method.
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**Method that allows users start a session with their information.*/
    public void startSession(View view) {

        EditText sizeOne = findViewById(R.id.firstSize);
        EditText sizeTwo = findViewById(R.id.secondSize);
        EditText sizeThree = findViewById(R.id.thirdSize);

        // Create an ArrayList of the 3 EditTexts above.
        ArrayList<EditText> sizesArray = new ArrayList<>();
        sizesArray.add(sizeOne);
        sizesArray.add(sizeTwo);
        sizesArray.add(sizeThree);

        // Build up a string of the input of the users in the EditText(s) by looping through the input.
        ArrayList<EditText> emptyEditTexts = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        // For every size EditText in the sizes array.
        for (EditText size : sizesArray) {

            // If the size EditText is visible.
            if (size.getVisibility() == View.VISIBLE) {

                // Get the text from that EditText and put it in the string builder followed by an m (meter).
                sb.append(size.getText().toString() + "m ");

                // Check which EditTexts are empty during submitting to be able to show error messages while submitting.
                if (TextUtils.isEmpty(size.getText())) {
                    emptyEditTexts.add(size);
                }

            }
        }

        TextView spotTitle = findViewById(R.id.sessionTitle);
        String spotTitleTxt = spotTitle.getText().toString();

        EditText name = findViewById(R.id.name);
        String nameTxt = name.getText().toString();

        TextView startTime = findViewById(R.id.startTime);
        TextView stopTime = findViewById(R.id.stopTime);

        String startTimeTxt = startTime.getText().toString();
        String stopTimeTxt = stopTime.getText().toString();

        TextInputLayout startTimeLayout = findViewById(R.id.startTimeLayout);
        TextInputLayout stopTimeLayout = findViewById(R.id.stopTimeLayout);

        // Create a timestamp to keep track on what day the user started his session.
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

        // Create a timestamp to keep track exactly when the user started his session.
        String timeStampExact = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(Calendar.getInstance().getTime());

        // If the name EditText is empty while submitting show an error message.
        if (TextUtils.isEmpty(name.getText())){
            name.setError("Voer een naam in!");
        }

        // If a size EditText is empty while submitting show an error message.
        if (emptyEditTexts.size() != 0){
            for (EditText size : emptyEditTexts) {
                size.setError("Voer een maat in!");
            }
        }

        // If the start time TextView is equal to 'start time' while submitting show an error message.
        if(startTime.getText().equals("Begintijd")){
            startTimeLayout.setError("Kies een begintijd!");
        }

        // If the end time TextView is equal to 'end time' while submitting show an error message.
        if(stopTime.getText().equals("Eindtijd")){
            stopTimeLayout.setError("Kies een eindtijd!");
        }

        // If none of the above if statements are the case approve the submit.
       else if(!TextUtils.isEmpty(name.getText()) && emptyEditTexts.size() == 0 && !stopTime.getText().equals("Eindtijd") && !startTime.getText().equals("Begintijd")){

            // Post session information of a user to my online database.
            SessionPostRequest x = new SessionPostRequest(this);
            x.postSession(nameTxt, sb.toString(), startTimeTxt + " - " + stopTimeTxt, spotTitleTxt, timeStamp, timeStampExact);

            // Heads the user back to the map activity after he/she started a session. Do this with a delay to be able to show them that the session was started successfully.
            // Source: https://stackoverflow.com/questions/7965494/how-to-put-some-delay-in-calling-an-activity-from-another-activity
            Toast.makeText(this, "Je sessie op de spot " + spotTitleTxt + " is succesvol gestart!",
                    Toast.LENGTH_LONG).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(CreateSessionActivity.this, MapsActivity.class);
                    startActivity(intent);
                    finish();
                }
        },2500);
    }
}
}
