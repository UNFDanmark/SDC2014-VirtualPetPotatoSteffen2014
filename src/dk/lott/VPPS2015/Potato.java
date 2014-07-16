package dk.lott.VPPS2015;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class Potato {

    Time time = new Time();

    public final static long MIN_HUNGER = 0;
    long hunger = 250;
    public final static long MAX_HUNGER = 1000;

    public final static long MIN_HAPPINESS = 0;
    long happiness = 250;
    public final static long MAX_HAPPINESS = 1000;
    long clickcount;

    public final static long MIN_THIRST = 0;
    long thirst = 250;
    public final static long MAX_THIRST = 1000;

    public final static long MIN_ENERGY = 0;
    long energy = 250;
    public final static long MAX_ENERGY = 1000;

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

    public void resetPotatoStats() {
        hunger = 250;
        happiness = 250;
        thirst = 250;
        energy = 250;
    }

    public void diePotato(Context context) {
        if (hunger <= MIN_HUNGER) {
            resetPotatoStats();
            Toast.makeText(context, "Your Potato Steffen died of hunger! Shame on you!", Toast.LENGTH_LONG).show();
        } else if (thirst <= MIN_THIRST) {
            resetPotatoStats();
            Toast.makeText(context, "You fool! Potato Steffen died of thirst!", Toast.LENGTH_LONG).show();
        } else if (energy <= MIN_ENERGY) {
            resetPotatoStats();
            Toast.makeText(context, "You lily liver! Potato Steffen died of energy loss!", Toast.LENGTH_LONG).show();
        } else if (happiness <= MIN_HAPPINESS) {
            resetPotatoStats();
            Toast.makeText(context, "Your Potato Steffen died of depression! You suck!", Toast.LENGTH_LONG).show();
        } //else if (clickcount >= 10) {
            //resetPotatoStats();
            //Toast.makeText(context, "Your Potato Steffen died of a Fucapo overdose! You monster!", Toast.LENGTH_LONG).show();
        // Hilsen Svend/Sofie ~ Til en Fucapo Overdose evt.}
    }

    public void eat() {
        if (hunger != MAX_HUNGER) {
            hunger = hunger + 37;
            }
        System.out.println("Hunger:" + hunger);
    }

    public void drink() {
        if (thirst != MAX_THIRST) {
            thirst = thirst + 29;
        }
        System.out.println("thirst:" + thirst);
    }

    public void play() {
        if (happiness != MAX_HAPPINESS) {
            happiness = happiness + 31;
        }
        System.out.println("Happiness:" + happiness);
    }

    public void eatfucapo() {
        if (energy != MAX_ENERGY) {
            energy = energy + 21;

        }
        System.out.println("Energy:" + energy);
    }

    public void onPause() {
        time.onPause();
    }

    public void onResume() {
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
        editorSave.commit();

    }

    public void load(SharedPreferences preferences) {
        hunger = preferences.getLong("hunger", hunger);
        thirst = preferences.getLong("thirst", thirst);
        happiness = preferences.getLong("happiness", happiness);
        energy = preferences.getLong("energy", energy);
        hunger = hunger - time.timeRes;
        thirst = thirst - time.timeRes;
        happiness = happiness - time.timeRes;
        energy = energy - time.timeRes;
        if (clickcount <= 0) {
            clickcount = 0;
        } else if (clickcount != 0) {
            clickcount = clickcount - time.timeRes;
        }
        time.onResume();
    }
}



