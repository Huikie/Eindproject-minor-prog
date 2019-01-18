package com.example.daan.kitesessiesnl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SpotAdapter extends ArrayAdapter<Spot>{

    ArrayList<Spot> spot;

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
        TextView spotName = convertView.findViewById(R.id.spotName);
        TextView spotType = convertView.findViewById(R.id.spotType);
        TextView spotSurface = convertView.findViewById(R.id.spotSurface);
        TextView spotDistance = convertView.findViewById(R.id.spotDistance);
        TextView spotStatus = convertView.findViewById(R.id.spotStatus);


        spotName.setText(spot.get(index).getName());
        spotType.setText(spot.get(index).getType());
        spotSurface.setText(spot.get(index).getSurface());
        spotDistance.setText((spot.get(index).getDistance()).toString());

        if(spot.get(index).getStatus() == 0){
            spotStatus.setText("(in behandeling)");
        } else{
            spotStatus.setText("(zichtbaar)");
        }

        return convertView;
    }
}
