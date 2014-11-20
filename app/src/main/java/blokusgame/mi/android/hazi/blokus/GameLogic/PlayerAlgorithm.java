package blokusgame.mi.android.hazi.blokus.GameLogic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by elekt on 2014.10.29..
 */
public class PlayerAlgorithm extends Player {
    private Map map = Map.getInstance();
    private int[][] values;
    private Player enemy;

    public PlayerAlgorithm(int _color) {
        super(_color);
        values = new int[(int)map.getLineSize()][(int)map.getLineSize()];
        for(int i=0; i<map.getLineSize(); ++i){
            for (int j=0; j<map.getLineSize(); ++j){
                values[i][j] = 1;
            }
        }
    }


    private ArrayList<Move> getAllPossibleMoves(){
        ArrayList<Move> moves = new ArrayList<Move>();

        for(int i=1; i<=5; ++i){
            moves.addAll(getNLongMoves(i));
        }

        return moves;
    }

    private ArrayList<Move> getNLongMoves(int n){
        ArrayList<Move> moves = new ArrayList<Move>();

        for (Point corner : corners) {
            for (Block block : blocks) {
                if(block.getSize()==n) {
                    ArrayList<Block> rotations = block.getRotations();
                    for (Block rBlock : rotations) {
                        for (int i = 0; i < rBlock.getSize(); ++i) {
                            Point pt = new Point(corner.x - block.getPoint(i).x, corner.y - block.getPoint(i).y);
                            if (map.isPlaceable(rBlock, pt, corners)) {
                                Block newBlock = new Block(rBlock);
                                int value = getValue(newBlock, pt);
                                Move move = new Move(newBlock, pt, value);
                                moves.add(move);
                            }
                        }
                    }
                }
            }
        }

        return moves;
    }

    private int getValue(Block block, Point pt) {
        int value = 0;

        for(int i=0; i<block.getSize(); ++i){
            Point bPoint = block.getPoint(i);
            value += values[pt.x+bPoint.x][pt.y+bPoint.y];
        }

        return value;
    }

    private void fillValues(){
        ArrayList<Point> enemyCorners = enemy.getCorners();
        for(int i=0; i<map.getLineSize(); ++i){
            for(int j=0; j<map.getLineSize(); ++j){
                if(map.getCell(i,j)!=0){
                    values[i][j] = -100;
                } else if(enemyCorners.contains(new Point(i,j))){
                    values[i][j] += 10;
                }
            }
        }
    }

    // a mainbol ezt kell hivni, nem a placeblockot. sry
    public void nextStep(){
        if(Map.getInstance().getSteps()>=2)
            fillCorners();

        fillValues();
        ArrayList<Move> possibleMoves = getAllPossibleMoves();
        Move bestMove = getBestMove(possibleMoves);
        placeBlock(bestMove.block, bestMove.pt);

        fillCorners();
        enemy.fillCorners();
    }

    private Move getBestMove(ArrayList<Move> moves) {
        int max = -1;
        ArrayList<Move> bestMoves = new ArrayList<Move>();
        for(Move move:moves){
            if(move.value > max){
                bestMoves.clear();
                max = move.value;
            }
            if(move.value == max){
                bestMoves.add(move);
            }
        }

        Random rand = new Random();
        Move bestMove = bestMoves.get(rand.nextInt(bestMoves.size()));
        return bestMove;
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

    public void setEnemy(Player _enemy){
        enemy = _enemy;
    }
}
