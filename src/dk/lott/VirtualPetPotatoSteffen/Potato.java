package dk.lott.VirtualPetPotatoSteffen;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.widget.Toast;
import dk.sdc14.VirtualPetPotatoSteffen.R;

public class Potato {

    Time time = new Time();

    public final static long MIN_HUNGER = 0;
    long hunger = 500;
    public final static long MAX_HUNGER = 1000;

    public final static long MIN_HAPPINESS = 0;
    long happiness = 500;
    public final static long MAX_HAPPINESS = 1000;
    long clickcount = 0;

    public final static long MIN_THIRST = 0;
    long thirst = 500;
    public final static long MAX_THIRST = 1000;

    public final static long MIN_ENERGY = 0;
    long energy = 500;
    public final static long MAX_ENERGY = 1000;
    long energyrest;

    public MediaPlayer mediaPlayer;
    boolean sover = false;

    OnDeathLister onDeathLister;


    public Potato(OnDeathLister onDeathLister) {
        this.onDeathLister = onDeathLister;
    }

    public void Limits() {
        if (hunger <= 0) {
            hunger = 0;
        } else if (hunger >= 1000) {
            hunger = 1000;
        }
        if (thirst <= 0) {
            thirst = 0;
        } else if (thirst >= 1000) {
            thirst = 1000;
        }
        if (energy <= 0) {
            energy = 0;
        } else if (energy >= 1000) {
            energy = 1000;
        }
        if (happiness <= 0) {
            happiness = 0;
        } else if (happiness >= 1000) {
            happiness = 1000;
        }
    }

    public boolean dead = false;


    public interface OnDeathLister {
        public void onDeath();
    }

    public void resetPotatoStats() {
        hunger = 500;
        happiness = 500;
        thirst = 500;
        energy = 500;
        dead = false;
    }

    // if (hunger != MAX_HUNGER) {
    public void eat() {
        hunger = hunger + 37;
        energy = energy - 5;

        System.out.println("Hunger:" + hunger);
    }

    // if (thirst != MAX_THIRST) {
    public void drink() {
        thirst = thirst + 33;
        energy = energy - 5;

        deathCheck();
        System.out.println("thirst:" + thirst);
    }

    //if (happiness != MAX_HAPPINESS) {
    public void play() {
        if (energy >= 25) {
            happiness = happiness + 31;
            energy = energy - 25;
        }
        deathCheck();
        System.out.println("Happiness:" + happiness);
    }

    //energy != MAX_ENERGY
    public void coffee() {
        energy = energy + 21;
        hunger = hunger - 5;
        deathCheck();
        System.out.println("Energy:" + energy);
    }

    public void restfirst(Context context) {
        time.onPause();
        Toast.makeText(context, "Potato Steffen Started Resting", Toast.LENGTH_LONG).show();
        System.out.println("Potato Steffen Started Resting= Energy:" + energy);
        energyrest = 0;
        mediaPlayer = MediaPlayer.create(context, R.raw.snore);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        sover = true;


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("IS_RESTING", true);
        editor.commit();
    }

    public void restthird(Context context) {
        energyrest = time.timeRes + time.timeRes + time.timeRes + time.timeRes;
        Toast.makeText(context, "Potato Steffen Rested : " + energyrest + "" + " Energy", Toast.LENGTH_LONG).show();
        System.out.println("Energy Rested : " + energyrest);
        energy += energyrest;
        sover = false;


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("IS_RESTING", false);
        editor.commit();
    }

    public void mediaplaystop() {
        mediaPlayer.stop();
    }

    public void onPause() {
        time.onPause();

    }

    public void onResume(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (prefs.getBoolean("IS_RESTING", false))
            restthird(context);
        System.out.println("Hunger:" + hunger);
        System.out.println("Thirst:" + thirst);
        System.out.println("Happiness:" + happiness);
        System.out.println("Energy:" + energy);
    }

    public void save(SharedPreferences preferences) {
        SharedPreferences.Editor editorSave = preferences.edit();
        editorSave.putLong("hunger", hunger);
        editorSave.putLong("thirst", thirst);
        editorSave.putLong("happiness", happiness);
        editorSave.putLong("energy", energy);
        editorSave.putLong("time", time.timePause);
        editorSave.commit();
    }

    public void load(SharedPreferences preferences) {
        hunger = preferences.getLong("hunger", hunger);
        thirst = preferences.getLong("thirst", thirst);
        happiness = preferences.getLong("happiness", happiness);
        energy = preferences.getLong("energy", energy);
        time.timePause = preferences.getLong("time", time.timePause);
        hunger = hunger - time.timeRes;
        thirst = thirst - time.timeRes;
        energy = energy - time.timeRes;
        if (energy > 300) {
            happiness = happiness - time.timeRes;
        } else if (energy <= 300) {
            happiness = happiness - time.timeRes * 4;
        }

        if (clickcount <= 0) {
            clickcount = 0;
        } else if (clickcount != 0) {
            clickcount = clickcount - time.timeRes;
        }
        deathCheck();
        time.onResume();
    }

    public void deathCheck() {

        if ((hunger <= MIN_HUNGER || thirst <= MIN_THIRST || happiness <= MIN_HAPPINESS) && !dead) {
            dead = true;
            onDeathLister.onDeath();
        }
    }

}

/**
 (\ /)
 ( ..)
 c(")(")
 ::;;
 */
