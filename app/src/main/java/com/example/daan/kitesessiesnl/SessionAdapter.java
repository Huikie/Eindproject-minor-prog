
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

package com.example.daan.kitesessiesnl;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.util.ArrayList;

/**Adapter that is used to display the sessions nicely in the SpotDetailsActivity.*/
public class SessionAdapter extends ArrayAdapter<Session> {

    private ArrayList<Session> session;

    // Constructor.
    public SessionAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Session> objects) {
        super(context, resource, objects);
        session = objects;
    }

    @NonNull
    @Override
    /**Custom view adapter that can be used to get the user's name, kitesize(s) and timespan nicely in a ListView.*/
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.session, parent, false);
        }

        // Alternate color of list items.
        // Source: https://android--code.blogspot.com/2015/08/android-listview-alternate-row-color.html
        if(position %2 == 1){

            // Set a background color for ListView regular row/item
            convertView.setBackgroundColor(Color.parseColor("#4e525a"));

        }
        else{

            // Set the background color for alternate row/item
            convertView.setBackgroundColor(Color.parseColor("#383d45"));

        }

        int index = position;
        TextView name = convertView.findViewById(R.id.name);
        TextView kitesize = convertView.findViewById(R.id.kitesize);
        TextView time = convertView.findViewById(R.id.timespan);

        name.setText(session.get(index).getName());
        kitesize.setText(session.get(index).getKite());
        time.setText(session.get(index).getTime());

        return convertView;
    }
}
