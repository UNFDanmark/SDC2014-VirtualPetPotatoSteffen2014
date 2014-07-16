package dk.lott.VPPS2015;

import android.content.SharedPreferences;

/**
 * Created by sdc on 7/15/14.
 */
public class Time {

    public long timePause = getTimeMilis();
    public long timeRes;

    private long getTimeMilis() {
        return System.currentTimeMillis();
    }

    private long getTimeDiffrence(Long lastTime) {
        return System.currentTimeMillis() - lastTime;
    }

    public void onPause() {
        timePause = getTimeMilis();
    }

    public void onResume() {
        timeRes = ((getTimeDiffrence(timePause) / 1000) / 10);
        System.out.println("Progressbarloses:" + timeRes);
    }
}

