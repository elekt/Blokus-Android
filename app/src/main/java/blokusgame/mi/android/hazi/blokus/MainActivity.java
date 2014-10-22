package blokusgame.mi.android.hazi.blokus;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import blokusgame.mi.android.hazi.blokus.GameLogic.Map;
import blokusgame.mi.android.hazi.blokus.GameLogic.Player;
import blokusgame.mi.android.hazi.blokus.GameLogic.PlayerHuman;
import blokusgame.mi.android.hazi.blokus.GameLogic.Point;


public class MainActivity extends Activity {
    private Map map = Map.getInstance();
    private Player player1 = new PlayerHuman(1);
    private Player player2 = new PlayerHuman(2);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStep = (Button) findViewById(R.id.btnStep);
        btnStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(step()) {
                    View boardView = findViewById(R.id.boardView);
                    boardView.invalidate();
                }
            }
        });
        Button btnReset = (Button) findViewById(R.id.btnReset);
    btnReset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            map.reset();
            View boardView = findViewById(R.id.boardView);
            boardView.invalidate();
        }
    });
    }

    // this method is called when somebody steps
    private boolean step() {
        int blockIndex = Integer.valueOf(((EditText) findViewById(R.id.blockIndex)).getText().toString());
        int coordx = Integer.valueOf(((EditText)findViewById(R.id.coordX)).getText().toString());
        int coordy = Integer.valueOf(((EditText)findViewById(R.id.coordY)).getText().toString());

        TextView turnText = (TextView) findViewById(R.id.playerTurn);
        Point coord = new Point(coordx, coordy);
        if(map.getSteps()%2==0){
             if(player1.placeBlock(blockIndex, coord)) {
                turnText.setTextColor(Color.RED);
                turnText.setText("Player2");
            } else {
                 Toast.makeText(getApplicationContext(), "Can't place there", Toast.LENGTH_SHORT).show();
                 return false;
             }
        } else {
            if(player2.placeBlock(blockIndex, coord)) {
                turnText.setTextColor(Color.BLUE);
                turnText.setText("Player1");
            } else {
                Toast.makeText(getApplicationContext(), "Can't place there", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
