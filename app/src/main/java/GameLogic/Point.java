package GameLogic;

/**
 * Created by elekt on 2014.10.21..
 */
public class Point {
    public Point(int _x, int _y){
        x=_x;
        y=_y;
    }
    void change(){
        int temp = x;
        x = y;
        y = temp;
    }
    public int x;
    public int y;
}
