package com.example.daan.kitesessiesnl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SessionAdapter extends ArrayAdapter<Session> {

    ArrayList<Session> session;

    public SessionAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Session> objects) {
        super(context, resource, objects);
        session = objects;
    }
    @NonNull
    @Override
    /**Custom view adapter that can be used to get the user's name, kitesize(s) and timespan nicely in a ListView*/
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.session, parent, false);
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
