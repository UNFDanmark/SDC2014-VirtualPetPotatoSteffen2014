package dk.lott.VPPS2015;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Set;

public class MainActivity extends Activity {

Potato potato = new Potato();

        /**
         * Called when the activity is first created.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);

/**
 * Toys
 */
            Button toys = (Button) findViewById(R.id.btoys);
            toys.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
/**
 * Food
 */
            Button food = (Button) findViewById(R.id.bfood);
            food.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

/**
 * Fucapo
 */
            Button fucapo = (Button) findViewById(R.id.bfucapo);
            fucapo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            /**
             * Drinks
             */
            Button drinks = (Button) findViewById(R.id.bdrinks);
            drinks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (potato.thirst == 5) ;


                }
            });
        }

}

