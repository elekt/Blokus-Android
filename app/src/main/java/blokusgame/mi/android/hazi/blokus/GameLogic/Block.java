package blokusgame.mi.android.hazi.blokus.GameLogic;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by elekt on 2014.10.21..
 */
public class Block {
    public Block(ArrayList<Point> _points, int _color, int _imageId, int _id){
        color = _color;
        points = _points;
        imageId = _imageId;
        id = _id;
    }

    public void turn(int degrees){
        // ha jol emlekszem (1,0) volt, nem veletlen, atirtad?
        Point dVec = new Point(1,1);//inkabb 1,1 lenne a default, not sure
        boolean bSwitch = false;
        switch(degrees){
            case 0:
                break;
            case 90:
                bSwitch = true;
                dVec = new Point(-1, 1);
                break;
            case 180:
                bSwitch = false;
                dVec = new Point(-1,-1);
                break;
            case 270:
                bSwitch = true;
                dVec = new Point(-1, 1);
                break;
            default:
                Log.e("WRONG TURN", "Wrong turning degree! Block::turn");
                return;
        }

        for (int i=0; i<points.size(); ++i) {
            if(bSwitch){
                points.get(i).change();
            }
            points.get(i).x *= dVec.x;
            points.get(i).y *= dVec.y;
        }
    }
    ///sides==0: no action
    ///sides==1: x tengely körül, sides==1: y tengely körül
    ///tengelyek a 0,0 pontnál találkoznak
    public void mirror(int sides){
        Point dVec = new Point(1,1);
        switch(sides){
            case 0:
                break;
            case 1://x tengely
                dVec = new Point(-1, 1);
                break;
            case 2://y tengely
                dVec = new Point(1,-1);
                break;
            default:
                Log.e("MIRROR INPUT","Wrong mirroring input! Block::mirror");
                return;
        }

        for (int i=0; i<points.size(); ++i) {
            points.get(i).x *= dVec.x;
            points.get(i).y *= dVec.y;
        }
    }

    public Point getPoint(int idx){ return points.get(idx); }
    public int getSize(){ return points.size(); }
    public int getColor() {return color;}
    public int getImageId(){ return imageId; }
    public int getId(){ return id; }
    // TODO
    public ArrayList<Block> getRotations() {
        return null;
    }

    private ArrayList<Point> points = new ArrayList<Point>();
    private int color;
    private int imageId;
    private int id;
}
