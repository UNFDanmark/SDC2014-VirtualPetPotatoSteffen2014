package dk.lott.VPPS2015;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.os.Vibrator;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends Activity {

    public SharedPreferences preferences;
    Potato potato = new Potato();
    boolean sultenBool = false;
    boolean tristBool = false;
    boolean doeendeBool = false;
    boolean overdoseBool = false;
    boolean traetBool = false;
    private boolean setExcitedFace = true;
    private boolean setExcitedMirrorFace = false;

    ImageView normal;
    ImageView doeende;
    ImageView head;
    ImageView sulten;
    ImageView trist;
    ImageView traet;
    ImageView glad;
    ImageView excited;
    ImageView excited_mirrored;
    ImageView sovende;
    LinearLayout layoutBackground;

    private ReverseProgressbarView energyView;
    private ProgressbarView hungerView;
    private ReverseProgressbarView happinessView;
    private ProgressbarView thirstView;

    public void updateBars() {
        energyView.setValues(potato.energy, Potato.MIN_ENERGY, Potato.MAX_ENERGY); //Sørens bug! skrev Potato med Stort
        hungerView.setValues(potato.hunger, Potato.MIN_HUNGER, Potato.MAX_HUNGER);
        happinessView.setValues(potato.happiness, Potato.MIN_HAPPINESS, Potato.MAX_HAPPINESS);
        thirstView.setValues(potato.thirst, Potato.MIN_THIRST, Potato.MAX_THIRST);
    }

    public void vibrate(int vibSec) {
        vibSec = vibSec * 1000;
        Vibrator v = (Vibrator) this.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(vibSec);
    }

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
        excited_mirrored = (ImageView) findViewById(R.id.excited_mirrored);
        doeende = (ImageView) findViewById(R.id.doeende);
        head = (ImageView) findViewById(R.id.head);
        sovende = (ImageView) findViewById(R.id.sovende);
        layoutBackground = (LinearLayout) findViewById(R.id.background);

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
         * Coffee
         */

        Button coffee = (Button) findViewById(R.id.bcoffee);
        coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                potato.coffee();
                System.out.println("Energy:" + potato.energy);
                potato.clickcount++;
                potato.Limits();
                System.out.println("Click Count:" + potato.clickcount);
                updateBars();
                faces();
            }
        });

        /**
         * Rest
         */

        Button rest = (Button) findViewById(R.id.breast);
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                potato.restfirst(getApplicationContext());
                System.out.println("Energy:" + potato.energy);
                potato.Limits();
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
        if (potato.clickcount >= 10 && potato.energy > 800 && !doeendeBool && setExcitedFace && setExcitedMirrorFace && !potato.sover) {
            setExcitedFace = false;
            setExcitedMirrorFace = false;
            overdoseBool = true;
            potato.sover = false;
            Context context;

            new CountDownTimer(10000, 100) {
                public void onTick(long millisUntilFinish) {
                    excited.setVisibility(View.VISIBLE);
                    excited_mirrored.setVisibility(View.INVISIBLE);
                    normal.setVisibility(View.INVISIBLE);
                    sulten.setVisibility(View.INVISIBLE);
                    trist.setVisibility(View.INVISIBLE);
                    traet.setVisibility(View.INVISIBLE);
                    glad.setVisibility(View.INVISIBLE);
                    sovende.setVisibility((View.INVISIBLE));
                    doeende.setVisibility(View.INVISIBLE);
                    vibrate(1);
                    for (int i = 0; i > 10; i++) {
                        if (setExcitedFace = false) {
                            excited.setVisibility(View.INVISIBLE);
                            excited_mirrored.setVisibility(View.VISIBLE);
                            setExcitedFace = true;
                            setExcitedMirrorFace = true;
                        }
                    }
                    if (setExcitedMirrorFace = true) {
                        excited.setVisibility(View.VISIBLE);
                        excited_mirrored.setVisibility(View.INVISIBLE);
                        setExcitedMirrorFace = false;
                        setExcitedFace = false;
                    }
                }

                @Override
                public void onFinish() {
                    potato.clickcount = 0;
                    overdoseBool = false;
                    faces();
                    setExcitedFace = true;
                    setExcitedMirrorFace = false;
                }
            }.start();
        }
        if (potato.happiness >= 300 && potato.thirst >= 300 && potato.hunger >= 300 && !traetBool && !potato.sover) {
            normal.setVisibility(View.VISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            excited_mirrored.setVisibility(View.INVISIBLE);
            sovende.setVisibility((View.INVISIBLE));
            doeende.setVisibility(View.INVISIBLE);
        }
        if (potato.happiness >= 700 && potato.thirst >= 700 && potato.hunger >= 700 && !potato.sover) {
            Log.d("Happiness", "");
            glad.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            excited_mirrored.setVisibility(View.INVISIBLE);
            sovende.setVisibility((View.INVISIBLE));
            doeende.setVisibility(View.INVISIBLE);
        }
        if (potato.thirst <= 300 || potato.hunger <= 300 && !doeendeBool && !potato.sover) {
            sulten.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            excited_mirrored.setVisibility(View.INVISIBLE);
            sovende.setVisibility((View.INVISIBLE));
            doeende.setVisibility(View.INVISIBLE);

            sultenBool = true;
        } else {
            sultenBool = false;
        }

        if (potato.happiness <= 300 && !sultenBool && !doeendeBool && !potato.sover) {
            trist.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            excited_mirrored.setVisibility(View.INVISIBLE);
            sovende.setVisibility((View.INVISIBLE));
            doeende.setVisibility(View.INVISIBLE);

            tristBool = true;
        } else {
            tristBool = false;
        }

        if (potato.energy <= 300 && !sultenBool && !tristBool && !doeendeBool && !potato.sover) {
            traet.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            excited_mirrored.setVisibility(View.INVISIBLE);
            sovende.setVisibility((View.INVISIBLE));
            doeende.setVisibility(View.INVISIBLE);

            traetBool = true;
        } else {
            traetBool = false;
        }

        if (potato.hunger <= 100 || potato.thirst <= 100 || potato.happiness <= 100 && !potato.sover) {
            doeende.setVisibility((View.VISIBLE));
            traet.setVisibility(View.INVISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            excited_mirrored.setVisibility(View.INVISIBLE);
            sovende.setVisibility((View.INVISIBLE));
            doeendeBool = true;
            potato.resetPotatoStats();
        } else {
            doeendeBool = false;
        }

        if (potato.sover) {
            sovende.setVisibility((View.VISIBLE));
            doeende.setVisibility((View.INVISIBLE));
            traet.setVisibility(View.INVISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            excited_mirrored.setVisibility(View.INVISIBLE);

        }

            Log.d("Energy: ", potato.energy + "");
            Log.d("Hunger: ", potato.hunger + "");
            Log.d("Happiness: ", potato.happiness + "");
            Log.d("Thirst: ", potato.thirst + "");

    }

    /**
     * Background ~Svend
     */

    public void baggrund() {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour == 0) {
            layoutBackground.setBackgroundResource(R.drawable.happyhour);
        }
        if (hour > 0 && hour < 6) {
            layoutBackground.setBackgroundResource(R.drawable.night);
        } else if (hour >= 6 && hour < 12)

        {
            layoutBackground.setBackgroundResource(R.drawable.morning_crop);
        } else if (hour >= 12 && hour < 18)

        {
            layoutBackground.setBackgroundResource(R.drawable.midday_crop);
        } else if (hour >= 18 && hour < 24)

        {
            layoutBackground.setBackgroundResource(R.drawable.afternoon);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        potato.onPause();
        potato.mpstop();
        potato.save(preferences);
    }

    @Override
    protected void onResume() {
        super.onResume();
        potato.load(preferences);
        potato.onResume(getApplicationContext());
        faces();
        baggrund();
        updateBars();
    }
}


/**
*    (\ /)
     ( ..)
    c(")(")
*/
