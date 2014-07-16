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

public class ReverseProgressbarView extends View{
    private long currentValue = 250;
    private long maxValue = 1000;
    private long minValue = 0;
    private int color = Color.CYAN;

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
        super.onDraw(canvas);

        long width = canvas.getWidth();
        long height = canvas.getHeight();

        long span = getMinValue() - getMaxValue();

        double percent = (double)getCurrentValue() / (double)span;

        Paint paint = new Paint();
        paint.setColor(getColor());

        canvas.drawRect((float)(width*percent), 0, 0, height, paint);
    }

    public void setValues(long currentValue, long minValue, long maxValue)
    {
        this.currentValue = currentValue;
        this.minValue = minValue;
        this.maxValue = maxValue;

        invalidate();
    }

    public long getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(long maxValue) {
        this.maxValue = maxValue;

        invalidate();
    }

    public long getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(long currentValue) {
        this.currentValue = currentValue;

        invalidate();
    }

    public long getMinValue() {
        return minValue;
    }

    public void setMinValue(long minValue) {
        this.minValue = minValue;

        invalidate();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    /**  ^ ^
     *  ( 0_0 )
     *  ()   ()
     *   (| |)
     */
}