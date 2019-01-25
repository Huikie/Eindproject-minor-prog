
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

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

        // Determine the width and the height of the popwindow that is displayed when the user clicks the "addmarkerbutton".
        getWindow().setLayout((int)(width*.6),(int)(height*.31));

    }
}
