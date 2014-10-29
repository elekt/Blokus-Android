package blokusgame.mi.android.hazi.blokus.GameLogic;

/**
 * Created by elekt on 2014.10.29..
 */
public class PlayerAlgorithm extends Player {
    public PlayerAlgorithm(int _color) {
        super(_color);
    }

    @Override
    public boolean placeBlock(int blockIndex, Point coord) {
        return false;
    }
}
