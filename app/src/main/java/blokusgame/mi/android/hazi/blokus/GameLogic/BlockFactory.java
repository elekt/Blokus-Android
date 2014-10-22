package blokusgame.mi.android.hazi.blokus.GameLogic;

import java.util.ArrayList;

/**
 * Created by elekt on 2014.10.21..
 */
public class BlockFactory {
    public static ArrayList<Block> createAllBlocks(int color){
        ArrayList<Block> blocks = new ArrayList<Block>();
        blocks.add(create1O(color));
        blocks.add(create2I(color));
        blocks.add(create3I(color));
        blocks.add(create3L(color));
        blocks.add(create4I(color));
        blocks.add(create4T(color));
        blocks.add(create4Z(color));
        blocks.add(create4O(color));
        blocks.add(create5I(color));
        blocks.add(create5L(color));
        blocks.add(create5U(color));
        blocks.add(create5Z(color));
        blocks.add(create5T(color));
        blocks.add(create5X(color));
        blocks.add(create5W(color));
        blocks.add(create5V(color));
        blocks.add(create5F(color));
        blocks.add(create5P(color));
        blocks.add(create5Y(color));
        blocks.add(create5N(color));
        return blocks;
    }

    ///naming according to http://www.boardgamegeek.com/image/112331/blokus
    private static Block create1O(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0,0));
        return new Block(points, color);
    }
    private static Block create2I(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        return new Block(points, color);
    }
    private static Block create3I(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        return new Block(points, color);
    }
    private static Block create3L(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(1, 1));
        return new Block(points, color);
    }
    private static Block create4I(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(3, 0));
        return new Block(points, color);
    }
    private static Block create4T(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(0, 2));
        points.add(new Point(1, 1));
        return new Block(points, color);
    }
    private static Block create4Z(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(1, 1));
        points.add(new Point(1, 2));
        return new Block(points, color);
    }
    private static Block create4O(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(1, 0));
        points.add(new Point(1, 1));
        return new Block(points, color);
    }
    private static Block create5I(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(3, 0));
        points.add(new Point(4, 0));
        return new Block(points, color);
    }
    private static Block create5L(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(3, 0));
        points.add(new Point(3, 1));
        return new Block(points, color);
    }
    private static Block create5U(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(1, 1));
        points.add(new Point(1, 2));
        points.add(new Point(0, 2));
        return new Block(points, color);
    }
    private static Block create5Z(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(1, 1));
        points.add(new Point(2, 1));
        points.add(new Point(2, 2));
        return new Block(points, color);
    }
    private static Block create5T(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(0, 2));
        points.add(new Point(1, 1));
        points.add(new Point(2, 1));
        return new Block(points, color);
    }
    private static Block create5X(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(0, 2));
        points.add(new Point(-1, 1));
        points.add(new Point(1, 1));
        return new Block(points, color);
    }
    private static Block create5W(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(1, 1));
        points.add(new Point(1, 2));
        points.add(new Point(2, 2));
        return new Block(points, color);
    }
    private static Block create5V(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(2, 1));
        points.add(new Point(2, 2));
        return new Block(points, color);
    }
    private static Block create5F(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(1, 1));
        points.add(new Point(1, 2));
        points.add(new Point(2, 1));
        return new Block(points, color);
    }
    private static Block create5P(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(1, 0));
        points.add(new Point(1, 1));
        points.add(new Point(2, 0));
        return new Block(points, color);
    }
    private static Block create5Y(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(0, 2));
        points.add(new Point(0, 3));
        points.add(new Point(1, 1));
        return new Block(points, color);
    }
    private static Block create5N(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(1, 1));
        points.add(new Point(1, 2));
        points.add(new Point(1, 3));
        return new Block(points, color);
    }
}
