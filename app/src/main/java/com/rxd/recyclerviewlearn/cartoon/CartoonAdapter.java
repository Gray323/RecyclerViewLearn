package com.rxd.recyclerviewlearn.cartoon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rxd.recyclerviewlearn.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/1/8.
 */

public class CartoonAdapter extends RecyclerView.Adapter<CartoonAdapter.MyViewHolder> implements ItemTouchMoveListener{

    private List<QQMessage> list;
    private StartDragListener listener;

    public CartoonAdapter(List<QQMessage> list, StartDragListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        QQMessage message = list.get(position);
        holder.iv_logo.setImageResource(message.getLogo());
        holder.tv_name.setText(message.getName());
        holder.tv_Msg.setText(message.getLastMsg());
        holder.tv_time.setText(message.getTime());

        holder.iv_logo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    listener.onStartDrag(holder);
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public boolean onItemMoved(int fromPosition, int toPosition) {
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemRemoved(int position) {
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_logo;
        private TextView tv_name;
        private TextView tv_Msg;
        private TextView tv_time;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_logo = itemView.findViewById(R.id.iv_logo);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_Msg = itemView.findViewById(R.id.tv_lastMsg);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }

}
