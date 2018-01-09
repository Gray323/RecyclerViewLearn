package com.rxd.recyclerviewlearn.cartoon;

/**
 * Created by Administrator on 2018/1/8.
 */

public interface ItemTouchMoveListener {

    /**
     * 当拖拽时回调
     * @param fromPosition
     * @param toPosition
     * @return
     */
    boolean onItemMoved(int fromPosition, int toPosition);


    void onItemRemoved(int position);

}
