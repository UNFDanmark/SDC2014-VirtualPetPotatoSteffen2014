package dk.lott.VirtualPetPotatoSteffen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ProgressbarView extends View {
    private long currentValue = 250;
    private long maxValue = 1000;
    private long minValue = 0;
    private int color = Color.CYAN;

    Paint paint = new Paint();

    public ProgressbarView(Context context) {
        super(context);
    }

    public ProgressbarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressbarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        long width = canvas.getWidth();
        long height = canvas.getHeight();

        long span = getMaxValue() - getMinValue();

        double percent = (double) getCurrentValue() / (double) span;

        paint.setColor(getColor());

        canvas.drawRect(0, 0, (float) (width * percent), height, paint);
    }

    public void setValues(long currentValue, long minValue, long maxValue) {
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