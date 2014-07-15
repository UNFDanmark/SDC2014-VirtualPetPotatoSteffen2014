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
    long starthunger = 5;
    long hungern;
    final static int MAX_HUNGER = 10;

    final static int MIN_HAPPINESS = 0;
    long starthappiness = 5;
    long happinessn;
    final static int MAX_HAPPINESS = 10;

    final static int MIN_THIRST = 0;
    long startthirst = 5;
    long thirstn;
    final static int MAX_THIRST = 10;

    final static int MIN_ENERGY = 0;
    long startenergy = 5;
    long energyn;
    final static int MAX_ENERGY = 10;

    public long lose = time.timeRes;

    boolean death = false;

    public void resetPotatoStats() {
        hungern = 5;
        happinessn = 5;
        thirstn = 5;
        energyn = 5;
    }

    public void diePotato() {

    public void diePotato(Context context){
        if(hunger == MIN_HUNGER){
        resetPotatoStats();
            Toast.makeText(context,"Your Potato Steffen died of hunger! Shame on you!", Toast.LENGTH_LONG ).show();
        }
        else if(thirst == MIN_THIRST){
        resetPotatoStats();
            Toast.makeText(context, "You fool! Potato Steffen died of thirst!", Toast.LENGTH_LONG ).show();
        }
        else if(energy == MIN_ENERGY){
            resetPotatoStats();
            Toast.makeText(context,"You lily liver! Potato Steffen died of energy loss!", Toast.LENGTH_LONG ).show();
        }
        else if(happiness == MIN_HAPPINESS){
            resetPotatoStats();
            Toast.makeText(context,"Your Potato Steffen died of energy loss! You suck!", Toast.LENGTH_LONG ).show();
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

    public void onResume() {
        hungern = hungern - lose;
        happinessn = happinessn - lose;
        thirstn = thirstn - lose;
        energyn = energyn - lose;
        System.out.println("Hunger:" + hungern);
        System.out.println("Thirst:" + thirstn);
        System.out.println("Happiness:" + happinessn);
        System.out.println("Energy:" + energyn);

    }

    public void save(SharedPreferences.Editor editorSave) {
        editorSave.putLong("hunger", hungern);
        editorSave.putLong("thirst", thirstn);
        editorSave.putLong("happiness", happinessn);
        editorSave.putLong("energy", energyn);
        editorSave.commit();

    }

    public void load(SharedPreferences preferences) {
        hungern = preferences.getInt("hunger", 5);
        thirstn = preferences.getInt("thirst", 5);
        happinessn = preferences.getInt("happiness", 5);
        energyn = preferences.getInt("energy", 5);

    }

    public class StatusEffect extends Potato {
        // Afgøre hvilket humør der skal vises. F.eks normal, sulten, trist, glad.

    }
}

