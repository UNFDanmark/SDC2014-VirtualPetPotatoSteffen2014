package dk.lott.VPPS2015;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends Activity {

    private ReverseProgressbarView energyView;
    private ProgressbarView hungerView;
    private ReverseProgressbarView happinessView;
    private ProgressbarView thirstView;

    Potato potato = new Potato();

    public void updateBars() {
        energyView.setValues(potato.energy, Potato.MIN_ENERGY, Potato.MAX_ENERGY); //Sørens bug! skrev Potato med Stort
        hungerView.setValues(potato.hunger, Potato.MIN_HUNGER, Potato.MAX_HUNGER);
        happinessView.setValues(potato.happiness, Potato.MIN_HAPPINESS, Potato.MAX_HAPPINESS);
        thirstView.setValues(potato.thirst, Potato.MIN_THIRST, Potato.MAX_THIRST);
    }

    public SharedPreferences preferences;

    boolean sultenBool = false;
    boolean tristBool = false;
    boolean doeendeBool = false;
    boolean overdoseBool = false;
    ImageView normal;
    ImageView doeende;
    ImageView head;
    ImageView sulten;
    ImageView trist;
    ImageView traet;
    ImageView glad;
    ImageView excited;
    ImageView background;

    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        potato.load(preferences);
        PotatoService.setAlarm(getApplicationContext());

        /**
         * Faces Loading
         */

        normal = (ImageView) findViewById(R.id.normal);
        sulten = (ImageView) findViewById(R.id.sulten);
        trist = (ImageView) findViewById(R.id.trist);
        traet = (ImageView) findViewById(R.id.traet);
        glad = (ImageView) findViewById(R.id.glad);
        excited = (ImageView) findViewById(R.id.excited);
        doeende = (ImageView) findViewById(R.id.doeende);
        head = (ImageView) findViewById(R.id.head);
        background = (ImageView) findViewById(R.id.background);

        potato.diePotato(getApplicationContext());
        potato.load(preferences);

        /**
         * Toys
         */

        Button toys = (Button) findViewById(R.id.btoys);
        toys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                potato.play();
                potato.Limits();
                faces();
                updateBars();
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
                faces();
                updateBars();
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
                System.out.println("Energy:" + potato.energy);
                potato.clickcount++;
                potato.Limits();
                System.out.println("Click Count:" + potato.clickcount);
                updateBars();
                faces();
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
                potato.Limits();
                faces();
                updateBars();
            }
        });
        energyView = (ReverseProgressbarView) findViewById(R.id.energyView);
        energyView.setColor(Color.YELLOW);
        hungerView = (ProgressbarView) findViewById(R.id.hungerView);
        hungerView.setColor(Color.RED);
        happinessView = (ReverseProgressbarView) findViewById(R.id.happinessView);
        happinessView.setColor(Color.GREEN);
        thirstView = (ProgressbarView) findViewById(R.id.thirstView);
        thirstView.setColor(Color.BLUE);

        updateBars();
    }

    /**
     * Faces
     */

    public void faces() {
        if (potato.clickcount >= 10 && potato.energy > 800 && !doeendeBool) {
            excited.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            doeende.setVisibility(View.INVISIBLE);

            overdoseBool = true;
            potato.clickcount = 0;
        } else {
            overdoseBool = false;
        }

        if (potato.happiness >= 700 && !sultenBool && potato.energy > 300 && !overdoseBool) {
            glad.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            doeende.setVisibility(View.INVISIBLE);
        }

        if (potato.hunger > 300 && potato.hunger < 700 && potato.happiness > 300 && potato.happiness < 700 && potato.energy > 300 && potato.energy < 700 && potato.thirst > 300 && potato.thirst < 700) {
            normal.setVisibility(View.VISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            doeende.setVisibility(View.INVISIBLE);
        }

        if (potato.thirst <= 300 || potato.hunger <= 300 && !doeendeBool) {
            sulten.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            doeende.setVisibility(View.INVISIBLE);

            sultenBool = true;
        } else sultenBool = false;

        if (potato.happiness <= 300 && !sultenBool && !doeendeBool) {
            trist.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            doeende.setVisibility(View.INVISIBLE);

            tristBool = true;
        } else tristBool = false;

        if (potato.energy <= 300 && !sultenBool && !tristBool && !doeendeBool) {
            traet.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            doeende.setVisibility(View.INVISIBLE);
        }

        if (potato.hunger <= 100 || potato.thirst <= 100 || potato.happiness <= 100) {
            doeende.setVisibility((View.VISIBLE));
            traet.setVisibility(View.INVISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            doeendeBool = true;
        } else doeendeBool = false;

        Log.d("Energy: ", potato.energy + "");
        Log.d("Hunger: ", potato.hunger + "");
        Log.d("Happiness: ", potato.happiness + "");
        Log.d("Thirst: ", potato.thirst + "");
    }

    /**
     * Background ~Svend
     */
public void baggrund(){
    Date date = new Date();
    Calendar calendar = GregorianCalendar.getInstance();
    calendar.setTime(date);
    int hour = calendar.get(Calendar.HOUR_OF_DAY);

    if(hour == 0){
        background.setImageResource(R.drawable.happyHour);
    }
    if(hour>0&&hour<6)

    {
        background.setImageResource(R.drawable.night);
    }

    else if(hour>=6&&hour<12)

    {
        background.setImageResource(R.drawable.morning_crop);
    }

    else if(hour>=12&&hour<18)

    {
        background.setImageResource(R.drawable.midday_crop);
    }

    else if(hour>=18&&hour<24)

    {
        background.setImageResource(R.drawable.afternoon);
    }

}

    @Override
    protected void onPause() {
        super.onPause();
        potato.onPause();
        potato.save(preferences);
    }

    @Override
    protected void onResume() {
        super.onResume();
        potato.load(preferences);
        potato.onResume();
        faces();
        baggrund();
    }
}
