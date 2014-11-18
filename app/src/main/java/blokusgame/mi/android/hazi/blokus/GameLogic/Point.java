package blokusgame.mi.android.hazi.blokus.GameLogic;

/**
 * Created by elekt on 2014.10.21..
 */
public class Point {
    public int x;
    public int y;
    public Point(Point p){
        x = p.x;
        y = p.y;
    }
    public Point(int _x, int _y){
        x=_x;
        y=_y;
    }
    void change(){
        int temp = x;
        x = y;
        y = temp;
    }

    @Override
    public boolean equals(Object _other){
        return (x == ((Point)_other).x && y == ((Point)_other).y);
    }

}
