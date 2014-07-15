package dk.lott.VPPS2015;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    Potato potato = new Potato();
    Time time = new Time();
    Progbar progbar = new Progbar();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        progbar = new Progbar(getApplicationContext());
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        potato.load(preferences);
        // Hvorfor load? -Casper
/**
 * Toys
 */
        Button toys = (Button) findViewById(R.id.btoys);
        toys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                potato.play();
            }
        });
/**
 * Food
 */
        Button food = (Button) findViewById(R.id.bfood);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                potato.eat();
            }
        });
/**
 * Fucapo
 */
        Button fucapo = (Button) findViewById(R.id.bfucapo);
        fucapo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                potato.eatfucapo();
            }
        });
/**
 * Drinks
 */
        Button drinks = (Button) findViewById(R.id.bdrinks);
        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                potato.drink();
            }
        });
/**
 * Faces
 */
        ImageView body = (ImageView) findViewById(R.id.body);
        body.setVisibility(View.VISIBLE);
/**
 * Die function
 */

    }

    @Override
    protected void onPause() {
        super.onPause();
        time.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        time.onResume();
    }
}
   