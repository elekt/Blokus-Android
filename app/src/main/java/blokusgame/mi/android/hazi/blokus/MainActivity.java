package blokusgame.mi.android.hazi.blokus;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;

import View.BoardTouchListener;
import View.*;
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
    private SlidingUpPanelLayout slidingUpLayout;
    private BoardView boardView;
    private BlockViewOnClickListener blockClickListener = new BlockViewOnClickListener();
    private RotatedBlockViewOnClickListener rotatedBlockClickListener = new RotatedBlockViewOnClickListener();

    private ImageView choosenBlockView = null;
    private Point choosenPoint = null;
    private Block choosenBlock = null;
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

        slidingUpLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        slidingUpLayout.setCoveredFadeColor(Color.TRANSPARENT);

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
                boardView.setOverlayBlock(null, null);
                boardView.invalidate();
                rotations_layout.removeAllViews();
            }
        });
    }

    private boolean step() {
        if(blockIndex>=0 && choosenPoint!=null) {
            if (player1.placeBlock(choosenBlock, choosenPoint)) {
                boardView.setOverlayBlock(null, null);
                horizontal_scroll.removeView(choosenBlockView);
                boardView.setCorners(player2.getCorners());
                rotations_layout.removeAllViews();
                // AI jatekos lepese
                player2.nextStep();
                boardView.setCorners(player1.getCorners());
                slidingUpLayout.expandPanel();
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
            slidingUpLayout.expandPanel();
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

    @Override
    public void onBoardTouched(int x, int y) {
        choosenPoint = new Point(x,y);
        Log.e("Cell coordinates:", String.valueOf(x)+" "+String.valueOf(y));
        if(choosenBlockView!=null) {
            boardView.setOverlayBlock(choosenBlock, choosenPoint);
            boardView.invalidate();
        }
    }

    // a felcsuszo ablakon a block kepeket kezeli
    public class BlockViewOnClickListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            blockIndex = view.getId();
            try {
                choosenBlock = player1.getBlock(view.getId());
                boardView.setOverlayBlock(choosenBlock, choosenPoint);
                boardView.invalidate();
                // draw rotations
                rotations_layout.removeAllViews();
                ArrayList<Block> rotations = choosenBlock.getRotations();
                for(Block b:rotations){
                    BlockView bView = new BlockView(getApplicationContext());
                    bView.setBlock(b);
                    bView.setOnClickListener(rotatedBlockClickListener);
                    rotations_layout.addView(bView);
                }
                rotations_layout.invalidate();
            }catch (NumberFormatException ex){
                Toast.makeText(getApplicationContext(), "Set the coordinates too", Toast.LENGTH_SHORT).show();
            }
            choosenBlockView = (ImageView) view;
            slidingUpLayout.collapsePanel();
        }
    }
    public class RotatedBlockViewOnClickListener implements OnClickListener{

        @Override
        public void onClick(View view) {
            Block block = ((BlockView)view).getBlock();
            blockIndex = block.getId();
            choosenBlock = block;
            boardView.setOverlayBlock(choosenBlock, choosenPoint);
            boardView.invalidate();
        }
    }
}
