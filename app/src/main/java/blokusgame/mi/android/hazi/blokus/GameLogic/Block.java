package blokusgame.mi.android.hazi.blokus.GameLogic;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by elekt on 2014.10.21..
 */
public class Block {
    private ArrayList<Point> points = new ArrayList<Point>();
    private int color;
    private int imageId;
    private int id;

    public Point getPoint(int idx){ return points.get(idx); }
    public int getSize(){ return points.size(); }
    public int getColor() {return color;}
    public int getImageId(){ return imageId; }
    public int getId(){ return id; }
    public Point getDimensions(){
        int minX,minY;
        minX = minY = 10;
        int maxX,maxY;
        maxX = maxY = 0;
        for(Point i: points){
            minX = (i.x<minX)?i.x:minX;
            maxX = (i.x>maxX)?i.x:maxX;
            minY = (i.y<minY)?i.y:minY;
            maxY = (i.y>maxY)?i.y:maxY;
        }
        return new Point(maxX-minX+1,maxY-minY+1);
    }
    public Block(ArrayList<Point> _points, int _color, int _imageId, int _id){
        color = _color;
        points = _points;
        imageId = _imageId;
        id = _id;
    }
//    public Block(ArrayList<Point> _points){
//        points = _points;
//    }
    public Block(Block b){
        color = b.color;
        points = new ArrayList<Point>();
        for(Point i: b.points){
            points.add(new Point(i));
        }
//        points = new ArrayList<Point>(b.points);
        imageId = b.imageId;
        id = b.id;
    }

    @Override
    public boolean equals(Object b){//TODO check
        return points.containsAll(((Block)b).points) && ((Block)b).points.containsAll(points);
//        return this.points.equals(((Block)b).points);

    }
    public Block turn(int degrees){
        Block ret = new Block(this);
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
                return this;
        }

        for (int i=0; i<ret.points.size(); ++i) {
            if(bSwitch){
                ret.points.get(i).change();
            }
            ret.points.get(i).x *= dVec.x;
            ret.points.get(i).y *= dVec.y;
        }
        return ret;
    }
    ///sides==0: no action
    ///sides==1: x tengely körül, sides==1: y tengely körül
    ///tengelyek a 0,0 pontnál találkoznak
    public Block mirror(int sides){
        Block ret = new Block(this);
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
                return this;
        }

        for (int i=0; i<ret.points.size(); ++i) {
            ret.points.get(i).x *= dVec.x;
            ret.points.get(i).y *= dVec.y;
        }
        return ret;
    }


    // TODO
    public ArrayList<Block> getRotations() {
        ArrayList<Block> rotatedBlocks = new ArrayList<Block>();
        Block thisBlock = new Block(this);
        rotatedBlocks.add(new Block(thisBlock.turn(90)));
        rotatedBlocks.add(new Block(thisBlock.turn(180)));
        rotatedBlocks.add(new Block(thisBlock.turn(270)));
        rotatedBlocks.add(new Block(thisBlock.mirror(1)));
        rotatedBlocks.add(new Block(thisBlock.mirror(2)));
        rotatedBlocks.add(new Block(thisBlock.turn(90).mirror(1)));
        rotatedBlocks.add(new Block(thisBlock.turn(180).mirror(1)));
        rotatedBlocks.add(new Block(thisBlock.turn(270).mirror(1)));
        rotatedBlocks.add(new Block(thisBlock.turn(90).mirror(2)));
        rotatedBlocks.add(new Block(thisBlock.turn(180).mirror(2)));
        rotatedBlocks.add(new Block(thisBlock.turn(270).mirror(2)));
        ArrayList<Block> rotatedBlocksSlim = new ArrayList<Block>();
        for(Block i: rotatedBlocks){
            if(!rotatedBlocksSlim.contains(i)){
                rotatedBlocksSlim.add(i);
            }
        }

//        HashSet<Block> hs = new HashSet<Block>();
//        hs.addAll(rotatedBlocks);
//        rotatedBlocks.clear();
//        rotatedBlocks.addAll(hs);


        return rotatedBlocksSlim;
    }


}
