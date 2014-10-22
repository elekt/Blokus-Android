package View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import GameLogic.Map;

/**
 * Created by elekt on 2014.10.04..
 */
public class BoardView extends View {
    Paint paintBg;
    Paint paintLine;
    Paint paintChar;

    public BoardView(Context context, AttributeSet attrs){
        super(context, attrs);

        paintBg = new Paint();
        paintBg.setColor(Color.BLACK);
        paintBg.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(2);

        paintChar = new Paint();
        paintChar.setColor(Color.CYAN);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(1);
        paintChar.setTextSize(40);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);

        drawGameArea(canvas);

        drawPlayers(canvas);
    }

    private void drawGameArea(Canvas canvas) {
        Map map = Map.getInstance();
        // border
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);

        for(int i=0; i<map.getLineSize(); ++i) {        // height-1 horizontal lines
            canvas.drawLine(0, i*getHeight() / map.getLineSize(), getWidth(), i*getHeight() / map.getLineSize(),
                    paintLine);

        }

        for(int i=0; i<map.getLineSize(); ++i) {        // height-1 horizontal lines
            canvas.drawLine(i*getWidth() / map.getLineSize(), 0, i*getWidth() / map.getLineSize(), getHeight(),
                    paintLine);
        }
    }

    private void drawPlayers(Canvas canvas) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            invalidate();
        }

        return super.onTouchEvent(event);
    }
}

