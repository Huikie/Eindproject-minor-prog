package com.example.daan.kitesessiesnl;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class SpotRequestActivity extends AppCompatActivity {

    public static final int GET_FROM_GALLERY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_request);

        //getWindow().getDecorView().setBackgroundColor(Color.parseColor("#ff33b5e5"));

        // Create a Spinner with the spotTypes so that someone can choose the type for their requested spot.
        Spinner spotTypes = findViewById(R.id.spotTypes);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.spotTypes));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spotTypes.setAdapter(myAdapter);

        // Create a Spinner with the spotSurfaces so that someone can choose the surface for their requested spot.
        Spinner spotSurfaces = findViewById(R.id.spotSurfaces);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.spotSurfaces));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spotSurfaces.setAdapter(myAdapter2);

    }

    // Source: https://stackoverflow.com/questions/9107900/how-to-upload-image-from-gallery-in-android.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            //Bitmap bitmap = null;
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ImageView spotImage = findViewById(R.id.spotImage);
                spotImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
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

        Integer distance = parseInt(spotDistance.getText().toString());

        ImageView spotImage = findViewById(R.id.spotImage);

        // Converting Image from ImageView to Base64 String.
        // Source: https://stackoverflow.com/questions/16291800/converting-image-from-imageview-to-base64-string.
        BitmapDrawable drawable = (BitmapDrawable) spotImage.getDrawable();
        Bitmap bmap = drawable.getBitmap();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bmap.compress(Bitmap.CompressFormat.PNG,100,bos);
        byte[] bb = bos.toByteArray();

        // Define flags. Source: https://stackoverflow.com/questions/9436103/android-util-base64-encode-decode-flags-parameter.
        int flags = Base64.NO_WRAP | Base64.URL_SAFE;

        String image = Base64.encodeToString(bb,flags);

        Spot spot = new Spot(name,spotTypeTxt,spotSurfacesTxt,distance,image,0,0,coordinates.latitude,coordinates.longitude);

        // Do a SpotPostRequest to put the spotinformation that the user added in the online database and give it a status 0 (not approved, so not shown on the map yet).
        SpotPostRequest x = new SpotPostRequest(this);
        x.postSpot(spot.getName(),spot.getType(),spot.getSurface(),spot.getDistance(),spot.getImageId(),spot.getDirectionId(),spot.getStatus(),spot.getLat(),spot.getLon());

        Intent intent2 = new Intent(this, MapsActivity.class);
        startActivity(intent2);

    }

    public void loadImage(View view){
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }

}