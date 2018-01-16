package zhangjiye.bawie.com.boni.view.cusView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by 张继业 on 2017/12/29.
 */

public class CustomView extends RecyclerView.ItemDecoration {

    private final Paint paint;

    public CustomView(){
        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setTextSize(50);
        paint.setStrokeWidth(2);
    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        drawLine(c,parent);
//        Rect rect = new Rect(0,100,500,600);
//        c.drawRect(rect,paint);
    }

    private void drawLine(Canvas c, RecyclerView recyclerView) {
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            View childAt = recyclerView.getChildAt(i);
            if (childAt != null) {
                int left = childAt.getLeft();
                int right = childAt.getRight();
                int height = childAt.getBottom();
                Log.e("myMessage", "left = " + left + " right " + right + "height = " + height);
                c.drawLine(left, height, right, height, paint);
            }

        }
    }
}
