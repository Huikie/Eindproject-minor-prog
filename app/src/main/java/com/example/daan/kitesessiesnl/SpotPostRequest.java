package com.example.daan.kitesessiesnl;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SpotPostRequest implements Response.Listener<String>, Response.ErrorListener {

    Context context;
    String name, type, surface;
    Integer distance, imageId, directionId;
    Double lat,lon;

    Callback callback_activity;

    /**Method that makes a callback possible.*/
    public interface Callback {
        void postedSpots(Boolean status);
        void postedSpotsError(String message);
    }

    // Class that makes a PostRequest possible.
    public class PostRequest extends StringRequest {

        // Constructor.
        public PostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        // Method to supply parameters (name, kite, time & spot) to the request.
        @Override
        protected Map<String, String> getParams() {

            Map<String, String> params = new HashMap<>();
            params.put("name", name);
            params.put("type", type);
            params.put("surface", surface);
            params.put("distance", distance.toString());
            params.put("imageId", imageId.toString());
            params.put("directionId", directionId.toString());
            params.put("lat", lat.toString());
            params.put("lon", lon.toString());
            return params;
        }
    }

    public SpotPostRequest(Context context) {
        this.context = context;
    }

    /**Method that posts the received name, kitesize, timespan & spot.*/
    public void postSpot(Callback activity,String spot_name, String spot_type, String spot_surface, Integer spot_distance, Integer spot_imageId, Integer spot_directionId, Double spot_lat, Double spot_long){
        callback_activity = activity;
        name = spot_name;
        type = spot_type;
        surface = spot_surface;
        distance = spot_distance;
        imageId = spot_imageId;
        directionId = spot_directionId;
        lat = spot_lat;
        lon = spot_long;
        String json_url = "https://ide50-huikie.legacy.cs50.io:8080/list2";
        RequestQueue queue = Volley.newRequestQueue(context);
        SpotPostRequest.PostRequest postRequest = new SpotPostRequest.PostRequest(Request.Method.POST, json_url,this,this);
        queue.add(postRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
    }

    @Override
    public void onResponse(String response) {
        callback_activity.postedSpots(true);
    }


}
