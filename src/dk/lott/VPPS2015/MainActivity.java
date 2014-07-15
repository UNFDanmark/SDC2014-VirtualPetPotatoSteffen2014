package dk.lott.VPPS2015;

import android.app.Activity;
import android.os.Bundle;

import java.util.Set;

public class MainActivity extends Activity {
static class Potato {
    int hunger=50;
    int happiness=50;
    int thirst=50;
    int energi=50;
    boolean death=false;
}
    static class StatusEffect extends Potato {
        boolean sad=false;
            if (happiness <= 30) {
        sad=true;
        normal=false;
            thirsty = false;

    }
        boolean happy=false;
            if (happiness >= 90) {
        happy=true;
    }
    boolean sick=false;
    if (hunger >=30 && thirst >=30) {
        sick=true;
    }
    boolean thirsty=false;
    if (thirst >=30) {
        thirsty=true;
    }
    boolean hungry=false;
    if (hunger>=30) {
        hungry=true;
    }
    boolean normal=true;
    if ()
    boolean tired=false;
    if (energi>=30) {
        tired=true;
    }
    boolean Dead=false;
    if (hunger>=0 && thirst>=0 && energi>=0){
        death=true;
    }


}
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);




}
