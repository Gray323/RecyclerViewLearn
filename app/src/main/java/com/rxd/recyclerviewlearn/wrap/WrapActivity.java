package com.rxd.recyclerviewlearn.wrap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rxd.recyclerviewlearn.R;

import java.util.ArrayList;
import java.util.List;

public class WrapActivity extends AppCompatActivity {

    private WrapRecyclerView recyclerView;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrap);

        recyclerView = findViewById(R.id.wrap_recyclerview);

        List<String> strings = new ArrayList<>();
        for(int i = 0; i < 120; i++){
            strings.add("第" + i + "个");
        }
        adapter = new RecyclerAdapter(strings);

        TextView headerView = new TextView(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        headerView.setLayoutParams(params);
        headerView.setText("hello");
        recyclerView.addHeaderView(headerView);

        TextView footerView = new TextView(this);
        ViewGroup.LayoutParams params2 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        footerView.setLayoutParams(params2);
        footerView.setText("end");
        recyclerView.addFooterView(footerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
