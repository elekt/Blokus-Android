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
        Block block = blocks.remove(blockIndex);
        boolean isPlaceable = Map.getInstance().isPlaceable(block, coord);
        if( isPlaceable==false){
            return false;
        }
        // lerakja a blockot
        Map map = Map.getInstance();
        for(int i = 0; i<block.getSize(); ++i){
            Point temp = new Point(coord.x +  block.getPoint(i).x, coord.y + block.getPoint(i).y);
            map.setCell(block.getColor(), temp);
        }
        Map.getInstance().incStep();
        return true;
    }
}
