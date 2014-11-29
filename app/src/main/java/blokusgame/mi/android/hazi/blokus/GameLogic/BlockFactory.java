package blokusgame.mi.android.hazi.blokus.GameLogic;

import java.util.ArrayList;

import blokusgame.mi.android.hazi.blokus.R;

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
        blocks.add(create4L(color));
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
        int imageId = R.drawable.o_1;
        return new Block(points, color, imageId, 0);
    }
    private static Block create2I(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        int imageId = R.drawable.i_2;
        return new Block(points, color, imageId, 1);
    }
    private static Block create3I(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        int imageId = R.drawable.i_3;
        return new Block(points, color, imageId, 2);
    }
    private static Block create3L(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(1, 1));
        int imageId = R.drawable.l_3;
        return new Block(points, color, imageId, 3);
    }
    private static Block create4I(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(3, 0));
        int imageId = R.drawable.i_4;
        return new Block(points, color, imageId, 4);
    }
    private static Block create4T(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(0, 2));
        points.add(new Point(1, 1));
        int imageId = R.drawable.t_4;
        return new Block(points, color, imageId, 5);
    }
    private static Block create4L(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(2, 1));
        int imageId = R.drawable.l_4;
        return new Block(points, color, imageId, 6);
    }
    private static Block create4Z(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(1, 1));
        points.add(new Point(1, 2));
        int imageId = R.drawable.z_4;
        return new Block(points, color, imageId, 7);
    }
    private static Block create4O(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(1, 0));
        points.add(new Point(1, 1));
        int imageId = R.drawable.o_4;
        return new Block(points, color, imageId, 8);
    }
    private static Block create5I(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(3, 0));
        points.add(new Point(4, 0));
        int imageId = R.drawable.i_5;
        return new Block(points, color, imageId, 9);
    }
    private static Block create5L(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(3, 0));
        points.add(new Point(3, 1));
        int imageId = R.drawable.l_5;
        return new Block(points, color, imageId, 10);
    }
    private static Block create5U(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(1, 1));
        points.add(new Point(1, 2));
        points.add(new Point(0, 2));
        int imageId = R.drawable.u_5;
        return new Block(points, color, imageId, 11);
    }
    private static Block create5Z(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(1, 1));
        points.add(new Point(2, 1));
        points.add(new Point(2, 2));
        int imageId = R.drawable.z_5;
        return new Block(points, color, imageId, 12);
    }
    private static Block create5T(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(0, 2));
        points.add(new Point(1, 1));
        points.add(new Point(2, 1));
        int imageId = R.drawable.t_5;
        return new Block(points, color, imageId, 13);
    }
    private static Block create5X(int color){
        ArrayList<Point> points = new ArrayList<Point>();
//        points.add(new Point(0, 0));
//        points.add(new Point(0, 1));
//        points.add(new Point(0, 2));
//        points.add(new Point(-1, 1));
//        points.add(new Point(1, 1));
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(0, -1));
        points.add(new Point(1, 0));
        points.add(new Point(-1, 0));
        int imageId = R.drawable.x_5;
        return new Block(points, color, imageId, 14);
    }
    private static Block create5W(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(1, 1));
        points.add(new Point(1, 2));
        points.add(new Point(2, 2));
        int imageId = R.drawable.w_5;
        return new Block(points, color, imageId, 15);
    }
    private static Block create5F(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(1, 1));
        points.add(new Point(1, 2));
        points.add(new Point(2, 1));
        int imageId = R.drawable.f_5;
        return new Block(points, color, imageId, 16);
    }
    private static Block create5V(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(2, 1));
        points.add(new Point(2, 2));
        int imageId = R.drawable.v_5;
        return new Block(points, color, imageId, 17);
    }
    private static Block create5P(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(1, 0));
        points.add(new Point(1, 1));
        points.add(new Point(2, 0));
        int imageId = R.drawable.p_5;
        return new Block(points, color, imageId, 18);
    }
    private static Block create5Y(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(0, 2));
        points.add(new Point(0, 3));
        points.add(new Point(1, 1));
        int imageId = R.drawable.y_5;
        return new Block(points, color, imageId, 19);
    }
    private static Block create5N(int color){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(1, 1));
        points.add(new Point(1, 2));
        points.add(new Point(1, 3));
        int imageId = R.drawable.n_5;
        return new Block(points, color, imageId, 20);
    }
}
