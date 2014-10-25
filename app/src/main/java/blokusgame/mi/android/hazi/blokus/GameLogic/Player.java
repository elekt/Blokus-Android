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

        // only in DUO
        Point startPoint = ((color==1)?new Point(5,5):new Point(10,10));
        corners = new ArrayList<Point>();
        corners.add(startPoint);
    }

    protected void setColor(int _color){ color = _color; }
    protected int getColor(){ return color; }

    public boolean isOutOfMoves(){ return false; }

    // TODO no me gusta
    // egyelore csak annyit csinal, hogy minden pontnak megnezi, hogy a sarkainal ures e, ha igen lementi
    protected void fillCorners(){
        corners = new ArrayList<Point>();
        Map map = Map.getInstance();
        for(int i=0; i<map.getLineSize(); ++i){
            for(int j=0; j<map.getLineSize(); ++j){
                if(map.getCell(i,j)==color){
                    Point pt = new Point(i,j);
                    Point temp = new Point(pt.x-1, pt.y-1);
                    if(map.getCell(temp)==0){
                        corners.add(temp);
                    }
                    temp = new Point(pt.x-1, pt.y+1);
                    if(map.getCell(temp)==0){
                        corners.add(temp);
                    }
                    temp = new Point(pt.x+1, pt.y-1);
                    if(map.getCell(temp)==0){
                        corners.add(temp);
                    }
                    temp = new Point(pt.x+1, pt.y+1);
                    if(map.getCell(temp)==0){
                        corners.add(temp);
                    }
                }
            }
        }
    }

    public boolean placeBlock(int blockIndex, Point coord){
        fillCorners();
        return true;
    }

    protected ArrayList<Block> blocks;
    protected ArrayList<Point> corners;
    protected int color;
}
