package dk.lott.VPPS2015;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by sdc on 7/15/14.
 */
public class Potato {

    Time time = new Time();

    final static int MIN_HUNGER = 0;
    long hunger = 3;
    long hungern;
    final static int MAX_HUNGER = 10;

    final static int MIN_HAPPINESS = 0;
    long happiness = 3;
    long happinessn;
    final static int MAX_HAPPINESS = 10;
    int clickcount = 0;

    final static int MIN_THIRST = 0;
    long thirst = 3;
    long thirstn;
    final static int MAX_THIRST = 10;

    final static int MIN_ENERGY = 0;
    long energy = 3;
    long energyn;
    final static int MAX_ENERGY = 10;

    public long lose = time.timeRes;

    boolean death = false;

    public void resetPotatoStats() {
        hunger = 5;
        happiness = 5;
        thirst = 5;
        energy = 5;
    }

    public void diePotato(Context context) {
        if (hungern == MIN_HUNGER) {
            resetPotatoStats();
            Toast.makeText(context, "Your Potato Steffen died of hunger! Shame on you!", Toast.LENGTH_LONG).show();
        } else if (thirstn == MIN_THIRST) {
            resetPotatoStats();
            Toast.makeText(context, "You fool! Potato Steffen died of thirst!", Toast.LENGTH_LONG).show();
        } else if (energyn == MIN_ENERGY) {
            resetPotatoStats();
            Toast.makeText(context, "You lily liver! Potato Steffen died of energy loss!", Toast.LENGTH_LONG).show();
        } else if (happinessn == MIN_HAPPINESS) {
            resetPotatoStats();
            Toast.makeText(context, "Your Potato Steffen died of depression! You suck!", Toast.LENGTH_LONG).show();
        }
    }

    public void eat() {
        if (hungern != MAX_HUNGER) {
            hungern++;
            System.out.println("Hunger:" + hungern);
        }
    }

    public void drink() {
        if (thirstn != MAX_THIRST) {
            thirstn++;
            System.out.println("thirst:" + thirstn);
        }
    }

    public void play() {
        if (happinessn != MAX_HAPPINESS) {
            happinessn++;
            System.out.println("Happiness:" + happinessn);
        }
    }

    public void eatfucapo() {
        if (energyn != MAX_ENERGY) {
            energyn++;
            System.out.println("Energy:" + energyn);
        }
    }

    public void onPause() {
        hunger = hungern;
        happiness = happinessn;
        thirst = thirstn;
        energy = energyn;
    }

    public void onResume() {
        hunger = hungern - lose;
        System.out.println("Hunger:" + hunger);
        System.out.println("Thirst:" + thirst);
        System.out.println("Happiness:" + happiness);
        System.out.println("Energy:" + energy);
    }

    public void save(SharedPreferences.Editor editorSave) {
        hunger = hungern;
        thirst = thirstn;
        happiness = happinessn;
        energy = energyn;
        editorSave.putLong("hunger", hungern);
        editorSave.putLong("thirst", thirstn);
        editorSave.putLong("happiness", happinessn);
        editorSave.putLong("energy", energyn);
        editorSave.commit();

    }

    public void load(SharedPreferences preferences) {
        hungern = preferences.getLong("hunger", hunger);
        thirstn = preferences.getLong("thirst", thirst);
        happinessn = preferences.getLong("happiness", happiness);
        energyn = preferences.getLong("energy", energy);

    }

    public class StatusEffect extends Potato {
        // Afgøre hvilket humør der skal vises. F.eks normal, sulten, trist, glad.

    }
}

