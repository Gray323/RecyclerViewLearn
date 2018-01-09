package com.rxd.recyclerviewlearn.divider;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rxd.recyclerviewlearn.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class StaggedRecyclerAdapter extends RecyclerView.Adapter<StaggedRecyclerAdapter.MyViewHolder>{

    private List<String> list;
    private List<Integer> heights;

    public StaggedRecyclerAdapter(List<String> list) {
        this.list = list;
        heights = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            heights.add((int) (200 + Math.random() * 50));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder holder  = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String str = list.get(position);
        ViewGroup.LayoutParams layoutparams = holder.textView.getLayoutParams();
        layoutparams.height = heights.get(position);
        holder.textView.setBackgroundColor(Color.rgb(100, heights.get(position), heights.get(position) ));
        holder.textView.setLayoutParams(layoutparams);
        holder.textView.setText(str);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
        }
    }

}
