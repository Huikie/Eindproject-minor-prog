
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

package com.example.daan.kitesessiesnl;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SessionAdapter2 extends ArrayAdapter<Session> {

    ArrayList<Session> session;

    // Constructor.
    public SessionAdapter2(@NonNull Context context, int resource, @NonNull ArrayList<Session> objects) {
        super(context, resource, objects);
        session = objects;
    }

    @NonNull
    @Override
    /**Custom view adapter that can be used to get the user's name, kitesize(s), timespan and spot where the user started his session nicely in a ListView.*/
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.session2, parent, false);
        }

        int index = position;

        // Alternate color of list items.
        // Source: https://android--code.blogspot.com/2015/08/android-listview-alternate-row-color.html
        if(index %2 == 1){

            // Set a background color for ListView regular row/item
            convertView.setBackgroundColor(Color.parseColor("#4e525a"));

        }
        else{

            // Set the background color for alternate row/item
            convertView.setBackgroundColor(Color.parseColor("#383d45"));

        }

        TextView name = convertView.findViewById(R.id.kiterName);
        TextView kitesize = convertView.findViewById(R.id.kiteSize);
        TextView spotName = convertView.findViewById(R.id.spotName);
        TextView time = convertView.findViewById(R.id.timeSpan);

        name.setText(session.get(index).getName());
        kitesize.setText(session.get(index).getKite());
        spotName.setText(session.get(index).getSpot());
        time.setText(session.get(index).getTime());

        return convertView;
    }
}
