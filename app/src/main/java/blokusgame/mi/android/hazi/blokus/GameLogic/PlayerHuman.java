package blokusgame.mi.android.hazi.blokus.GameLogic;

/**
 * Created by elekt on 2014.10.21..
 */
public class PlayerHuman extends Player {
    public PlayerHuman(int _color) {
        super(_color);
    }

    @Override
    public boolean placeBlock(int blockIndex, Point coord) {
        if(!Map.getInstance().isPlaceable(blocks.get(blockIndex), coord)) {
            return false;
        }
        // lerakja a blockot
        Block block = blocks.get(blockIndex);
        Map map = Map.getInstance();
        for(int i = 0; i<block.getSize(); ++i){
            Point temp = new Point(coord.x +  block.getPoint(i).x, coord.y + block.getPoint(i).y);
            map.setCell(block.getColor(), temp);
        }
        Map.getInstance().incStep();
        return true;
    }

    @Override
    protected Block chooseBlock() {
        return null;
    }

    @Override
    protected Point choosePoint() {
        return null;
    }


}
