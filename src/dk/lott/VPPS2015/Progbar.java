package dk.lott.VPPS2015;

import android.content.Context;
import android.view.View;
import android.content.SharedPreferences;

/**
 * Created by sdc on 7/15/14.
 */

public class Progbar extends View {
    public Progbar(Context context) {
        super(context);
    }

    Potato potato = new Potato();
    Time time = new Time();
    int progbarLength;

}
