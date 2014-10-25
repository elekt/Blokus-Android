package blokusgame.mi.android.hazi.blokus.GameLogic;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by elekt on 2014.10.21..
 */
public class Map {
    public static Map getInstance(){
        if(mapInstance==null){
            mapInstance = new Map();
        }

        return mapInstance;
    }

    public Map(){
        steps = 0;
        mapInstance=null;
        lineSize = 14;
        mapSize = lineSize * lineSize;
        cells = new ArrayList<Integer>(mapSize);
        for(int i = 0; i<mapSize; ++i) cells.add(0);
    }

    public void setCell(int set, int idx) {
        if (set < 0) Log.e("ERROR: ", "Expected int over 0");
        if (cells.get(idx) != 0) Log.e("ERROR: ", "Already set");
        cells.set(idx, set);
    }
    public void setCell(int set, int x, int y){
        setCell(set, x*lineSize + y);
    }
    public void setCell(int set, Point pt){
        setCell(set, pt.x*lineSize + pt.y);
    }

    public int getCell(int idx){
        if (idx<0 || idx>=cells.size()) return -1;
        return cells.get(idx);
    }
    public int getCell(int x, int y){
        return getCell(x*lineSize + y);
    }
    public int getCell(Point pt){
        return getCell(pt.x*lineSize + pt.y);
    }
    public int getSteps(){ return steps; }
    // lehetne private?
    public void incStep(){ ++steps; }

    public int getLineSize(){ return lineSize; }

    // TODO
    public boolean isPlaceable(Block block, ArrayList<Point> corners, Point pt){


        ///TODO a vizsgalatokat ossze is lehetne vonni, ha lassu lenne
        ///az elhelyezett block kilóg e a palyarol
        for(int i = 0; i<block.getSize(); ++i){
            Point temp = new Point(pt.x +  block.getPoint(i).x, pt.y + block.getPoint(i).y);
            if(getCell(temp)==-1){
                return false;
            }
        }
        /// megvizsgalja, hogy van e kocka utban
        for(int i = 0; i<block.getSize(); ++i){
            Point temp = new Point(pt.x +  block.getPoint(i).x, pt.y + block.getPoint(i).y);
            if(getCell(temp)!=0){
                return false;
            }
        }
        ///megvizsgalja, hogy van e sajat szinu lapjaval talalkozva
        for(int i = 0; i<block.getSize(); ++i){//minden elem a blockban
            Point temp0 = new Point(pt.x +  block.getPoint(i).x - 1, pt.y + block.getPoint(i).y + 0);
            Point temp1 = new Point(pt.x +  block.getPoint(i).x + 1, pt.y + block.getPoint(i).y + 0);
            Point temp2 = new Point(pt.x +  block.getPoint(i).x + 0, pt.y + block.getPoint(i).y + 1);
            Point temp3 = new Point(pt.x +  block.getPoint(i).x + 0, pt.y + block.getPoint(i).y - 1);
            if (getCell(temp0)==block.getColor() ||
                    getCell(temp1)==block.getColor() ||
                    getCell(temp2)==block.getColor() ||
                    getCell(temp3)==block.getColor()
                    )
            {
                return false;
            }
        }
        // TODO
        ///megvizsgalja, hogy van e saját sarokcsatlakozasa, (nincs e a levegoben)
        for(int i = 0; i<block.getSize(); ++i){//minden elem a blockban
            if(corners.contains(block.getPoint(i))){
                return true;
            }
        }
        return true;
    }
    public int gameEnd(){ return 0; }
    public void reset(){
        steps = 0;
        for(int i=0; i<mapSize; ++i) cells.set(i, 0);
    }


    private static Map mapInstance;
    private ArrayList<Integer> cells;
    private int steps;
    private int mapSize;
    private int lineSize;
}
