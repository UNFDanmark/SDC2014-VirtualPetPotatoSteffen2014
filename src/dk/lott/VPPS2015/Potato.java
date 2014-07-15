package dk.lott.VPPS2015;

import android.content.SharedPreferences;

/**
 * Created by sdc on 7/15/14.
 */
public class Potato {

    Time time = new Time();

    final static int MIN_HUNGER = 0;
    long hunger = 5;
    final static int MAX_HUNGER = 10;

    final static int MIN_HAPPINESS = 0;
    long happiness = 5;
    final static int MAX_HAPPINESS = 10;

    final static int MIN_THIRST = 0;
    long thirst = 5;
    final static int MAX_THIRST = 10;

    final static int MIN_ENERGY = 0;
    long energy = 5;
    final static int MAX_ENERGY = 10;

    public long lose = time.timeRes;

    boolean death = false;

    public void resetPotatoStats(){
        hunger = 5;
        happiness = 5;
        thirst = 5;
        energy = 5;
    }

    public void diePotato(){

    }

    public void eat() {
        if (hunger != MAX_HUNGER) {
            hunger++;
            System.out.println("Hunger:"+hunger);
        }
    }

    public void drink() {
        if (thirst != MAX_THIRST) {
            thirst++;
            System.out.println("thirst:"+thirst);
        }
    }

    public void play() {
        if (happiness != MAX_HAPPINESS) {
            happiness++;
            System.out.println("Happiness:"+happiness);
        }
    }

    public void eatfucapo() {
        if (energy != MAX_ENERGY) {
            energy++;
            System.out.println("Energy:"+energy);
        }
    }

    public void onResume() {
        hunger = hunger - lose;
        happiness = happiness - lose;
        thirst = thirst - lose;
        energy = energy - lose;
        System.out.println("Hunger:"+hunger);
        System.out.println("thirst:"+thirst);
        System.out.println("Happiness:"+happiness);
        System.out.println("Energy:"+energy);

    }

    public void save(SharedPreferences.Editor editorSave) {
        editorSave.putLong("hunger", hunger);
        editorSave.putLong("thirst", thirst);
        editorSave.putLong("happiness", happiness);
        editorSave.putLong("energy", energy);
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

