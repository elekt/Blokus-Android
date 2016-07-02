package com.elekt.blokus.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.elekt.blokus.GameLogic.Block;
import com.elekt.blokus.GameLogic.Point;
import com.elekt.blokus.R;

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

        Point min = block.getMin();
        Point max = block.getMax();
        float offsetX = (min.x - dimensions.x/2.0f)<(max.x - dimensions.x/2.0f)?
                (min.x - dimensions.x/2.0f) : (max.x - dimensions.x/2.0f);
        float offsetY = (min.y - dimensions.y/2.0f)<(max.y - dimensions.y/2.0f)?
                (min.y - dimensions.y/2.0f) : (max.y - dimensions.y/2.0f);
        for(int i=0; i<block.getSize(); ++i){//todo
            float startX = center + block.getPoint(i).x*cellSize + offsetX;
            float startY = center + block.getPoint(i).y*cellSize + offsetY;
            canvas.drawRect(startX-cellSize/2, startY-cellSize/2,
                            startX+cellSize/2, startY+cellSize/2, paintCell);
        }

//        for(int i=0; i<block.getSize(); ++i) {//todo
//            Point pt = block.getPoint(i);
//            float startX = center - (pt.x* cellSize - (dim.x*cellSize)/2.0f);
//            float startY = center - (pt.y* cellSize - (dim.y*cellSize)/2.0f);
//            canvas.drawRect(startX, startY, startX + cellSize, startY + cellSize, paintCell);
//        }
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
