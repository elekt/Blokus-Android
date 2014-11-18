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
        center = getWidth()/2;

        drawBackGround(canvas);
        drawBlock(canvas);
    }

    private void drawBlock(Canvas canvas) {
        Point dimensions = block.getDimensions();
        Paint paintCell = new Paint();
        paintCell.setColor(Color.GREEN);
        paintCell.setStyle(Paint.Style.FILL);

        for(int i=0; i<block.getSize(); ++i){
            canvas.drawRect(center-(dimensions.x/2*cellSize), center-(dimensions.y/2 * cellSize), center-(dimensions.x/2*cellSize)+cellSize, center-(dimensions.y/2 * cellSize)+cellSize, paintCell);
        }
    }

    private void drawBackGround(Canvas canvas) {
        Paint paintBg = new Paint();
        paintBg.setColor(Color.GRAY);
        paintBg.setStyle(Paint.Style.FILL);

        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);
    }

    public void setBlock(Block _block){ block = _block; }

    // to make sure its always a square
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = width > height ? height : width;
        setMeasuredDimension(200, 200);
    }
}
