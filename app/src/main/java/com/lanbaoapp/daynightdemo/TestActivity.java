package com.lanbaoapp.daynightdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lanbaoapp.daynightdemo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private List<TestBean> mDatas = new ArrayList<>();

    @Override
    public int getLayoutRes() {
        return R.layout.activity_test;
    }

    @Override
    public void onCreateActivity(@Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initAdapter();
    }

    private void initAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        for (int i = 0; i < 200; i++) {
            TestBean bean = new TestBean();
            bean.setTitle("标题 : " + i);
            mDatas.add(bean);
        }
        mAdapter = new MyAdapter(mContext, mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, 1));
    }
}
