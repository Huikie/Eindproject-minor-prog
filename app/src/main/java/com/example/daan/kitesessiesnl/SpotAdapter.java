
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

package com.example.daan.kitesessiesnl;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**Adapter that is used to display the spots nicely in the SpotStatusActivity.*/
public class SpotAdapter extends ArrayAdapter<Spot>{

    private ArrayList<Spot> spot;

    // Constructor.
    public SpotAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Spot> objects) {
        super(context, resource, objects);
        spot = objects;
    }

    @NonNull
    @Override
    /**Custom view adapter that can be used to get the spot's name, type, surface and distance nicely in a ListView.*/
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spot, parent, false);
        }

        int index = position;

        // Alternate color of list items.
        // Source: https://android--code.blogspot.com/2015/08/android-listview-alternate-row-color.html
        if (index %2 == 1){

            // Set a background color for ListView regular row/item
            convertView.setBackgroundColor(Color.parseColor("#4e525a"));

        }
        else {

            // Set the background color for alternate row/item
            convertView.setBackgroundColor(Color.parseColor("#383d45"));

        }

        TextView spotName = convertView.findViewById(R.id.spotName);
        TextView spotType = convertView.findViewById(R.id.spotType);
        TextView spotSurface = convertView.findViewById(R.id.spotSurface);
        TextView spotDistance = convertView.findViewById(R.id.spotDistance);
        TextView spotStatus = convertView.findViewById(R.id.spotStatus);


        spotName.setText(spot.get(index).getName());
        spotType.setText(spot.get(index).getType());
        spotSurface.setText(spot.get(index).getSurface());

        spotDistance.setText(Html.fromHtml("<b>"+(spot.get(index).getDistance()).toString() + "m" + "</b>"+ " vanaf parkeerplaats"));

        if (spot.get(index).getStatus() == 0){
            spotStatus.setText("(in behandeling)");
        } else {
            spotStatus.setText("(zichtbaar)");
        }

        return convertView;
    }
}
