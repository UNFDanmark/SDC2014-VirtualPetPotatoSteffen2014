package dk.lott.VPPS2015;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

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

    public void resetPotatoStats(){
        hunger = 5;
        happiness = 5;
        thirst = 5;
        energy = 5;
    }

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
        if (hunger != MAX_HUNGER) {
            hunger++;
            System.out.println("Hunger:" + hunger);
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

    public void hungerloose() {

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

