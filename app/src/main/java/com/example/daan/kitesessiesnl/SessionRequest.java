
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

public class SessionRequest implements Response.Listener<JSONArray>, Response.ErrorListener{

    Context context;
    Callback callbackActivity;
    ArrayList<Session> sessions = new ArrayList<>();

    /**Method that makes a callback possible.*/
    public interface Callback {
        void gotSessions(ArrayList<Session> sessions);
        void gotSessionsError(String message);
    }

    // Constructor.
    public SessionRequest(Context context) {
        this.context = context;
    }

    /**Method that gets the sessions from the API.*/
    public void getSessions(Callback activity){
        callbackActivity = activity;
        String jsonUrl = "https://ide50-huikie.legacy.cs50.io:8080/sessionList";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(jsonUrl,this,this);
        queue.add(jsonArrayRequest);
    }

    @Override
    /**The sessions aren't received successfully, an error message will be displayed.*/
    public void onErrorResponse(VolleyError error) {
        callbackActivity.gotSessionsError(error.getMessage());
    }

    @Override
    /**The sessions are received successfully and will be added to the sessions ArrayList.*/
    public void onResponse(JSONArray response) {
        try{
            for (int i = 0; i < response.length(); i++){
                JSONObject sessionInfo = response.getJSONObject(i);
                String spot = sessionInfo.getString("spot");
                String name = sessionInfo.getString("name");
                String kite = sessionInfo.getString("kite");
                String time = sessionInfo.getString("time");
                String date = sessionInfo.getString("date");
                String exactDate = sessionInfo.getString("exactDate");
                sessions.add(new Session(name,kite,time,spot,date, exactDate));

            }

            // The sessions ArrayList is send to the activity that wanted it.
            callbackActivity.gotSessions(sessions);
        }
        catch(JSONException e){
            System.out.println(e.toString());
        }
    }
}
