package com.elekt.blokus.View;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.ArrayList;

import com.elekt.blokus.GameLogic.Map;

public class BoardGestureListener extends GestureDetector.SimpleOnGestureListener {
    BoardView boardView;
    ArrayList<BoardTouchListener> listeners;
    public BoardGestureListener(BoardView _boardView, ArrayList<BoardTouchListener> _listeners) {
        boardView = _boardView;
        listeners = _listeners;
    }

    @Override
    public boolean onDown(MotionEvent event) {
        //Log.e("PRESS HAPPENED", "PRESS");
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int tX = (int) (event.getX() / (boardView.getWidth() / Map.getInstance().getLineSize()));
            int tY = (int) (event.getY() / (boardView.getHeight() / Map.getInstance().getLineSize()));

            for (BoardTouchListener listener:listeners){
                listener.onBoardTouched(tX,tY);
            }
        }
        return true;
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        Log.e("FLING HAPPENED", "FLING");
        return true;
    }

}
