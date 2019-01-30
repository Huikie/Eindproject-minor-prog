
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

package com.example.daan.kitesessiesnl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.Integer.parseInt;

/**Activity in which the user can do a spot request.*/
public class SpotRequestActivity extends AppCompatActivity {

    public static final int GET_FROM_GALLERY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_request);

        // Create a Spinner with the spotTypes so that someone can choose the type for their requested spot.
        Spinner spotTypes = findViewById(R.id.spotTypes);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this,R.layout.spinner_textview,getResources().getStringArray(R.array.spotTypes));
        myAdapter.setDropDownViewResource(R.layout.dropdown_item_textview);
        spotTypes.setAdapter(myAdapter);

        // Create a Spinner with the spotSurfaces so that someone can choose the surface for their requested spot.
        Spinner spotSurfaces = findViewById(R.id.spotSurfaces);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(this,R.layout.spinner_textview,getResources().getStringArray(R.array.spotSurfaces));
        myAdapter2.setDropDownViewResource(R.layout.dropdown_item_textview);
        spotSurfaces.setAdapter(myAdapter2);

    }

    /**Method that allows users to upload a spot image and preview it.
     * Source: https://stackoverflow.com/questions/9107900/how-to-upload-image-from-gallery-in-android*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageView spotImage = findViewById(R.id.spotImage);

        // Error messages to display if image is too big or the user didn't load up an image.
        TextView errorMessage = findViewById(R.id.errorMessageSize);
        TextView errorMessageEmpty = findViewById(R.id.errorMessageEmpty);

        // Detects request codes.
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {

            Uri selectedImage = data.getData();

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                // Get the size of an image that a user tries to upload and if it's bigger than 0.5 mb show an error message.
                // Source: https://stackoverflow.com/questions/9316986/how-to-get-the-size-of-an-image-in-android
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] imageInByte = stream.toByteArray();
                long lengthbmp = imageInByte.length;

                // If size of image isn't bigger than 0.5 mb doesn't show any error messages and set the ImageView equal to that image.
                if(lengthbmp < 500000){
                    spotImage.setImageBitmap(bitmap);
                    errorMessageEmpty.setVisibility(View.GONE);
                    errorMessage.setVisibility(View.GONE);
                }

                // If size of image is bigger than 0.5 mb show error message and delete image that was possible set before.
                else{
                    errorMessage.setVisibility(View.VISIBLE);
                    spotImage.setImageDrawable(null);
                }

                // Handle possible errors.
                } catch (FileNotFoundException e) {
                    Toast.makeText(this, "Deze afbeelding kan niet worden gevonden",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast.makeText(this, "Er is geen afbeelding gevonden.",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
        }
    }

    /**Method that directs users back to the map page when they've submitted their spot request.*/
    public void submitRequest(View view) {

        Intent intent1 = getIntent();
        LatLng coordinates = intent1.getParcelableExtra("LatLng");

        EditText spotName = findViewById(R.id.spotName);
        EditText spotDistance = findViewById(R.id.spotDistance);

        Spinner spotTypes = findViewById(R.id.spotTypes);
        Spinner spotSurfaces = findViewById(R.id.spotSurfaces);

        String spotTypeTxt = spotTypes.getSelectedItem().toString();
        String spotSurfacesTxt = spotSurfaces.getSelectedItem().toString();

        String name = spotName.getText().toString();

        ImageView spotImage = findViewById(R.id.spotImage);

        // If the user didn't put in a spot name, show an error message.
        if (TextUtils.isEmpty(spotName.getText())) {
            spotName.setError("Voer een spot naam in!");
        }

        // If the user didn't put in a spot distance, show an error message.
        if (TextUtils.isEmpty(spotDistance.getText())){
            spotDistance.setError("Voer een afstand in!");
        }

        // If the user didn't put in a spot image, show an error message.
        if (spotImage.getDrawable() == null){
            TextView errorMessage = findViewById(R.id.errorMessageEmpty);
            TextView errorMessageSize = findViewById(R.id.errorMessageSize);
            errorMessageSize.setVisibility(View.GONE);
            errorMessage.setVisibility(View.VISIBLE);
        }

        // If the above if statements aren't the case, post the spot request to the online database.
        else if(!TextUtils.isEmpty(spotName.getText()) && !TextUtils.isEmpty(spotDistance.getText()) && spotImage.getDrawable() != null ) {

                Integer distance = parseInt(spotDistance.getText().toString());

                // Converting Image from ImageView to Base64 String.
                // Source: https://stackoverflow.com/questions/16291800/converting-image-from-imageview-to-base64-string
                BitmapDrawable drawable = (BitmapDrawable) spotImage.getDrawable();
                Bitmap bmap = drawable.getBitmap();

                // Resize uploaded image.
                Bitmap bmap_resized = Bitmap.createScaledBitmap(bmap, 100, 100, true);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmap_resized.compress(Bitmap.CompressFormat.PNG, 100, bos);

                // Change the image to a ByteArray.
                byte[] bb = bos.toByteArray();

                // Define flags: Source: https://stackoverflow.com/questions/9436103/android-util-base64-encode-decode-flags-parameter
                int flags = Base64.NO_WRAP | Base64.URL_SAFE;

                // Encode the ByteArray to a Base64 encoded string.
                String image = Base64.encodeToString(bb, flags);

                // Do a SpotPostRequest to put the spot information that the user added in the online database and give it a status 0 (not approved, so not shown on the map yet).
                // SpotDirectionId will also be 0 for now. Later on I might want to let users add a picture of the possible wind directions on the spot.
                SpotPostRequest x = new SpotPostRequest(this);
                x.postSpot(name, spotTypeTxt, spotSurfacesTxt, distance, image, 0, 0, coordinates.latitude, coordinates.longitude);


            // Heads the user back to the map activity after he/she started a session. Do this with a delay to be able to show them that the session was started successfully.
            // Source: https://stackoverflow.com/questions/7965494/how-to-put-some-delay-in-calling-an-activity-from-another-activity
            Toast.makeText(this, "Je spotaanvraag voor " + name + " is succesvol uitgevoerd! Je aanvraag is nu in behandeling.",
                    Toast.LENGTH_LONG).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SpotRequestActivity.this, MapsActivity.class);
                    startActivity(intent);
                    finish();
                }
            },2500);
            }
    }

    /**Method that allows users to upload an image by starting startActivityForResult when the user clicks the upload button.*/
    public void loadImage(View view){
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }

}