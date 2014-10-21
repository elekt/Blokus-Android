package GameLogic;

/**
 * Created by elekt on 2014.10.21..
 */
public class PlayerHuman extends Player {
    public PlayerHuman(int _color) {
        super(_color);
    }

    @Override
    public boolean placeBlock() {
        return false;
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
