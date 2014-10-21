package GameLogic;

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


    // TODO delete this
    public void draw(){}

    public void setCell(int set, int idx){
        if(set < 0 ) Log.e("ERROR: ", "Expected int over 0");
        if(cells.get(idx)!=0) Log.e("ERROR: ","Already set");
        ++steps;
        cells.add(idx, set);
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
    public boolean isPlaceable(Point pt, Block block){ return true; }
    /// visszaadja ki nyert ill 0-t ha meg senki
    public int gameEnd(){ return 0; }
    /// ha vege a jateknak akkor sztorno minden
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
