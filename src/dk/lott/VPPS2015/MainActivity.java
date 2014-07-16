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

    boolean sultenBool = false;
    boolean tristBool = false;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        potato.resetPotatoStats();

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
        potato.load(preferences);
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
                potato.Limits();
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
                potato.clickcount++;
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
        if (potato.happiness >= 7 && !sultenBool && potato.energy > 3) {
            glad.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
        }
        if (potato.clickcount >= 5 && potato.energy > 8) {
            excited.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            potato.clickcount = 0;
        }
        if (potato.hunger > 3 && potato.hunger < 7 && potato.happiness > 3 && potato.happiness < 7 && potato.energy > 3 && potato.energy < 7 && potato.thirst > 3 && potato.thirst < 7) {
            normal.setVisibility(View.VISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
        }
        if (potato.thirst <= 3 || potato.hunger <= 3) {
            sulten.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            sultenBool = true;
        } else sultenBool = false;
        if (potato.happiness <= 3 && !sultenBool) {
            trist.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);

            tristBool = true;
        } else tristBool = false;
        if (potato.energy <= 3 && !sultenBool && !tristBool) {
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
   