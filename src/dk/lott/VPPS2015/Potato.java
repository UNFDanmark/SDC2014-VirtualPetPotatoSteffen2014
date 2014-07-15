package dk.lott.VPPS2015;

import java.util.Set;

/**
 * Created by sdc on 7/15/14.
 */
public class Potato {

    int minHunger = 0;
    int hunger = 5;
    int maxHunger = 10;

    int minHappiness = 0;
    int happiness = 5;
    int maxHappiness = 10;

    int minThirst = 0;
    int thirst = 5;
    int maxThirst = 10;

    int minEnergi = 0;
    int energi = 5;
    int maxEnergi = 10;

    boolean death = false;

    public void eat() {
        if(hunger != maxHunger){
            hunger++;
        }
    }

    public void drink() {
        if(thirst != maxThirst){
            thirst++;
        }
    }

    public void play() {
        if(happiness != maxHappiness){
            happiness++;
        }
    }

    public void eatfucapo() {
        if(energi != maxEnergi){
            energi++;
        }
    }
}