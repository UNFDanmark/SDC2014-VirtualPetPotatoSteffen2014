package dk.lott.VPPS2015;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by sdc on 7/15/14.
 */

public class ReverseProgressbarView extends ProgressbarView {

    Paint paint = new Paint();

    public ReverseProgressbarView(Context context) {
        super(context);
    }

    public ReverseProgressbarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReverseProgressbarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        long width = canvas.getWidth();
        long height = canvas.getHeight();

        long span = getMaxValue() - getMinValue();

        double percent = 1 - (double) getCurrentValue() / (double) span;


        paint.setColor(getColor());

        canvas.drawRect((float) (width * percent), 0, width, height, paint);
    }
}