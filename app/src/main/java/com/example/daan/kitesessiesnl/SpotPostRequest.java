
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

package com.example.daan.kitesessiesnl;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**Helper that is used to POST spots in the online database when a spot request is done in the SpotRequestActivity.*/
public class SpotPostRequest implements Response.Listener<String>, Response.ErrorListener {

    private Context context;
    private String name, type, surface, imageId;
    private Integer distance, directionId, status;
    private Double lat,lon;

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
            params.put("imageId", imageId);
            params.put("directionId", directionId.toString());
            params.put("status", status.toString());
            params.put("lat", lat.toString());
            params.put("lon", lon.toString());
            return params;
        }
    }

    public SpotPostRequest(Context context) {
        this.context = context;
    }

    /**Method that posts the received name, kitesize, timespan & spot.*/
    public void postSpot(String spotName, String spotType, String spotSurface, Integer spotDistance, String spotImageId, Integer spotDirectionId, Integer spotStatus, Double spotLat, Double spotLong){
        name = spotName;
        type = spotType;
        surface = spotSurface;
        distance = spotDistance;
        imageId = spotImageId;
        directionId = spotDirectionId;
        status = spotStatus;
        lat = spotLat;
        lon = spotLong;
        String jsonUrl = "https://ide50-huikie.legacy.cs50.io:8080/spotList";
        RequestQueue queue = Volley.newRequestQueue(context);
        SpotPostRequest.PostRequest postRequest = new SpotPostRequest.PostRequest(Request.Method.POST,jsonUrl,this,this);

        // To prevent that a spot is posted twice.
        // Source: https://stackoverflow.com/questions/27873001/android-volley-sending-data-twice
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(postRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
    }

    @Override
    public void onResponse(String response) {
    }


}
