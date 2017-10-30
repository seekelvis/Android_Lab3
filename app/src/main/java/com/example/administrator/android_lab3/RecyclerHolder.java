package com.example.administrator.android_lab3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/10/25.
 */

class RecyclerHolder extends RecyclerView.ViewHolder {

    TextView alpha;//首字母
    TextView name;//商品名

    RecyclerHolder (View view) {//构造函数
        super(view);
        alpha=(TextView) view.findViewById(R.id.alpha);
        name = (TextView) view.findViewById(R.id.name);
    }

}
