package dk.lott.VPPS2015;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

    Potato potato = new Potato();
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Progbar progbar = new Progbar(getApplicationContext());

    boolean sultenBool = false;
    boolean tristBool = false;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        progbar = new Progbar(getApplicationContext());
        potato.load(preferences);
        // Hvorfor load? -Casper
        /**
         * Faces Loading
         */
        ImageView normal = (ImageView) findViewById(R.id.normal);
        ImageView sulten = (ImageView) findViewById(R.id.sulten);
        ImageView trist = (ImageView) findViewById(R.id.trist);
        ImageView traet = (ImageView) findViewById(R.id.traet);
        ImageView glad = (ImageView) findViewById(R.id.glad);
        ImageView excited = (ImageView) findViewById(R.id.excited);



        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        potato.diePotato(getApplicationContext());
        editor = preferences.edit();
        potato.save(editor);

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
                //if (potato.clickcount != 0) {potato.clickcount++; }
                System.out.println("Click Count:" + potato.clickcount);
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
        if(potato.happinessn >= 7 && sultenBool == false && potato.energyn > 3){
            glad.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
        }
        if(potato.clickcount == 5 && potato.energyn > 8){
            excited.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
        }
        if (potato.hungern > 3 && potato.hungern < 7 && potato.happinessn > 3 && potato.happinessn < 7 && potato.energyn > 3 && potato.energyn < 7 && potato.thirstn > 3 && potato.thirstn < 7) {
            normal.setVisibility(View.VISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
        }
        if (potato.thirstn <= 3 || potato.hungern <= 3) {
            sulten.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            sultenBool = true;
        } else sultenBool = false;
        if (potato.happinessn <= 3 && sultenBool == false) {
            trist.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);

            tristBool = true;
        } else tristBool = false;
        if (potato.energyn <= 3 && sultenBool == false && tristBool == false) {
            traet.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
        }


        ImageView body = (ImageView) findViewById(R.id.body);
        body.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        potato.onPause();
        potato.save(editor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        potato.load(preferences);
        potato.onResume();
    }
}
   