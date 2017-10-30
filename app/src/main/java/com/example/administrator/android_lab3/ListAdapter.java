package com.example.administrator.android_lab3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/10/26.
 */

public class ListAdapter extends BaseAdapter{
    private Context mcontext;
    private List<Goods> mDatas;

    public ListAdapter(Context context, List<Goods> Datas){
        mcontext = context;
        mDatas = Datas;
    }

    @Override
    public int getCount() {
        return mDatas.size() ;
    }

    @Override
    public Object getItem(int position) {
        if (mDatas == null)
            return null;
        else
            return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View convertView;
        ListHolder listHolder;

        if (view == null){
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.item, null);
            listHolder = new ListHolder(convertView);
            convertView.setTag(listHolder);//建立关联
        }
        else
        {
            convertView = view;
            listHolder = (ListHolder) convertView.getTag();
        }
        if (position == 0)
            listHolder.alpha.setText("*");
        else
            listHolder.alpha.setText(mDatas.get(position).name.substring(0,1));
        listHolder.name.setText(mDatas.get(position).name);
        listHolder.price.setText(mDatas.get(position).price);
        return convertView;
    }
}
