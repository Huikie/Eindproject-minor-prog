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
    Callback callback_activity;
    ArrayList<Spot> spots = new ArrayList<>();

    /**Method that makes a callback possible.*/
    public interface Callback {
        void gotSpots(ArrayList<Spot> spots);
        void gotSpotsError(String message);
    }

    public SpotGetRequest(Context context) {
        this.context = context;
    }

    /**Method that gets the sessions from the API.*/
    public void getSpots(Callback activity){
        callback_activity = activity;
        String json_url = "https://ide50-huikie.legacy.cs50.io:8080/list2";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(json_url,this,this);
        queue.add(jsonArrayRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONArray response) {
        try{
            for (int i = 0; i < response.length(); i++){
                JSONObject session_info = response.getJSONObject(i);
                String name = session_info.getString("name");
                String type = session_info.getString("type");
                String surface = session_info.getString("surface");
                Integer distance = parseInt(session_info.getString("distance"));
                Integer imageId = parseInt(session_info.getString("imageId"));
                Integer directionId = parseInt(session_info.getString("directionId"));
                Double lat = parseDouble(session_info.getString("lat"));
                Double lon = parseDouble(session_info.getString("lon"));
                spots.add(new Spot(name,type,surface,distance,imageId,directionId,lat,lon));

            }
            // The sessions ArrayList is send to the activity that wanted it.
            callback_activity.gotSpots(spots);
        }
        catch(JSONException e){
            System.out.println(e.toString());
        }
    }
}
