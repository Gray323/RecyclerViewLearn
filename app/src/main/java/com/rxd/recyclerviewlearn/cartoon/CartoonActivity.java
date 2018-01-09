package com.rxd.recyclerviewlearn.cartoon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.rxd.recyclerviewlearn.R;

import java.util.List;

public class CartoonActivity extends AppCompatActivity implements StartDragListener{

    private RecyclerView recyclerView;
    private List<QQMessage> list;
    private CartoonAdapter adapter;
    private MyItemTouchHelperCallback callback;
    private ItemTouchHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoon);
        list = DataUtils.init();
        adapter = new CartoonAdapter(list, this);

        recyclerView = findViewById(R.id.recyclerview_cartoon);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        callback = new MyItemTouchHelperCallback(adapter);
        helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
    }

    /**
     * 主动开始拖拽
     * @param viewHolder
     */
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder){
        helper.startDrag(viewHolder);
    }

}
