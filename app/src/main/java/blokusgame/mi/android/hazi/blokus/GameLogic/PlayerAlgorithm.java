package blokusgame.mi.android.hazi.blokus.GameLogic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by elekt on 2014.10.29..
 */
public class PlayerAlgorithm extends Player {
    private ArrayList<Integer> values;
    private Map map = Map.getInstance();

    public PlayerAlgorithm(int _color) {
        super(_color);
    }

    private ArrayList<Move> getAllPossibleMoves(){
        ArrayList<Move> moves = new ArrayList<Move>();

        for (Point corner : corners) {
            for (Block block : blocks) {
                ArrayList<Block> rotations = block.getRotations();
                for(Block rBlock:rotations){
                    for(int i=0; i<rBlock.getSize(); ++i){
                        Point pt = new Point(corner.x-block.getPoint(i).x, corner.y-block.getPoint(i).y);
                        if(map.isPlaceable(rBlock, pt)){
                            Block newBlock = new Block(rBlock);
                            Move move = new Move(newBlock, pt, 0);
                            moves.add(move);
                        }
                    }
                }
            }
        }

        return moves;
    }

    private void fillValues(){
        Map map = Map.getInstance();
        for(int i=0; i<map.getLineSize()*map.getLineSize(); ++i){
            if(map.getCell(i)!=0){
                values.set(i, -100);
            } else {
                values.set(i, 1);
            }
        }
    }

    // a mainbol ezt kell hivni, nem a placeblockot. sry
    public void nextStep(){
        if(Map.getInstance().getSteps()>=2)
            fillCorners();

        ArrayList<Move> possibleMoves = getAllPossibleMoves();

        Random rand = new Random();
        int i=0;
        // TODO, minden cornershez tarolni, hogy mik illenek oda, hogy i is egy rand legyen
        while(!placeBlock(getBlock(i), corners.get(rand.nextInt(corners.size())))){
            ++i;
        }

        fillCorners();
    }

    @Override
    public boolean placeBlock(Block block, Point coord) {
        Map map = Map.getInstance();
        if(block==null)
             return false;
        if(map.isPlaceable(block, coord)){
            super.placeBlock(block, coord);
        }
        return true;
    }

    private class Move {
        Point pt = null;
        Block block = null;
        int value = 0;

        public Move(Block _block, Point _pt, int _value) {
            pt = _pt;
            block = _block;
            value = _value;
        }
    }
}
