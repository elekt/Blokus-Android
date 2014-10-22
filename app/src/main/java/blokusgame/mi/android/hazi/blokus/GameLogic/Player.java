package blokusgame.mi.android.hazi.blokus.GameLogic;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by elekt on 2014.10.21..
 */
public abstract class Player {
    public Player(int _color){
        if(_color<=0) Log.e("ERROR: ", "Bad starting type.");//1,2,3... ok
        color=_color;
        blocks = BlockFactory.createAllBlocks(color);
    }

    protected void setColor(int _color){ color = _color; }
    protected int getColor(){ return color; }

    // TODO
    //protected ArrayList<Point> getPoints();
    public boolean isOutOfMoves(){ return false; }

    public abstract boolean placeBlock(int blockIndex, Point coord);

    protected ArrayList<Block> blocks;
    protected int color;
}
