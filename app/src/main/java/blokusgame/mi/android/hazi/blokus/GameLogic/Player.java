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
    protected int getColor(){ return color; }

    public boolean isOutOfMoves(){ return false; }

    // TODO no me gusta
    // egyelore csak annyit csinal, hogy minden pontnak megnezi, hogy a sarkainal ures e, ha igen lementi
    protected void fillCorners(){
        corners.clear();
        Map map = Map.getInstance();
        for(int i=0; i<map.getLineSize(); ++i){
            for(int j=0; j<map.getLineSize(); ++j){
                if(map.getCell(i,j)==color){
                    Point pt = new Point(i,j);

                    Point temp = new Point(pt.x-1, pt.y-1);
                    if(map.getCell(temp)==0 && checkCorner(temp)){
                        corners.add(temp);
                    }
                    temp = new Point(pt.x-1, pt.y+1);
                    if(map.getCell(temp)==0 && checkCorner(temp)){
                        corners.add(temp);
                    }
                    temp = new Point(pt.x+1, pt.y-1);
                    if(map.getCell(temp)==0 && checkCorner(temp)){
                        corners.add(temp);
                    }
                    temp = new Point(pt.x+1, pt.y+1);
                    if(map.getCell(temp)==0 && checkCorner(temp)){
                        corners.add(temp);
                    }

                }
            }
        }
    }

    private boolean checkCorner(Point pt) {
        Map map = Map.getInstance();
        if(!corners.contains(pt)){
            // TODO, most csak vizsgalja a kornyezo 4 cellat, hogy nincs e ugyanolyan szin
            Point temp0 = new Point(pt.x - 1, pt.y);
            Point temp1 = new Point(pt.x + 1, pt.y);
            Point temp2 = new Point(pt.x, pt.y + 1);
            Point temp3 = new Point(pt.x, pt.y - 1);
            if (map.getCell(temp0)==color ||
                map.getCell(temp1)==color ||
                map.getCell(temp2)==color ||
                map.getCell(temp3)==color){
                return false;
            }
            return true;
            // TODO ez lenne a tuti
//            for (Block block : blocks) {
//                if (map.isFitting(block, pt)) {
//                    return true;
//                }
//            }
        }
        return false;
    }

    public boolean placeBlock(int blockId, Point coord){
        Block block = getBlock(blockId);
        if(block == null){
            Log.e("BLOCKS", "BLOCK ALREADY REMOVED");
        } else {
            // lerakja a blockot
            Map map = Map.getInstance();
            for (int i = 0; i < block.getSize(); ++i) {
                Point temp = new Point(coord.x + block.getPoint(i).x, coord.y + block.getPoint(i).y);
                map.setCell(block.getColor(), temp);
            }
            blocks.remove(block);
            Map.getInstance().incStep();
            fillCorners();
        }

        return true;
    }

    // !!!! INDEX ALAPJAN HIVATKOZUNK BLOCKOKRA
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

}
