package View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import blokusgame.mi.android.hazi.blokus.GameLogic.Block;
import blokusgame.mi.android.hazi.blokus.GameLogic.Point;
import blokusgame.mi.android.hazi.blokus.R;

/**
 * Created by elekt on 2014.11.13..
 */
public class BlockView extends View{
    private Block block;
    private float cellSize;
    private float center;

    public BlockView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cellSize = (getWidth()-2*getResources().getDimension(R.dimen.block_margin)) / 5.0f;
        center = getWidth()/2.0f;

        drawBackGround(canvas);
        drawBlock(canvas);
    }

    private void drawBlock(Canvas canvas) {
        Point dimensions = block.getDimensions();
        Paint paintCell = new Paint();
        paintCell.setColor(Color.GREEN);
        paintCell.setStyle(Paint.Style.FILL);

        for(int i=0; i<block.getSize(); ++i){//todo
            float startX = center + block.getPoint(i).x*cellSize - ((dimensions.x-1)/*/2.0f*/*cellSize);
            float startY = center + block.getPoint(i).y*cellSize - ((dimensions.y-1)/*/2.0f */* cellSize);
            canvas.drawRect(startX-cellSize/2, startY-cellSize/2,
                            startX+cellSize/2, startY+cellSize/2, paintCell);
        }
    }

    private void drawBackGround(Canvas canvas) {
        Paint paintBg = new Paint();
        paintBg.setColor(Color.GRAY);
        paintBg.setStyle(Paint.Style.FILL);

        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);
        paintBg.setColor(Color.DKGRAY);
        canvas.drawRect(10,10,getWidth()-10, getHeight()-10, paintBg);
    }

    public void setBlock(Block _block){ block = _block; }
    public Block getBlock() { return block;  }
    // to make sure its always a square
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = (int)getResources().getDimension(R.dimen.block_size);
        setMeasuredDimension(size, size);
    }


}
