package com.example.daan.kitesessiesnl;

import android.content.Context;
import android.util.Log;

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
    Callback callback_activity;
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
        callback_activity = activity;
        String json_url = "https://ide50-huikie.legacy.cs50.io:8080/sessionList";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(json_url,this,this);
        queue.add(jsonArrayRequest);
    }

    @Override
    /**The sessions aren't received successfully, an error message will be displayed.*/
    public void onErrorResponse(VolleyError error) {
        callback_activity.gotSessionsError(error.getMessage());
    }

    @Override
    /**The sessions are received successfully and will be added to the sessions ArrayList.*/
    public void onResponse(JSONArray response) {
        try{
            for (int i = 0; i < response.length(); i++){
                JSONObject session_info = response.getJSONObject(i);
                String spot = session_info.getString("spot");
                String name = session_info.getString("name");
                String kite = session_info.getString("kite");
                String time = session_info.getString("time");
                String date = session_info.getString("date");
                sessions.add(new Session(name,kite,time,spot,date));

            }

            // The sessions ArrayList is send to the activity that wanted it.
            callback_activity.gotSessions(sessions);
        }
        catch(JSONException e){
            System.out.println(e.toString());
        }
    }
}
