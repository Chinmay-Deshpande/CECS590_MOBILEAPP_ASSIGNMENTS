package com.example.chinmaydeshpande.assignment_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Chinmay Deshpande on 3/14/2017.
 */

public class Custom_view extends View {

    public Paint paint = new Paint();
    public Path path = new Path();


    public Custom_view(Context context) {
        super(context);
       // initialize(null,0);

    }

    public Custom_view(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(attrs,0);
    }

    public Custom_view(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //initialize(attrs,defStyleAttr);
    }




    public void initialize(AttributeSet attributeSet, int defStyleAttr)
    {

        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x =event.getX();
        float y=event.getY();

        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN: path.moveTo(x,y);
                    break;
            case MotionEvent.ACTION_MOVE: path.lineTo(x,y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;


    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        canvas.drawPath(path,paint);
    }

    public void clearCanvas() {

        path.reset();

        invalidate();

    }


}
