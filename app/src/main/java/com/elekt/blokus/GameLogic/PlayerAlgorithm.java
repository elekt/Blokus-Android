package com.elekt.blokus.GameLogic;

import java.util.ArrayList;
import java.util.Random;

public class PlayerAlgorithm extends Player {
    private Map map = Map.getInstance();
    private int[][] values;
    private ArrayList<Integer> firstSteps = new ArrayList<Integer>(3);
    private Player enemy;

    public PlayerAlgorithm(int _color) {
        super(_color);

        // put the first steps in random order in
        Random rand = new Random();
        int temp = rand.nextInt(3);
        firstSteps.add(14 + temp);
        firstSteps.add(14 + (temp+1)%3);
        firstSteps.add(14 + (temp+2)%3);

        values = new int[(int)map.getLineSize()][(int)map.getLineSize()];
        for(int i=0; i<map.getLineSize(); ++i){
            for (int j=0; j<map.getLineSize(); ++j){
                values[i][j] = 1;
            }
        }
    }

    // AI player chooses, and places block here
    public boolean nextStep() {
        fillCorners();

        fillValues();
        ArrayList<Move> possibleMoves = new ArrayList<Move>();

        // first 2 moves are from the 3 blocks from the firstSteps
        if(steps<2){
            possibleMoves.addAll(getMovesByBlock(firstSteps.remove(0)));
        }else {
            // for optimalization, first only searches only the longest pieces
            possibleMoves.addAll(getNLongMoves(5));
            possibleMoves.addAll(getNLongMoves(4));

            if (possibleMoves.isEmpty()) {
                possibleMoves.addAll(getNLongMoves(3));
                possibleMoves.addAll(getNLongMoves(2));
                possibleMoves.addAll(getNLongMoves(1));
            }
        }
        if(possibleMoves.isEmpty())
            return false;

        Move bestMove = getBestMove(possibleMoves);
        placeBlock(bestMove.block, bestMove.pt);
        ++steps;

        fillCorners();
        enemy.fillCorners();
        return true;
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
                                //Block newBlock = new Block(rBlock);
                                int value = getValue(rBlock, pt);
                                Move move = new Move(rBlock, pt, value);
                                moves.add(move);
                            }
                        }
                    }
                }
            }
        }

        return moves;
    }

    private ArrayList<Move> getMovesByBlock(int blockId){
        ArrayList<Move> moves = new ArrayList<Move>();

        Block block = getBlock(blockId);
        ArrayList<Block> rotations = block.getRotations();
        for (Point corner : corners) {
            for (Block rBlock : rotations) {
                for (int i = 0; i < rBlock.getSize(); ++i) {
                    Point pt = new Point(corner.x - block.getPoint(i).x, corner.y - block.getPoint(i).y);
                    if (map.isPlaceable(rBlock, pt, corners)) {
                        Block newBlock = new Block(rBlock);
                        int value = getValue(newBlock, pt);
                        if(value != PlayerConstants.DEAD_CELL) {
                            Move move = new Move(newBlock, pt, value);
                            moves.add(move);
                        }
                    }
                }
            }
        }
        return moves;
    }

    private ArrayList<Move> getNLongEnemyMoves(int n){
        ArrayList<Move> moves = new ArrayList<Move>();
        ArrayList<Block> eBlocks = enemy.blocks;
        ArrayList<Point> eCorners = enemy.getCorners();

        for (Point corner : eCorners) {
            for (Block block : eBlocks) {
                if(block.getSize()==n) {
                    ArrayList<Block> rotations = block.getRotations();
                    for (Block rBlock : rotations) {
                        for (int i = 0; i < rBlock.getSize(); ++i) {
                            Point pt = new Point(corner.x - block.getPoint(i).x, corner.y - block.getPoint(i).y);
                            if (map.isPlaceable(rBlock, pt, eCorners)) {
                                //Block newBlock = new Block(rBlock);
                                int value = getValue(rBlock, pt);
                                Move move = new Move(rBlock, pt, value);
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

        for(int i=0; i<block.getSize() && value != PlayerConstants.DEAD_CELL; ++i){
            Point bPoint = block.getPoint(i);
            int adding = values[pt.x+bPoint.x][pt.y+bPoint.y];
            if(adding == PlayerConstants.DEAD_CELL){
                value = PlayerConstants.DEAD_CELL;
            } else {
                value += adding;
            }
        }
        // to maximalize the number of corners
        value += 5*validCornersCount(block, pt);

        return value;
    }

    private int validCornersCount(Block block, Point pt) {
        int ret = 0;

        for(int i=0; i<block.getSize(); ++i){
            Point blockPoint = new Point(pt.x+block.getPoint(i).x, pt.y+block.getPoint(i).y);

            // the additional neighbour color check frees a lot of computing time, and solves a bug. the bug is concerning me
            Point temp = new Point(blockPoint.x-1, blockPoint.y-1);
            if(map.getCell(temp)==0 && map.getCell(blockPoint.x-1, blockPoint.y)!=color &&
                    map.getCell(blockPoint.x, blockPoint.y-1)!=color && checkCorner(temp)){
                ++ret;
            }
            temp = new Point(blockPoint.x-1, blockPoint.y+1);
            if(map.getCell(temp)==0 && map.getCell(blockPoint.x-1, blockPoint.y)!=color &&
                    map.getCell(blockPoint.x, blockPoint.y+1)!=color && checkCorner(temp)){
                ++ret;
            }
            temp = new Point(blockPoint.x+1, blockPoint.y-1);
            if(map.getCell(temp)==0 && map.getCell(blockPoint.x+1, blockPoint.y)!=color &&
                    map.getCell(blockPoint.x, blockPoint.y-1)!=color && checkCorner(temp)){
                ++ret;
            }
            temp = new Point(blockPoint.x+1, blockPoint.y+1);
            if(map.getCell(temp)==0 && map.getCell(blockPoint.x+1, blockPoint.y)!=color &&
                    map.getCell(blockPoint.x, blockPoint.y+1)!=color && checkCorner(temp)){
                ++ret;
            }
        }

        return ret;
    }

    // focuses on the opponents corners, and side-to-side meets
    private void fillValues(){
        ArrayList<Point> enemyCorners = enemy.getCorners();
        for(int i=0; i<map.getLineSize(); ++i){
            for(int j=0; j<map.getLineSize(); ++j){
                if(map.getCell(i,j)!=0 || hasTwoNeighbours(i, j)){
                    values[i][j] = PlayerConstants.DEAD_CELL;
                } else {
                    if (enemyCorners.contains(new Point(i, j))) {
                        values[i][j] += 10;
                    }
                    // check if sides "touch"
                    if(map.getCell(i+1,j)==enemy.getColor()){
                        values[i][j] += 5;
                    }
                    if(map.getCell(i-1,j)==enemy.getColor()){
                        values[i][j] += 5;
                    }
                    if(map.getCell(i,j+1)==enemy.getColor()){
                        values[i][j] += 5;
                    }
                    if(map.getCell(i,j-1)==enemy.getColor()){
                        values[i][j] += 5;
                    }
                }
            }
        }
        ArrayList<Move> enemyMoves = getNLongEnemyMoves(5);
        enemyMoves.addAll(getNLongEnemyMoves(4));
        for(Move move:enemyMoves){
            for(int i=0; i<move.block.getSize(); ++i){
                Point temp = new Point(move.pt.x +  move.block.getPoint(i).x, move.pt.y + move.block.getPoint(i).y);
                values[temp.x][temp.y] += 2;
            }
        }
    }
    // dead cells, who are empty, but their neighbours assure that no block will be over them
    private boolean hasTwoNeighbours(int i, int j) {
        Map map = Map.getInstance();
        if(map.getCell(i,j)==0){
            // if AI player is neighbouring
            if(map.getCell(i, j+1)==color ||
                    map.getCell(i+1, j)==color ||
                    map.getCell(i, j-1)==color) {
                // if there is an enemy neighbour
                if (map.getCell(i, j + 1) == enemy.getColor() ||
                        map.getCell(i + 1, j) == enemy.getColor() ||
                        map.getCell(i, j - 1) == enemy.getColor()) {
                    return true;
                }
            }
        }
        return false;
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
