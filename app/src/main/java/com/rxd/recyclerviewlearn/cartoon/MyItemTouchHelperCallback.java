package com.rxd.recyclerviewlearn.cartoon;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.rxd.recyclerviewlearn.R;

/**
 * Created by Administrator on 2018/1/8.
 */

public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private ItemTouchMoveListener listener;

    public MyItemTouchHelperCallback(ItemTouchMoveListener listener) {
        this.listener = listener;
    }

    //callback回调监听时先调用的，用来判断当前是什么动作， 比如判断方向
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //方向:up,down,left,right
        //我要监听的拖拽方向
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //我要监听的侧滑方向
        //int swipeFlags = 0;
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int flags = makeMovementFlags(dragFlags, swipeFlags);
        return flags ;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    //当移动的时候回调的方法，拖拽
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        boolean result = listener.onItemMoved(fromPosition, toPosition);
        return result;
    }

    //侧滑的时候回调的
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onItemRemoved(viewHolder.getAdapterPosition());
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        //判断选中状态
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE){
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.color_pink));
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //viewholder被复用时恢复viewholder的样式
        viewHolder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        viewHolder.itemView.setAlpha(1);
       /* viewHolder.itemView.setScaleX(1);
        viewHolder.itemView.setScaleY(1);*/
        //viewHolder.itemView.setTranslationX(viewHolder.itemView.getWidth());
        super.clearView(recyclerView, viewHolder);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //水平方向移动的增量
        float alpha = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
       /* if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            //透明度动画
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setScaleX(alpha);
            viewHolder.itemView.setScaleY(alpha);
        }*/
        //判断是否超出或者达到一定的宽度
        if(dX <= viewHolder.itemView.getWidth() / 2){
            viewHolder.itemView.setTranslationX(-0.5f * viewHolder.itemView.getWidth());
        }else{

        }

        //此方法会自动处理setTranslationX
        //super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
