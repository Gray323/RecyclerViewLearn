package com.rxd.recyclerviewlearn.divider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rxd.recyclerviewlearn.R;
import com.rxd.recyclerviewlearn.wrap.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.rxd.recyclerviewlearn.R.layout;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private StaggedRecyclerAdapter staggedRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        List<String> strings = new ArrayList<>();
        for(int i = 0; i < 120; i++){
            strings.add("第" + i + "个");
        }
        adapter = new RecyclerAdapter(strings);
        //staggedRecyclerAdapter = new StaggedRecyclerAdapter(strings);
        recyclerView = findViewById(R.id.recyclerview);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true    ));
        recyclerView.setLayoutManager(new GridLayoutManager(this,  3));
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new DividerGridViewItemDecoration(this));
        //recyclerView.setAdapter(staggedRecyclerAdapter);
    }
}
