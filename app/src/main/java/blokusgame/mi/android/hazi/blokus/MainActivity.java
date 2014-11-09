package blokusgame.mi.android.hazi.blokus;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
    private BoardView boardView;
    private BlockViewOnClickListener blockClickListener = new BlockViewOnClickListener();
    private EditText blockIndexEditText;
    private EditText coordXEditText;
    private EditText coordYEditText;

    private ImageView choosenBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boardView = (BoardView) findViewById(R.id.boardView);
        boardView.setWasTouchedListener(this);

        boardView.setCorners(player1.getCorners());

        horizontal_scroll = (LinearLayout) findViewById(R.id.horizontal_layout);
        blockIndexEditText = (EditText) findViewById(R.id.blockIndex);
        coordXEditText = (EditText)findViewById(R.id.coordX);
        coordYEditText = (EditText)findViewById(R.id.coordY);

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
                boardView.invalidate();
            }
        });
    }

    private boolean step() {
        int blockIndex = Integer.valueOf(blockIndexEditText.getText().toString());
        int coordx = Integer.valueOf(coordXEditText.getText().toString());
        int coordy = Integer.valueOf(coordYEditText.getText().toString());

        TextView turnText = (TextView) findViewById(R.id.playerTurn);
        Point coord = new Point(coordx, coordy);
        if(map.getSteps()%2==0){
             if(player1.placeBlock(blockIndex, coord)) {
                turnText.setTextColor(Color.RED);
                turnText.setText("Player2");
                boardView.setCorners(player2.getCorners());
                horizontal_scroll.removeView(choosenBlock);
             } else {
                 Toast.makeText(getApplicationContext(), "Can't place there", Toast.LENGTH_SHORT).show();
                 return false;
             }
        } else {
            player2.nextStep();
            turnText.setTextColor(Color.BLUE);
            turnText.setText("Player1");
            boardView.setCorners(player1.getCorners());
        }
        return true;
    }

    void setPlayer(Player player){
        ArrayList<Block> blocks = player.getBlocks();

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
        EditText coordX = (EditText) findViewById(R.id.coordX);
        coordX.setText(String.valueOf(x));
        EditText coordY = (EditText) findViewById(R.id.coordY);
        coordY.setText(String.valueOf(y));
    }

    public class BlockViewOnClickListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            blockIndexEditText.setText(String.valueOf(view.getId()));
            int coordx = Integer.valueOf(coordXEditText.getText().toString());
            int coordy = Integer.valueOf(coordYEditText.getText().toString());
            boardView.setOverlayBlock(player1.getBlock(view.getId()), new Point(coordx, coordy));
            boardView.invalidate();
            choosenBlock = (ImageView) view;
            Log.e("CLICKED:", String.valueOf(view.getId()));
        }
    }
}
