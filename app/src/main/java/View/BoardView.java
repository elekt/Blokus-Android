package View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import blokusgame.mi.android.hazi.blokus.GameLogic.Map;
import blokusgame.mi.android.hazi.blokus.GameLogic.PlayerConstants;
import blokusgame.mi.android.hazi.blokus.GameLogic.Point;
import blokusgame.mi.android.hazi.blokus.R;

/**
 * Created by elekt on 2014.10.04..
 */
public class BoardView extends View {
    ArrayList<BoardTouchListener> listeners = new ArrayList<BoardTouchListener>();

    private Paint paintBg;
    private Paint paintLine;
    private Paint paintRect;
    private ArrayList<Point> corners;

    public BoardView(Context context, AttributeSet attrs){
        super(context, attrs);

        paintBg = new Paint();
        paintBg.setColor(Color.BLACK);
        paintBg.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(2);

        paintRect = new Paint();
        paintRect.setColor(Color.CYAN);
        paintRect.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);

        drawGameArea(canvas);

        drawPlayers(canvas);

        drawCorners(canvas);
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
        Map map = Map.getInstance();

        for(int i=0; i<map.getLineSize(); ++i){
            for(int j=0; j<map.getLineSize(); ++j){
                int cell = map.getCell(i,j);
                if(cell>0) {
                    paintRect.setColor(getColor(cell));
                    int x = i * (getWidth() / map.getLineSize());
                    int y = j * (getHeight() / map.getLineSize());
                    Rect rect = new Rect(x, y, x + (getWidth() / map.getLineSize()), y + (getHeight() / map.getLineSize()));
                    canvas.drawRect(rect, paintRect);
                }
            }
        }
    }

    private void drawCorners(Canvas canvas){
        paintRect.setColor(Color.GRAY);
        paintRect.setAlpha(160);
        Map map = Map.getInstance();
        for(int i=0; i<corners.size(); ++i){
            int x = corners.get(i).x * (getWidth() / map.getLineSize());
            int y = corners.get(i).y * (getHeight() / map.getLineSize());
            Rect rect = new Rect(x, y, x + (getWidth() / map.getLineSize()), y + (getHeight() / map.getLineSize()));
            canvas.drawRect(rect, paintRect);
        }
    }

    private int getColor(int cell) {
        switch (cell){
            case 1:
                return PlayerConstants.PLAYER_ONE;
            case 2:
                return PlayerConstants.PLAYER_TWO;
            case 3:
                return PlayerConstants.PLAYER_THREE;
            case 4:
                return PlayerConstants.PLAYER_FOUR;
            default:
                return Color.CYAN;
        }
    }


    public void setCorners(ArrayList<Point> _corners){
        corners = _corners;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int tX = ((int) event.getX()) / (getWidth() / Map.getInstance().getLineSize());
            int tY = ((int) event.getY()) / (getHeight() / Map.getInstance().getLineSize());

            for (BoardTouchListener listener:listeners){
                listener.onBoardTouched(tX,tY);
            }
        }

        return super.onTouchEvent(event);
    }

    public void setWasTouchedListener(BoardTouchListener listener){
        listeners.add(listener);
    }

}

