package com.example.daan.kitesessiesnl;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class LongClickInfo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.3),(int)(height*.3));

    }
}
