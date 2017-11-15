package com.lanbaoapp.daynightdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lx on 2017/11/15.
 */

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private Context mContext;
    private List<TestBean> mDatas;

    public MyAdapter(Context context, List<TestBean> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rlv_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText(mDatas.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text);
        }
    }

}
