
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

package com.example.daan.kitesessiesnl;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SessionPostRequest implements Response.Listener<String>, Response.ErrorListener{

    Context context;
    String name,kite,time,spot,date, exactDate;

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
            params.put("kite", kite);
            params.put("time", time);
            params.put("spot", spot);
            params.put("date", date);
            params.put("exactDate", exactDate);
            return params;
        }
    }

    // Constructor.
    public SessionPostRequest(Context context) {
        this.context = context;
    }

    /**Method that posts the received name, kitesize, timespan, spot, timespan and date.*/
    public void postSession(String kiterName, String kiterKite, String sessionTime, String sessionSpot, String sessionDate, String sessionExactDate){
        spot = sessionSpot;
        name = kiterName;
        kite = kiterKite;
        time = sessionTime;
        date = sessionDate;
        exactDate = sessionExactDate;
        String jsonUrl = "https://ide50-huikie.legacy.cs50.io:8080/sessionList";
        RequestQueue queue = Volley.newRequestQueue(context);
        PostRequest postRequest = new PostRequest(Request.Method.POST, jsonUrl,this,this);

        // https://stackoverflow.com/questions/27873001/android-volley-sending-data-twice
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(postRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error){
    }

    @Override
    public void onResponse(String response) {
    }
}
