package com.rxd.recyclerviewlearn.wrap;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rxd.recyclerviewlearn.R;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private List<String> list;

    public RecyclerAdapter(List<String> list) {
        this.list = list;
        Log.d("sc", "adapter初始化");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("sc", "onCreateViewHolder");
        View view = View.inflate(parent.getContext(), R.layout.item, null);
        MyViewHolder holder  = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d("sc", "size:" + list.size());
        String str = list.get(position);
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
