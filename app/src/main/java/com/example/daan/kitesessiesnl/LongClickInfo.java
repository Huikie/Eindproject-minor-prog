
/**Daan Huikeshoven - 11066628
 * University of Amsterdam*/

package com.example.daan.kitesessiesnl;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

/**Activity that occurs when a user clicks on the add marker button.
 * The user then gets information about how he/she can do a spot request.*/
public class LongClickInfo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        // Determine the width and the height of the pop window that is displayed when the user clicks the "add marker button".
        getWindow().setLayout((int)(width*.6),(int)(height*.31));

    }
}
