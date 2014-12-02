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
    public int getColor(){ return color; }

    public boolean isOutOfMoves(){ return false; }

    // searches for all possible corners, and if any block can fill there, puts
    protected void fillCorners(){
        if(steps==0)
            return;
        corners.clear();
        Map map = Map.getInstance();
        for(int i=0; i<map.getLineSize(); ++i){
            for(int j=0; j<map.getLineSize(); ++j){
                if(map.getCell(i,j)==color){
                    Point pt = new Point(i,j);

                    // the additional neighbour color check frees a lot of computing time, and solves a bug. the bug is concerning me
                    Point temp = new Point(pt.x-1, pt.y-1);
                    if(map.getCell(temp)==0 && map.getCell(i-1, j)!=color && map.getCell(i, j-1)!=color && checkCorner(temp)){
                        corners.add(temp);
                    }
                    temp = new Point(pt.x-1, pt.y+1);
                    if(map.getCell(temp)==0 && map.getCell(i-1, j)!=color && map.getCell(i, j+1)!=color && checkCorner(temp)){
                        corners.add(temp);
                    }
                    temp = new Point(pt.x+1, pt.y-1);
                    if(map.getCell(temp)==0 && map.getCell(i+1, j)!=color && map.getCell(i, j-1)!=color && checkCorner(temp)){
                        corners.add(temp);
                    }
                    temp = new Point(pt.x+1, pt.y+1);
                    if(map.getCell(temp)==0 && map.getCell(i+1, j)!=color && map.getCell(i, j+1)!=color && checkCorner(temp)){
                        corners.add(temp);
                    }

                }
            }
        }
    }

    // we have to fit all blocks, all possible ways, until we find any that fits there
    protected boolean checkCorner(Point corner) {
        Map map = Map.getInstance();

        // for optimalization, if the player still has the 1 piece block, check only for that
        if(getBlock(0)!=null) {
            if (map.isPlaceable(getBlock(0), corner)) {
                return true;
            } else {
                return false;
            }
        }

        for(Block block : blocks){
            ArrayList<Block> rotations = block.getRotations();
            for (Block rBlock : rotations) {
                for(int i=0; i<rBlock.getSize(); ++i){
                    Point pt = new Point(corner.x - block.getPoint(i).x, corner.y - block.getPoint(i).y);
                    if (rBlock.isContaining(pt, corner) && map.isPlaceable(rBlock, pt)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean placeBlock(Block block, Point coord){
        if(block == null){
            Log.e("PLAYER", "BLOCK ALREADY REMOVED");
        } else {
            // lerakja a blockot
            Map map = Map.getInstance();
            for (int i = 0; i < block.getSize(); ++i) {
                Point temp = new Point(coord.x + block.getPoint(i).x, coord.y + block.getPoint(i).y);
                map.setCell(block.getColor(), temp);
            }
            blocks.remove(getBlock(block.getId()));
            Map.getInstance().incStep();
            fillCorners();
        }

        return true;
    }

    // each block have unique id, you search them regards to that
    public Block getBlock(int blockId) {
        for(int i = 0; i<blocks.size(); ++i){
            if(blocks.get(i).getId()==blockId){
                return blocks.get(i);
            }
        }
        return null;
    }
    public ArrayList<Point> getCorners() {
        return corners;
    }

    protected ArrayList<Block> blocks;
    protected ArrayList<Point> corners;
    protected int color;
    protected int steps = 0;
}
