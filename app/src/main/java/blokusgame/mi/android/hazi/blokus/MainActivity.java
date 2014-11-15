package blokusgame.mi.android.hazi.blokus;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import View.BoardTouchListener;
import View.BoardView;
import blokusgame.mi.android.hazi.blokus.GameLogic.Block;
import blokusgame.mi.android.hazi.blokus.GameLogic.Map;
import blokusgame.mi.android.hazi.blokus.GameLogic.Player;
import blokusgame.mi.android.hazi.blokus.GameLogic.PlayerAlgorithm;
import blokusgame.mi.android.hazi.blokus.GameLogic.PlayerHuman;
import blokusgame.mi.android.hazi.blokus.GameLogic.Point;

import static android.view.View.OnClickListener;

public class MainActivity extends Activity implements BoardTouchListener {
    private Map map = Map.getInstance();
    private Player player1 = new PlayerHuman(1);
    private PlayerAlgorithm player2 = new PlayerAlgorithm(2);

    private LinearLayout horizontal_scroll;
    private LinearLayout rotations_layout;
    private BoardView boardView;
    private BlockViewOnClickListener blockClickListener = new BlockViewOnClickListener();

    private ImageView choosenBlock = null;
    private Point coord = null;
    private int blockIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        boardView = (BoardView) findViewById(R.id.boardView);
        boardView.setWasTouchedListener(this);

        horizontal_scroll = (LinearLayout) findViewById(R.id.horizontal_layout);
        rotations_layout = (LinearLayout) findViewById(R.id.rotations_layout);

        setPlayer(player1);

        Button btnStep = (Button) findViewById(R.id.btnStep);
        btnStep.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(step()) {
                    boardView.invalidate();
                }
            }
        });
        Button btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                map.reset();
                // TODO tobbjatekosra
                player1 = new PlayerHuman(1);
                setPlayer(player1);
                player2 = new PlayerAlgorithm(2);
                boardView.setCorners(player1.getCorners());
                boardView.setOverlayBlock(null,null);
                boardView.invalidate();
            }
        });
    }

    private boolean step() {
        if(blockIndex>=0 && coord!=null) {
            if (player1.placeBlock(blockIndex, coord)) {
                boardView.setOverlayBlock(null, null);
                horizontal_scroll.removeView(choosenBlock);
                boardView.setCorners(player2.getCorners());
                // AI jatekos lepese
                player2.nextStep();
                boardView.setCorners(player1.getCorners());
            }
        } else{
            Toast.makeText(getApplicationContext(), "Block, or cell not selected", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    void setPlayer(Player player){
        ArrayList<Block> blocks = new ArrayList<Block>();
        horizontal_scroll.removeAllViews();
        for(int i=0; i<21; ++i){
            Block block = player.getBlock(i);
            if(block!=null){
                blocks.add(block);
            }
        }

        boardView.setCorners(player1.getCorners());

        for (Block block : blocks) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(block.getImageId());
            imageView.setId(block.getId());
            imageView.setOnClickListener(blockClickListener);
            horizontal_scroll.addView(imageView);
        }
    }

//    void setBlockRotations(Block block){
//        ArrayList<Block> rotations = block.getRotations();
//
//        for(Block rotated : rotations){
//            View.BlockView rotatedImage = new View.BlockView(this);
//
//            rotations_layout.addView(rotatedImage);
//        }
//    }

    @Override
    public void onBoardTouched(int x, int y) {
        coord = new Point(x,y);
        Log.e("Cell coordinates:", String.valueOf(x)+" "+String.valueOf(y));
        if(choosenBlock!=null) {
            boardView.setOverlayBlock(player1.getBlock(blockIndex), coord);
            boardView.invalidate();
        }
    }

    // a felcsuszo ablakon a block kepeket kezeli
    public class BlockViewOnClickListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            blockIndex = view.getId();
            try {
                boardView.setOverlayBlock(player1.getBlock(view.getId()), coord);
                boardView.invalidate();
            }catch (NumberFormatException ex){
                Toast.makeText(getApplicationContext(), "Set the coordinates too", Toast.LENGTH_SHORT).show();
            }
            choosenBlock = (ImageView) view;
        }
    }
}
