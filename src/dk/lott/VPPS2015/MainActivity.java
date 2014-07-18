package dk.lott.VPPS2015;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.os.Vibrator;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends Activity {

    public SharedPreferences preferences;
    Potato potato;
    boolean sultenBool = false;
    boolean tristBool = false;
    boolean doeendeBool = false;
    boolean overdoseBool = false;
    boolean traetBool = false;
    private boolean setExcitedFace = true;
    boolean smerteBool = false;
    boolean aeBool = false;

    ImageView normal;
    ImageView doeende;
    ImageView head;
    ImageView sulten;
    ImageView trist;
    ImageView traet;
    ImageView glad;
    ImageView excited;
    ImageView body;
    ImageView smerte;
    ImageView sovende;
    ImageView sDhun;
    ImageView sDhap;
    ImageView sDene;
    ImageView sDthi;
    ImageView ae;

    LinearLayout layoutBackground;

    private ReverseProgressbarView energyView;
    private ProgressbarView hungerView;
    private ReverseProgressbarView happinessView;
    private ProgressbarView thirstView;

    /**
     * indikator INDE I APPEN for om Potato Steffen er ved at dø (små røde udråbstegn)
     */

    public void soondead() {
        sDhun = (ImageView) findViewById(R.id.sDhun);
        sDene = (ImageView) findViewById(R.id.sDene);
        sDthi = (ImageView) findViewById(R.id.sDthi);
        sDhap = (ImageView) findViewById(R.id.sDhap);
        if (potato.hunger <= 200) {
            sDhun.setVisibility(View.VISIBLE);
            sDene.setVisibility(View.INVISIBLE);
            sDthi.setVisibility(View.INVISIBLE);
            sDhap.setVisibility(View.INVISIBLE);
        } else {
            sDhun.setVisibility(View.INVISIBLE);
        }

        if (potato.energy <= 200) {
            sDene.setVisibility(View.VISIBLE);
            sDhun.setVisibility(View.INVISIBLE);
            sDthi.setVisibility(View.INVISIBLE);
            sDhap.setVisibility(View.INVISIBLE);
        } else {
            sDene.setVisibility(View.INVISIBLE);
        }

        if (potato.thirst <= 200) {
            sDthi.setVisibility(View.VISIBLE);
            sDhun.setVisibility(View.INVISIBLE);
            sDene.setVisibility(View.INVISIBLE);
            sDhap.setVisibility(View.INVISIBLE);
        } else {
            sDthi.setVisibility(View.INVISIBLE);
        }

        if (potato.happiness <= 200) {
            sDhap.setVisibility(View.VISIBLE);
            sDhun.setVisibility(View.INVISIBLE);
            sDene.setVisibility(View.INVISIBLE);
            sDthi.setVisibility(View.INVISIBLE);
        } else {
            sDhap.setVisibility(View.INVISIBLE);
        }
    }

    public void updateBars() {
        energyView.setValues(potato.energy, Potato.MIN_ENERGY, Potato.MAX_ENERGY); //Sørens bug! skrev Potato med Stort
        hungerView.setValues(potato.hunger, Potato.MIN_HUNGER, Potato.MAX_HUNGER);
        happinessView.setValues(potato.happiness, Potato.MIN_HAPPINESS, Potato.MAX_HAPPINESS);
        thirstView.setValues(potato.thirst, Potato.MIN_THIRST, Potato.MAX_THIRST);
        soondead();
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
        potato = new Potato(new Potato.OnDeathLister() {
            @Override
            public void onDeath() {
                deathmenu();
            }
        });
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
        smerte = (ImageView) findViewById(R.id.smerte);
        head = (ImageView) findViewById(R.id.head);
        body = (ImageView) findViewById(R.id.body);
        sovende = (ImageView) findViewById(R.id.sovende);
        ae = (ImageView) findViewById(R.id.ae);
        layoutBackground = (LinearLayout) findViewById(R.id.background);
        potato.load(preferences);

        /**
         * Toys
         */

        Button toys = (Button) findViewById(R.id.btoys);
        toys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setExcitedFace) {
                    potato.play();
                    potato.Limits();
                    faces();
                    updateBars();
                }
            }
        });

        /**
         * Food
         */

        Button food = (Button) findViewById(R.id.bfood);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setExcitedFace) {
                    potato.eat();
                    potato.Limits();
                    faces();
                    updateBars();
                }
            }
        });

        /**
         * Coffee
         */

        Button coffee = (Button) findViewById(R.id.bcoffee);
        coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setExcitedFace) {
                    potato.coffee();
                    System.out.println("Energy:" + potato.energy);
                    potato.clickcount++;
                    potato.Limits();
                    System.out.println("Click Count:" + potato.clickcount);
                    updateBars();
                    faces();
                }
            }
        });

        /**
         * Rest
         */

        Button rest = (Button) findViewById(R.id.breast);
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setExcitedFace) {
                    potato.restfirst(getApplicationContext());
                    System.out.println("Energy:" + potato.energy);
                    potato.Limits();
                    updateBars();
                    faces();
                }
            }
        });

        /**
         * Drinks
         */

        Button drinks = (Button) findViewById(R.id.bdrinks);
        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setExcitedFace) {
                    potato.drink();
                    potato.Limits();
                    faces();
                    updateBars();
                }
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


        //AE OG SMERTE
        //SMERTE FØRST

        ImageView body = (ImageView) findViewById(R.id.body);

        body.setOnLongClickListener(new View.OnLongClickListener() {

                                        @Override
                                        public boolean onLongClick(View v) {
                                            potato.happiness = potato.happiness + 150;
                                            aeBool = true;
                                            updateBars();
                                            faces();


                                            new CountDownTimer(3000, 100) {
                                                public void onTick(long millisUntilFinish) {
                                                    excited.setVisibility(View.INVISIBLE);
                                                    normal.setVisibility(View.INVISIBLE);
                                                    sulten.setVisibility(View.INVISIBLE);
                                                    trist.setVisibility(View.INVISIBLE);
                                                    traet.setVisibility(View.INVISIBLE);
                                                    glad.setVisibility(View.INVISIBLE);
                                                    doeende.setVisibility(View.INVISIBLE);
                                                    sovende.setVisibility(View.INVISIBLE);
                                                    smerte.setVisibility(View.INVISIBLE);
                                                    ae.setVisibility(View.VISIBLE);
                                                }

                                                @Override
                                                public void onFinish() {
                                                    aeBool = false;
                                                    faces();
                                                    updateBars();
                                                }
                                            }.start();
                                            aeBool = false;
                                            return true;

                                        }


                                    }


        );

        body.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        potato.happiness = potato.happiness - 50;
                                        updateBars();
                                        faces();
                                        smerteBool = true;
                                        vibrate(1);
                                        new CountDownTimer(1000, 100) {
                                            public void onTick(long millisUntilFinish) {
                                                excited.setVisibility(View.INVISIBLE);
                                                normal.setVisibility(View.INVISIBLE);
                                                sulten.setVisibility(View.INVISIBLE);
                                                trist.setVisibility(View.INVISIBLE);
                                                traet.setVisibility(View.INVISIBLE);
                                                glad.setVisibility(View.INVISIBLE);
                                                doeende.setVisibility(View.INVISIBLE);
                                                sovende.setVisibility(View.INVISIBLE);
                                                ae.setVisibility(View.INVISIBLE);
                                                smerte.setVisibility(View.VISIBLE);
                                            }

                                                @Override
                                                public void onFinish() {
                                                    smerteBool = false;
                                                    faces();
                                                    updateBars();
                                                    potato.deathCheck();
                                                }
                                            }.start();
                                        }


                                }


        );
    }


    /**
     * Død funktion. Now even better. 10/10 would use again
     */

    public void deathmenu() {

        final AlertDialog.Builder alertdialog = new AlertDialog.Builder(this)
                .setTitle("You killed Potato Steffen")
                .setMessage("You didn't take good enough care of him! Do you wish to clone your late Potato Steffen, you monster? ")
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        potato.resetPotatoStats();
                        Toast.makeText(getApplicationContext(), "Fine. Just try not to kill him. Yes, we know what you did.", Toast.LENGTH_LONG).show();
                        faces();
                        updateBars();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        potato.resetPotatoStats();
                        Toast.makeText(getApplicationContext(), "You must clone him! FOR SCIENCE! Just please try to not kill this one. Please.", Toast.LENGTH_LONG).show();
                        faces();
                        updateBars();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert);

        alertdialog.show();


    }

    /**
     * Faces
     */

    public void faces() {
        if (potato.clickcount >= 10 && potato.energy > 800 && !doeendeBool && setExcitedFace && !smerteBool && !potato.sover) {
            setExcitedFace = false;
            overdoseBool = true;
            potato.sover = false;

            new CountDownTimer(10000, 100) {
                public void onTick(long millisUntilFinish) {
                    excited.setVisibility(View.VISIBLE);
                    normal.setVisibility(View.INVISIBLE);
                    sulten.setVisibility(View.INVISIBLE);
                    trist.setVisibility(View.INVISIBLE);
                    traet.setVisibility(View.INVISIBLE);
                    glad.setVisibility(View.INVISIBLE);
                    sovende.setVisibility((View.INVISIBLE));
                    doeende.setVisibility(View.INVISIBLE);
                    smerte.setVisibility(View.INVISIBLE);
                    ae.setVisibility(View.INVISIBLE);

                    vibrate(1);

                }

                @Override
                public void onFinish() {
                    potato.clickcount = 0;
                    overdoseBool = false;
                    faces();
                    setExcitedFace = true;
                }
            }.start();
        }
        if (potato.happiness >= 300 && potato.thirst >= 300 && potato.hunger >= 300 && !traetBool && !smerteBool) {
            normal.setVisibility(View.VISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            sovende.setVisibility((View.INVISIBLE));
            doeende.setVisibility(View.INVISIBLE);
            smerte.setVisibility(View.INVISIBLE);
            ae.setVisibility(View.INVISIBLE);
        }
        if (potato.happiness >= 700 && potato.thirst >= 700 && potato.hunger >= 700 && !smerteBool && !potato.sover && !overdoseBool) {
            Log.d("Happiness", "");
            glad.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            sovende.setVisibility((View.INVISIBLE));
            doeende.setVisibility(View.INVISIBLE);
            smerte.setVisibility(View.INVISIBLE);
            ae.setVisibility(View.INVISIBLE);
        }


        if (potato.thirst <= 300 || potato.hunger <= 300 && !doeendeBool && !smerteBool && !potato.sover && !overdoseBool) {
            sulten.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            sovende.setVisibility((View.INVISIBLE));
            doeende.setVisibility(View.INVISIBLE);
            smerte.setVisibility(View.INVISIBLE);
            ae.setVisibility(View.INVISIBLE);

            sultenBool = true;
        } else {
            sultenBool = false;
        }

        if (potato.happiness <= 300 && !sultenBool && !doeendeBool && !smerteBool && !potato.sover && !overdoseBool) {
            trist.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            traet.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            sovende.setVisibility((View.INVISIBLE));
            doeende.setVisibility(View.INVISIBLE);
            smerte.setVisibility(View.INVISIBLE);
            ae.setVisibility(View.INVISIBLE);

            tristBool = true;
        } else {
            tristBool = false;
        }

        if (potato.energy <= 300 && !sultenBool && !tristBool && !doeendeBool && !smerteBool && !potato.sover && !overdoseBool) {
            traet.setVisibility(View.VISIBLE);
            normal.setVisibility(View.INVISIBLE);
            sulten.setVisibility(View.INVISIBLE);
            trist.setVisibility(View.INVISIBLE);
            glad.setVisibility(View.INVISIBLE);
            excited.setVisibility(View.INVISIBLE);
            sovende.setVisibility((View.INVISIBLE));
            doeende.setVisibility(View.INVISIBLE);
            smerte.setVisibility(View.INVISIBLE);
            ae.setVisibility(View.INVISIBLE);

            traetBool = true;
        } else {
            traetBool = false;
        }

            if (potato.hunger <= 100 || potato.thirst <= 100 || potato.happiness <= 100 && !smerteBool && !potato.sover && !overdoseBool) {
                doeende.setVisibility((View.VISIBLE));
                traet.setVisibility(View.INVISIBLE);
                normal.setVisibility(View.INVISIBLE);
                sulten.setVisibility(View.INVISIBLE);
                trist.setVisibility(View.INVISIBLE);
                glad.setVisibility(View.INVISIBLE);
                excited.setVisibility(View.INVISIBLE);
                sovende.setVisibility((View.INVISIBLE));
                smerte.setVisibility(View.INVISIBLE);
                ae.setVisibility(View.INVISIBLE);
                doeendeBool = true;
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
                smerte.setVisibility(View.INVISIBLE);
                excited.setVisibility(View.INVISIBLE);
                ae.setVisibility(View.INVISIBLE);

        }

        Log.d("Energy: ", potato.energy + "");
        Log.d("Hunger: ", potato.hunger + "");
        Log.d("Happiness: ", potato.happiness + "");
        Log.d("Thirst: ", potato.thirst + "");


    }

    /**
     * Background
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

        if (potato.mediaPlayer != null) {
            if (potato.mediaPlayer.isLooping() || potato.mediaPlayer.isPlaying()) {
                potato.mediaplaystop();
            }
        }

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
 ::
*/
