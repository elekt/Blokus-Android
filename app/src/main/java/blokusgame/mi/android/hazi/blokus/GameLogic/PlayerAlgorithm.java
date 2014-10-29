package blokusgame.mi.android.hazi.blokus.GameLogic;

import java.util.Random;

/**
 * Created by elekt on 2014.10.29..
 */
public class PlayerAlgorithm extends Player {
    public PlayerAlgorithm(int _color) {
        super(_color);
    }


    // a mainbol ezt kell hivni, nem a placeblockot. sry
    public void nextStep(){
        if(Map.getInstance().getSteps()>=2)
            fillCorners();

        Random rand = new Random();
        int i=0;
        // TODO, minden cornershez tarolni, hogy mik illenek oda, hogy i is egy rand legyen
        while(!placeBlock(i, corners.get(rand.nextInt(corners.size())))){
            ++i;
        }

        fillCorners();
    }

    @Override
    public boolean placeBlock(int blockIndex, Point coord) {
        Map map = Map.getInstance();
        if(map.isFitting(blocks.get(blockIndex), coord)){
            super.placeBlock(blockIndex, coord);
        }
        return true;
    }
}
