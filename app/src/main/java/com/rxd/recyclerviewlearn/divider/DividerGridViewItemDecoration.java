package com.rxd.recyclerviewlearn.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Gray on 2018/1/4.
 */

public class DividerGridViewItemDecoration extends RecyclerView.ItemDecoration{

    private Drawable mDivider;
    private  int[] attrs = new int[]{android.R.attr.listDivider};

    public DividerGridViewItemDecoration(Context context){
        TypedArray ta =  context.obtainStyledAttributes(attrs);
        mDivider = ta.getDrawable(0);
        ta.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawVertaical(c, parent);
        drawHorizontal(c, parent);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        //绘制水平间隔线
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams paChild = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getLeft() - paChild.leftMargin;
            int right = child.getRight() + paChild.rightMargin;
            int top = child.getBottom() + paChild.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawVertaical(Canvas c, RecyclerView parent) {
        //绘制垂直间隔线
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            /*if( i % 3 == 2){
                continue;
            }*/
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams paChild = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + paChild.rightMargin;
            int right = left + mDivider.getIntrinsicWidth();
            int top = child.getTop() - paChild.topMargin;
            int bottom = child.getBottom()  + paChild.bottomMargin;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        int right = mDivider.getIntrinsicWidth();
        int bottom = mDivider.getIntrinsicHeight();
        if(isLastColumn(itemPosition, parent)) {
            right = 0;
        }
        if(isLastRow(itemPosition, parent)) {
            bottom = 0;
        }
        outRect.set(0, 0, right, bottom);
    }

    /**
     * 判断是否是最后一行
     * @param itemPosition
     * @param parent
     * @return
     */
    private boolean isLastRow(int itemPosition, RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            GridLayoutManager manager = (GridLayoutManager) layoutManager;
            int childCount = parent.getAdapter().getItemCount();
            int spanCount = manager.getSpanCount();
            int lastRowCount = childCount % spanCount;
            if(lastRowCount == spanCount - 1 || lastRowCount < spanCount){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是最后一列
     * @param itemPosition
     * @param parent
     * @return
     */
    private boolean isLastColumn(int itemPosition, RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            GridLayoutManager manager = (GridLayoutManager) layoutManager;
            int spanCount = manager.getSpanCount();
            if(itemPosition % spanCount == spanCount - 1){
                return true;
            }
        }

        return false;
    }
}
