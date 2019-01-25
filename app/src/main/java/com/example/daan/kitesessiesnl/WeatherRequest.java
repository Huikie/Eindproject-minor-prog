
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

package com.example.daan.kitesessiesnl;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherRequest implements com.android.volley.Response.Listener<JSONObject>, com.android.volley.Response.ErrorListener{
    Context context;
    Callback callbackActivity;

    /**Method that makes it possible to do a callback from a different activity.*/
    public interface Callback {
        void gotWeatherInfo(WeatherInfo weatherInfo);
        void gotWeatherInfoError(String message);
    }

    // Constructor
    public WeatherRequest(Context context) {
        this.context = context;
    }

    /**This method will attempt to retrieve weather info from the API from the coordinates of the marker
     * where the user did click on, and if successful, will notify the activity that instantiated the request
     *  that it is done through the callback. */
    public void getWeatherInfo(Callback activity, Double lat, Double lon){
        callbackActivity = activity;

        //Do an API request to an url and put the latitude and longitude from the marker that is clicked on the right places in the url.
        String jsonUrl = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=3202ffc700f83be2f43e2114aa41d0a3";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(jsonUrl, null, this,this);
        queue.add(jsonObjectRequest);
    }

    @Override
    /**When something goes wrong with getting the weatherInfo this method displays an error message.*/
    public void onErrorResponse(VolleyError error) {
        callbackActivity.gotWeatherInfoError(error.getMessage());
    }

    @Override
    /**When everything goes as expected the response parameter will contain a JSONObject with weatherinfo where the wanted information can be extracted from.*/
    public void onResponse(JSONObject response) {
        try {

            Integer windspeed = response.getJSONObject("wind").getInt("speed");
            Integer winddegrees = response.getJSONObject("wind").getInt("deg");
            Integer temperature = response.getJSONObject("main").getInt("temp");
            WeatherInfo weatherInfo = new WeatherInfo(windspeed, winddegrees, temperature);

            // Pass the ArrayList back to the activity that wanted to have it.
            callbackActivity.gotWeatherInfo(weatherInfo);

        }

        catch(JSONException e){
            System.out.println(e.toString());
        }


    }

}
