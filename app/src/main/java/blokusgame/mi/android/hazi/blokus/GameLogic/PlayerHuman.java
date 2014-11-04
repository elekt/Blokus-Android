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
        if(Map.getInstance().getSteps()>=2)
            fillCorners();

         // nem feltetlen a kezdopontnal kell lennie
//        if(!corners.contains(coord)){
//            return false;
//        }
        Block block = blocks.get(blockIndex);
        boolean isPlaceable = Map.getInstance().isPlaceable(block, corners, coord);
        if(!isPlaceable){
            return false;
        }

        super.placeBlock(blockIndex, coord);

        return true;
    }
}
