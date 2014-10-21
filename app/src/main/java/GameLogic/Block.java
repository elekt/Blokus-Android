package GameLogic;

import java.util.ArrayList;

/**
 * Created by elekt on 2014.10.21..
 */
public class Block {
    public Block(){};
    public Block(ArrayList<Point> _points, int _color){}

    public void turn(int degrees){}
    public void mirror(int sides){}

    public Point getPoint(int idx){ return points.get(idx); }
    public int getSize(){ return points.size(); }
    public int getColor() {return color;}

    public void draw(){}

    private ArrayList<Point> points = new ArrayList<Point>();
    private int color;
}
