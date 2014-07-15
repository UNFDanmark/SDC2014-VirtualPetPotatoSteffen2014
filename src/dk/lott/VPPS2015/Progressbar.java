package dk.lott.VPPS2015;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by sdc on 7/15/14.
 */
public class Progressbar extends View {
Potato potato = new Potato();
public long timePause;
public long timeRes;

    public Progressbar(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    public void onPause() {
        timePause=getTimeMilis();
    }

    public void onResume() {
        timeRes=getTimeDiffrence(timePause);


    }

    private long getTimeMilis() {
        return System.currentTimeMillis();
    }

    private long getTimeDiffrence(Long lastTime) {
        return System.currentTimeMillis() - lastTime;
    }
}
