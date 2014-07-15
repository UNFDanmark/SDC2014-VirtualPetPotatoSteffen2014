package dk.lott.VPPS2015;

import android.content.SharedPreferences;

/**
 * Created by sdc on 7/15/14.
 */
public class Potato {

    final static int MIN_HUNGER = 0;
    int hunger = 5;
    final static int MAX_HUNGER = 10;

    final static int MIN_HAPPINESS = 0;
    int happiness = 5;
    final static int MAX_HAPPINESS = 10;

    final static int MIN_THIRST = 0;
    int thirst = 5;
    final static int MAX_THIRST = 10;

    final static int MIN_ENERGY = 0;
    int energy = 5;
    final static int MAX_ENERGY = 10;

    boolean death = false;

    public void eat() {
        if (hunger != MAX_HUNGER) {
            hunger++;
        }
    }

    public void drink() {
        if (thirst != MAX_THIRST) {
            thirst++;
        }
    }

    public void play() {
        if (happiness != MAX_HAPPINESS) {
            happiness++;
        }
    }

    public void eatfucapo() {
        if (energy != MAX_ENERGY) {
            energy++;
        }
    }

    public void save(SharedPreferences.Editor editorSave) {
        editorSave.putInt("hunger", hunger);
        editorSave.putInt("thirst", thirst);
        editorSave.putInt("happiness", happiness);
        editorSave.putInt("energy", energy);
        editorSave.commit();

    }

    public void load(SharedPreferences preferences) {
        hunger = preferences.getInt("hunger", 5);
        thirst = preferences.getInt("thirst", 5);
        happiness = preferences.getInt("happiness", 5);
        energy = preferences.getInt("energy", 5);

    }

    public class StatusEffect extends Potato {
        // Afgøre hvilket humør der skal vises. F.eks normal, sulten, trist, glad.

    }
}

