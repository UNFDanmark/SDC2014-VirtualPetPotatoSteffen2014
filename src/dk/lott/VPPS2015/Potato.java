package dk.lott.VPPS2015;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by sdc on 7/15/14.
 */
public class Potato {

    Time time = new Time();

    final static int MIN_HUNGER = 0; //mangler Limit
    long hunger;
    final static int MAX_HUNGER = 1000; //mangler limit

    final static int MIN_HAPPINESS = 0;
    long happiness;
    final static int MAX_HAPPINESS = 1000;
    long clickcount;

    final static int MIN_THIRST = 0;
    long thirst;
    final static int MAX_THIRST = 1000;

    final static int MIN_ENERGY = 0;
    long energy;
    final static int MAX_ENERGY = 1000;

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
        }
    }

    public void eat() {
        if (hunger != MAX_HUNGER) {
            hunger = hunger + 37;
            System.out.println("Hunger:" + hunger);
        }
    }

    public void drink() {
        if (thirst != MAX_THIRST) {
            thirst = thirst + 29;
            System.out.println("thirst:" + thirst);
        }
    }

    public void play() {
        if (happiness != MAX_HAPPINESS) {
            happiness = happiness + 31;
            System.out.println("Happiness:" + happiness);
        }
    }

    public void eatfucapo() {
        if (energy != MAX_ENERGY) {
            energy = energy + 150;
            System.out.println("Energy:" + energy);
        }
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

    public void save(SharedPreferences.Editor editorSave) {
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



