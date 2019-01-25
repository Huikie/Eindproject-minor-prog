
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

package com.example.daan.kitesessiesnl;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class SpotGetRequest implements Response.Listener<JSONArray>, Response.ErrorListener{

    Context context;
    Callback callbackActivity;
    ArrayList<Spot> spots = new ArrayList<>();

    /**Method that makes a callback possible.*/
    public interface Callback {
        void gotSpots(ArrayList<Spot> spots);
        void gotSpotsError(String message);
    }

    public SpotGetRequest(Context context) {
        this.context = context;
    }

    /**Method that gets the spots from the API.*/
    public void getSpots(Callback activity){
        callbackActivity = activity;
        String jsonUrl = "https://ide50-huikie.legacy.cs50.io:8080/spotList";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(jsonUrl,this,this);
        queue.add(jsonArrayRequest);
    }

    @Override
    /**The spots aren't received successfully, an error message will be displayed.*/
    public void onErrorResponse(VolleyError error) {
        callbackActivity.gotSpotsError(error.getMessage());
    }

    @Override
    /**The spots are received successfully and will be added to the spots ArrayList.*/
    public void onResponse(JSONArray response) {
        try{
            for (int i = 0; i < response.length(); i++){
                JSONObject sessionInfo = response.getJSONObject(i);
                String name = sessionInfo.getString("name");
                String type = sessionInfo.getString("type");
                String surface = sessionInfo.getString("surface");
                Integer distance = parseInt(sessionInfo.getString("distance"));
                String imageId = sessionInfo.getString("imageId");
                Integer directionId = parseInt(sessionInfo.getString("directionId"));
                Integer status = parseInt(sessionInfo.getString("status"));
                Double lat = parseDouble(sessionInfo.getString("lat"));
                Double lon = parseDouble(sessionInfo.getString("lon"));
                spots.add(new Spot(name,type,surface,distance,imageId,directionId,status,lat,lon));

            }

            // The spots ArrayList is send to the activity that wanted it.
            callbackActivity.gotSpots(spots);
        }
        catch(JSONException e){
            System.out.println(e.toString());
        }
    }
}
