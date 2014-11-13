package View;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by elekt on 2014.11.13..
 */
public class BlockView extends View{

    public BlockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackGround(canvas);
        drawBlock(canvas);
    }

    private void drawBlock(Canvas canvas) {

    }

    private void drawBackGround(Canvas canvas) {



    }
}
