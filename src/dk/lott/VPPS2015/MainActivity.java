package dk.lott.VPPS2015;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        public class Potato {
            int hunger=50;
            int happiness=50;
            int eat;

            /**
             * Hunger
             */
            if (hunger > 100) {
                hunger = 100;
            }
            else if (hunger <= 50) {

            }
            else if (hunger <= 0) {
                hunger = 0;
                //Die somehow
            }
            if (eat) {
                hunger+20;
            }
            /**
             * Happiness
             */

            if (happiness <=0) {

            }
            //Evt. Flere Ting
        }


    }


}
