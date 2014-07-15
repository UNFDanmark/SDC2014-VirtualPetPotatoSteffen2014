package dk.lott.VPPS2015;

import android.content.SharedPreferences;

/**
 * Created by sdc on 7/15/14.
 */
public class Progressbar {
//Potato potato = new Potato();
public long timePause;
public long timeRes;
public long Progressbarlooses=(timeRes-timePause);

    public void onPause() {
        timePause=getTimeMilis();
    }

    public void onResume() {
        timeRes=getTimeDiffrence(timePause);
        System.out.println(Progressbarlooses);
    }

    private long getTimeMilis() {
        return System.currentTimeMillis();
    }

    private long getTimeDiffrence(Long lastTime) {
        return System.currentTimeMillis() - lastTime;
    }
}

